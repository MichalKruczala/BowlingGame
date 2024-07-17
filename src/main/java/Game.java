import model.Countable;
import model.Score;
import model.classifiedScores.NonBonusScore;
import model.classifiedScores.Spare;
import model.classifiedScores.Strike;
import providers.ScoresProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    public static void main(String[] args) {

        Score[] scoreArray = getScores();
        Countable[] classifiedScores = getClassifiedScores(scoreArray);
        int[] frameScores = getPointsForEachFrame(classifiedScores);
        int totalPointsInGame = getTotalPointsInGame(frameScores);

        for (Score score : scoreArray) {
            System.out.println(score);
        }
        System.out.println("------------------------------------");
        for (Countable object : classifiedScores) {
            System.out.println(object);
        }
        System.out.println("Points in each frame: " + Arrays.toString(frameScores));
        System.out.println(totalPointsInGame);
    }


    public static Score[] getScores() {
        Score[] scoreArray = new Score[10];
        ScoresProvider scoresProvider = new ScoresProvider();
        for (int i = 0; i < scoreArray.length; i++) {
            if (i == scoreArray.length - 1) {
                Score score = scoresProvider.getRandomPairOfScores();
                if (score.countTotal() == 10 || score.getFirstScore() == 10 || score.getSecondScore() == 10) {
                    Score scoreWithExtraRoll = new Score(
                            score.getFirstScore(),
                            score.getSecondScore(),
                            scoresProvider.getRandomBonusScore(),
                            true);
                    scoreArray[i] = scoreWithExtraRoll;
                } else {
                    Score nonBonusScore = new Score(
                            score.getFirstScore(),
                            score.getSecondScore(),
                            false);
                    scoreArray[i] = nonBonusScore;
                }
            } else {
                scoreArray[i] = scoresProvider.getRandomPairOfScores();
            }
        }
        return scoreArray;
    }

    public static Countable[] getClassifiedScores(Score[] scoreArray) {
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

    public static int[] getPointsForEachFrame(Countable[] classifiedScores) {
        int[] frameScores = new int[10];
        for (int i = 0; i < 10; i++) {
            int actualFrameScore = 0;
            Countable actualObject = classifiedScores[i];

            if (i == 9 && actualObject instanceof NonBonusScore && ((NonBonusScore) actualObject).hasExtraRoll()) {
                actualFrameScore += ((NonBonusScore) actualObject).countTotalWithExtraRoll();
            } else if (i == 9 && actualObject instanceof NonBonusScore && !((NonBonusScore) actualObject).hasExtraRoll()) {
                actualFrameScore += actualObject.countTotal();
            } else if (i < 9 && actualObject instanceof Spare) {
                actualFrameScore += actualObject.countTotal();
                if (i + 1 < 10) {
                    actualFrameScore += classifiedScores[i + 1].getFirstScore();
                }
            } else if (i < 9 && actualObject instanceof Strike) {
                actualFrameScore += actualObject.countTotal();
                if (i + 1 < 10) {
                    actualFrameScore += classifiedScores[i + 1].countTotal();
                    if (classifiedScores[i + 1] instanceof Strike && i + 2 < 10) {
                        actualFrameScore += classifiedScores[i + 2].getFirstScore();
                    }
                }
            } else {
                actualFrameScore += actualObject.countTotal();
            }
            frameScores[i] = actualFrameScore;
        }
        return frameScores;
    }

    private static int getTotalPointsInGame(int[] frameScores) {
        return Arrays.stream(frameScores).sum();
    }
}
