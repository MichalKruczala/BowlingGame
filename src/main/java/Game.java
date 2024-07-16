import model.*;
import providers.ScoresProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        ScoresProvider scoresProvider = new ScoresProvider();

        Score[] scoreArray = new Score[10];
        for (int i = 0; i < 10; i++) {
            if (i == 9) {
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
                    namedScores.add(new Strike(score.getFirstScore()));
                }
                else if (score.countTotal() == 10) {
                    namedScores.add(new Spare(score.getFirstScore(), score.getSecondScore()));
                } else {
                    namedScores.add(new NonBonusScore(score.getFirstScore(), score.getSecondScore(), false));
                }
            }
        }
        Countable[] objects = namedScores.toArray(new Countable[0]);
        int[] frameScore = new int[10];
        for (int i = 0; i < 10; i++) {
            int actualFrameScore = 0;
            Countable actualObject = objects[i];

            if (i == 9 && actualObject instanceof NonBonusScore && ((NonBonusScore) actualObject).hasExtraRoll()) {
                actualFrameScore += ((NonBonusScore) actualObject).countTotalWithExtraRoll();
            } else if (i == 9 && actualObject instanceof NonBonusScore && !((NonBonusScore) actualObject).hasExtraRoll()) {
                actualFrameScore += actualObject.countTotal();
            } else if (i < 9 && actualObject instanceof Spare) {
                actualFrameScore += actualObject.countTotal();
                if (i + 1 < 10) {
                    actualFrameScore += objects[i + 1].getFirstScore();
                }
            } else if (i < 9 && actualObject instanceof Strike) {
                actualFrameScore += actualObject.countTotal();
                if (i + 1 < 10) {
                    actualFrameScore += objects[i + 1].countTotal();
                    if (objects[i + 1] instanceof Strike && i + 2 < 10) {
                        actualFrameScore += objects[i + 2].getFirstScore();
                    }
                }
            } else {
                actualFrameScore += actualObject.countTotal();
            }

            frameScore[i] = actualFrameScore;
        }
        for (Countable object : objects) {
            System.out.println(object);

        }
        System.out.println(Arrays.toString(frameScore));
        System.out.println(Arrays.stream(frameScore).sum());
    }
}
