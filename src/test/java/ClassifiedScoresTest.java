import model.Countable;
import model.Score;
import model.classifiedScores.NonBonusScore;
import model.classifiedScores.Spare;
import model.classifiedScores.Strike;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ClassifiedScoresTest {
    @Test
    public void shouldThrowExceptionWhenNullScoreArray() {
        Score[] scoreArray = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> Game.getClassifiedScores(scoreArray));
    }


    @Test
    public void ShouldThrowExceptionWhenNullElementInScoreArray() {

        Score[] scoreArray = {
                new Score(5, 3, false),
                null,
                new Score(5, 3, false),
        };
        Assertions.assertThrows(IllegalArgumentException.class, () -> Game.getClassifiedScores(scoreArray));
    }

    @Test
    public void shouldGetTheSameSizeScoresArrayAndClassifiedScoresArray() {
        Score[] scoreArray = {
                new Score(4, 5, false),
                new Score(3, 6, false),
                new Score(7, 2, false)
        };
        Countable[] result = Game.getClassifiedScores(scoreArray);
        Assertions.assertEquals(scoreArray.length, result.length);
    }

    @Test
    public void shouldClassifyFrameAsStrikeWhenAnyRollIs10() {
        Score[] scoreArray = {
                new Score(10, 0, false),
                new Score(0, 10, false)
        };
        Countable[] result = Game.getClassifiedScores(scoreArray);
        Assertions.assertTrue(result[0] instanceof Strike);
        Assertions.assertTrue(result[1] instanceof Strike);
    }

    @Test
    public void shouldClassifyFrameAsSpareWhenTotal10PointsIn2Rolls() {
        Score[] scoreArray = {
                new Score(5, 5, false),
                new Score(3, 7, false)
        };
        Countable[] result = Game.getClassifiedScores(scoreArray);
        Assertions.assertEquals(2, result.length);
        Assertions.assertTrue(result[0] instanceof Spare);
        Assertions.assertTrue(result[1] instanceof Spare);
    }

    @Test
    public void shouldClassifyAsNonBonusScoreWithExtraRoll() {
        Score[] scoreArray = {
                new Score(10, 0, 5, true),
                new Score(8, 2, 10, true)
        };
        Countable[] result = Game.getClassifiedScores(scoreArray);

        Assertions.assertTrue(result[0] instanceof NonBonusScore);
        Assertions.assertEquals(15, ((NonBonusScore) result[0]).countTotalWithExtraRoll());
        Assertions.assertTrue(result[1] instanceof NonBonusScore);
        Assertions.assertEquals(20, ((NonBonusScore) result[1]).countTotalWithExtraRoll());
    }
}
