import model.Countable;
import model.Score;
import model.classifiedScores.NonBonusScore;
import model.classifiedScores.Spare;
import model.classifiedScores.Strike;
import providers.ScoresProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Game {
    public static void main(String[] args) {
        int[] scores = getscores();
        Score[] framesScores = getFramesScores(scores);
        Countable[] classifiedScores = getClassifiedScores(framesScores);
        int[] classifiedFrameScores = getCalculatedPointsForEachFrame(classifiedScores);
        int totalPointsInGame = getTotalPointsInGame(classifiedFrameScores);

        System.out.println(Arrays.toString(scores));
        for (Score score : framesScores) {
            System.out.println(score);
        }
        System.out.println("------------------------------------");
        for (Countable object : classifiedScores) {
            System.out.println(object);
        }
        System.out.println("Points in each frame: " + Arrays.toString(classifiedFrameScores));
        System.out.println(totalPointsInGame);
    }

    public static int[] getscores() {
        ScoresProvider randomScoreProvider = new ScoresProvider();
        int[] scores = new int[21];
        for (int i = 0; i < scores.length - 1; i += 2) {
            Score randomPairOfScores = randomScoreProvider.getRandomPairOfScores();
            scores[i] = randomPairOfScores.getFirstScore();
            scores[i + 1] = randomPairOfScores.getSecondScore();
            if (i == 18) {
                scores[20] = randomScoreProvider.getRandomBonusScore();
            }
        }
        return scores;
    }

    public static Score[] getFramesScores(int[] rolls) {
        Score[] scoreArray = new Score[10];
        for (int i = 0, j = 0; i < rolls.length - 1 && j < scoreArray.length; i += 2, j++) {
            boolean isLastFrame = j == scoreArray.length - 1;
            if (isLastFrame) {
                if (rolls[i] + rolls[i + 1] == 10 || rolls[i] == 10 || rolls[i + 1] == 10) {
                    Score scoreWithExtraRoll = new Score(
                            rolls[i],
                            rolls[i + 1],
                            rolls[i + 2],
                            true);
                    scoreArray[j] = scoreWithExtraRoll;
                } else {
                    Score nonBonusScore = new Score(
                            rolls[i],
                            rolls[i + 1],
                            false);
                    scoreArray[j] = nonBonusScore;
                }
            } else {
                scoreArray[j] = new Score(rolls[i],
                        rolls[i + 1], false);
            }
        }
        return scoreArray;
    }

    public static Countable[] getClassifiedScores(Score[] scoreArray) {
        if (scoreArray == null || Arrays.stream(scoreArray).anyMatch(Objects::isNull) ||
                Arrays.stream(scoreArray).anyMatch(x -> x.countTotal() > 10 || x.countTotal() < 0)) {
            throw new IllegalArgumentException("Wrong value in scoresArray");
        }
        List<Countable> namedScores = new ArrayList<>();
        for (Score score : scoreArray) {
            if (score.hasExtraScore()) {
                namedScores.add(new NonBonusScore(
                        score.getFirstScore(),
                        score.getSecondScore(),
                        score.getThirdScore(),
                        true));
            } else {
                if (score.getFirstScore() == 10 || score.getSecondScore() == 10) {
                    if (score.getFirstScore() == 10) {
                        namedScores.add(new Strike(score.getFirstScore()));
                    } else {
                        namedScores.add(new Strike(score.getSecondScore()));
                    }
                } else if (score.countTotal() == 10) {
                    namedScores.add(new Spare(score.getFirstScore(), score.getSecondScore()));
                } else {
                    namedScores.add(new NonBonusScore(score.getFirstScore(), score.getSecondScore(), false));
                }
            }
        }
        return namedScores.toArray(new Countable[0]);
    }

    public static int[] getCalculatedPointsForEachFrame(Countable[] classifiedScores) {
        if (classifiedScores == null || classifiedScores.length != 10 || Arrays.stream(classifiedScores).anyMatch(Objects::isNull) ||
                Arrays.stream(classifiedScores).anyMatch(x -> x.countTotal() > 10 || x.countTotal() < 0)) {
            throw new IllegalArgumentException("Wrong value in scoresArray");
        }
        int[] frameScores = new int[10];
        for (int i = 0; i < 10; i++) {
            int actualFrameScore = 0;
            Countable currentFrame = classifiedScores[i];
            if (i == 9 && currentFrame instanceof NonBonusScore && ((NonBonusScore) currentFrame).hasExtraRoll()) {
                actualFrameScore += ((NonBonusScore) currentFrame).countTotalWithExtraRoll();
            } else if (i == 9 && currentFrame instanceof NonBonusScore && !((NonBonusScore) currentFrame).hasExtraRoll()) {
                actualFrameScore += currentFrame.countTotal();
            } else if (i < 9 && currentFrame instanceof Spare) {
                actualFrameScore += currentFrame.countTotal() + classifiedScores[i + 1].getFirstScore();
            } else if (i < 9 && currentFrame instanceof Strike) {
                actualFrameScore += currentFrame.countTotal();
                if (i + 1 < 10) {
                    actualFrameScore += classifiedScores[i + 1].countTotal();
                    if (classifiedScores[i + 1] instanceof Strike && i + 2 < 10) {
                        actualFrameScore += classifiedScores[i + 2].getFirstScore();
                    }
                }
            } else {
                actualFrameScore += currentFrame.countTotal();
            }
            frameScores[i] = actualFrameScore;
        }
        return frameScores;
    }

    private static int getTotalPointsInGame(int[] frameScores) {
        return Arrays.stream(frameScores).sum();
    }
}