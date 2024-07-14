import model.*;
import providers.ScoresProvider;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        ScoresProvider scoresProvider = new ScoresProvider();


        Score[] scoreArray = new Score[10];
        for (int i = 0; i < scoreArray.length; i++) {
            scoreArray[i] = scoresProvider.getRandomScores();
        }
        //Wypełnianie randomowymi wynikami


        List<Countable> cos = new ArrayList<>();
        for (Score score : scoreArray) {
            if (score.getFirstScore() == 10 || score.getSecondScore() == 10) {
                cos.add(new Strike(score.getFirstScore()));
            }
            if (score.countTotal() == 10) {
                cos.add(new Spare(score.getFirstScore(), score.getSecondScore()));
            } else {
                cos.add(new NonBonusScore(score.getFirstScore(), score.getSecondScore()));
            }
        }
        System.out.println(cos);

        Countable[] objects = (Countable[]) cos.toArray();

        int[] frameScore = new int[10];
        for (int i = 0; i < 10; i++) {
            int actualFrameScore = frameScore[i];

            Countable actualObject = objects[i];
            Countable nextObject = objects[i + 1];
            Countable secondNextObject = objects[i + 2];

            if (actualObject instanceof Spare) {                             //  jedna następna piłka
                actualFrameScore += (actualObject).countTotal() + nextObject.getFirstScore();
            }
            if (actualObject instanceof Strike) {                       // dwie następne piłki
                actualFrameScore += actualObject.countTotal();

                if (nextObject instanceof Strike) {
                    //TODO jeżeli jesteśmy w 9 framie to nie ma secondNextObject! ale za to w 10 frame(nextObject) moga być
                    // 3x Strike/ Spare + strike/ non bonus score + zwykłe punkty


                    actualFrameScore += nextObject.countTotal() + secondNextObject.getFirstScore();
                } else {
                    actualFrameScore += nextObject.countTotal();
                }
            }
            //TODO  ograniczyć DOkończyć  że w 10 frame mamy inne zqsady
            // i jest dotatkowy rzut jeżeli w nim zdobędziemy skrikea lub sparea


        }


    }


}

