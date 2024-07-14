package providers;

import lombok.NoArgsConstructor;
import lombok.ToString;
import model.Score;

import java.util.Objects;
import java.util.Random;

@NoArgsConstructor
@ToString
public class ScoresProvider {
    public Score getRandomScores() {
        Random random = new Random();
        int firstScore = random.nextInt(11);
        int secondScore = 0;
        if (firstScore == 0) {
            secondScore = random.nextInt(11);
        } else if (firstScore != 10) {
            secondScore = random.nextInt(10 - firstScore);
        }
        return new Score(firstScore, secondScore);
    }
}

