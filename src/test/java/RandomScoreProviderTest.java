import model.Score;
import org.junit.Assert;
import org.junit.Test;
import providers.ScoresProvider;

public class RandomScoreProviderTest {
    @Test
    public void shouldGetRandomPairOfScores() {
        ScoresProvider randomScoreProvider = new ScoresProvider();
        Assert.assertNotNull(randomScoreProvider.getRandomPairOfScores());
    }

    @Test
    public void shouldGetRandomPairOfScoresIn10Total() {
        ScoresProvider randomScoreProvider = new ScoresProvider();
        Score scores = randomScoreProvider.getRandomPairOfScores();
        Assert.assertTrue(scores.getFirstScore() + scores.getSecondScore() <= 10);
    }
}



