import model.*;
import providers.ScoresProvider;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        ScoresProvider scoresProvider = new ScoresProvider();


        Score[] scoreArray = new Score[10];
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
            }
            scoreArray[i] = scoresProvider.getRandomPairOfScores();
        }
        // to jest dobrze

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
                } else if (score.countTotal() == 10) {
                    namedScores.add(new Spare(score.getFirstScore(), score.getSecondScore()));
                } else {
                    namedScores.add(new NonBonusScore(score.getFirstScore(), score.getSecondScore(), false));

                }
            }
        }
        // to  jest dobrze

        Countable[] objects = namedScores.toArray(new Countable[0]);

        int[] frameScore = new int[10];
        for (int i = 0; i < 10; i++) {
            int actualFrameScore = 0;
            frameScore[i] = actualFrameScore;

            Countable actualObject = objects[i];

            if (i == 9 && actualObject instanceof NonBonusScore && ((NonBonusScore) actualObject).hasExtraRoll()) {
                actualFrameScore += ((NonBonusScore) actualObject).countTotalWithExtraRoll();
            } else if (i == 9 && actualObject instanceof NonBonusScore && !((NonBonusScore) actualObject).hasExtraRoll()) {
                actualFrameScore += actualObject.countTotal();
            }
            // ostatni frame


            if (i < 9 && actualObject instanceof Spare) {                      //  jedna następna piłka
                actualFrameScore += actualObject.countTotal() + objects[i + 1].getFirstScore();
            }


            if (i < 9 && actualObject instanceof Strike) {                 //   jeśli jest strajkiem
                actualFrameScore += actualObject.countTotal();
                if (objects[i + 1] instanceof Strike) {                // i następny też
                    actualFrameScore += objects[i + 1].countTotal();
                    if (i + 2 < 9) {
                        Countable secondNextObject = objects[i + 2];               //TODO sprawdzić stiki w końcowych frameach
                        actualFrameScore += secondNextObject.getFirstScore();
                    }
                } else {
                    actualFrameScore += objects[i + 1].countTotal();
                }


            } else if (actualObject instanceof NonBonusScore) {
               // actualFrameScore += object.

            }

        }


    }
}