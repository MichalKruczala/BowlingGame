package providers;

import lombok.NoArgsConstructor;
import lombok.ToString;
import model.Score;

import java.util.Random;

@NoArgsConstructor
@ToString
public class ScoresProvider {

    public Score getRandomPairOfScores() {
        Random random = new Random();
        int firstScore = random.nextInt(11);
        int secondScore ;
        if (firstScore == 10) {
            secondScore = 0;
        } else {
            secondScore = random.nextInt(11 - firstScore);
        }
        return new Score(firstScore, secondScore,false);
    }

    public int getRandomBonusScore() {
        return new Random().nextInt(11);
    }
}