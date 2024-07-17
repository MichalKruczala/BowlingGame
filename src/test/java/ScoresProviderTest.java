import model.Score;
import org.junit.Assert;
import org.junit.Test;
import providers.ScoresProvider;

public class ScoresProviderTest {
    @Test
    public void shouldGetRandomPairOfScores() {
        ScoresProvider scoresProvider = new ScoresProvider();
        Assert.assertNotNull(scoresProvider.getRandomPairOfScores());
    }

    @Test
    public void shouldGetRandomPairOfScoresIn10Total() {
        ScoresProvider scoresProvider = new ScoresProvider();
        Score scores = scoresProvider.getRandomPairOfScores();
        for (int i = 0; i < 20; i++) {
            Assert.assertTrue(scores.getFirstScore() + scores.getSecondScore() <= 10);
        }
    }

    @Test
    public void shouldGetRandomBonusScoreInRange0To10() {
        ScoresProvider scoresProvider = new ScoresProvider();
        int bonusScore = scoresProvider.getRandomBonusScore();
        Assert.assertTrue(bonusScore >= 0 && bonusScore <= 10);

    }

}



