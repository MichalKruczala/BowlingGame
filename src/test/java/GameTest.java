import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    @Test
    public void shouldGet10ElementArrayOfScores() {
        Assert.assertEquals(10, Game.getScores().length);
    }

    //TODO Dopisać więcej testów

}
