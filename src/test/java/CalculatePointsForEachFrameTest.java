import model.Countable;
import model.classifiedScores.NonBonusScore;
import model.classifiedScores.Spare;
import model.classifiedScores.Strike;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatePointsForEachFrameTest {
    @Test
    public void shouldThrowExceptionWhenNullArray() {
        Countable[] nullArray = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> Game.getCalculatedPointsForEachFrame(nullArray));
    }

    @Test
    public void shouldThrowExceptionWhenNullInArray() {
        Countable[] classifiedScores = {
                new NonBonusScore(3, 4, false),
                new NonBonusScore(2, 6, false),
                null,
                new NonBonusScore(4, 5, false),
                new NonBonusScore(6, 3, false),
                new NonBonusScore(5, 2, false),
                new NonBonusScore(0, 0, false),
                new NonBonusScore(4, 5, false),
                new NonBonusScore(3, 6, false),
                new NonBonusScore(7, 2, false)};
        Assertions.assertThrows(IllegalArgumentException.class, () -> Game.getCalculatedPointsForEachFrame(classifiedScores));
    }
    @Test
    public void shouldThrowExceptionWhenArrayTooBig() {
        Countable[] classifiedScores = {
                new NonBonusScore(3, 4, false),
                new NonBonusScore(2, 6, false),
                new NonBonusScore(2, 6, false),
                new NonBonusScore(2, 6, false),
                new NonBonusScore(2, 6, false),
                new NonBonusScore(2, 6, false),
                new NonBonusScore(2, 6, false),
                new NonBonusScore(2, 6, false),
                new NonBonusScore(2, 6, false),
                new NonBonusScore(4, 5, false),
                new NonBonusScore(6, 3, false),
                new NonBonusScore(5, 2, false),
                new NonBonusScore(0, 0, false),
                new NonBonusScore(4, 5, false),
                new NonBonusScore(3, 6, false),
                new NonBonusScore(7, 2, false)};
        Assertions.assertThrows(IllegalArgumentException.class, () -> Game.getCalculatedPointsForEachFrame(classifiedScores));
    }



    @Test
    public void shouldThrowExceptionWhenArrayIsTooSmall() {
        Countable[] classifiedScores = {
                new NonBonusScore(3, 4, false),
                new NonBonusScore(2, 6, false),
                new NonBonusScore(7, 1, false)};
        Assertions.assertThrows(IllegalArgumentException.class, () -> Game.getCalculatedPointsForEachFrame(classifiedScores));
    }

    @Test
    public void testAllNonBonusFrames() {
        Countable[] classifiedScores = {
                new NonBonusScore(3, 4, false),
                new NonBonusScore(2, 6, false),
                new NonBonusScore(7, 1, false),
                new NonBonusScore(4, 5, false),
                new NonBonusScore(6, 3, false),
                new NonBonusScore(5, 2, false),
                new NonBonusScore(0, 0, false),
                new NonBonusScore(4, 5, false),
                new NonBonusScore(3, 6, false),
                new NonBonusScore(7, 2, false)
        };
        int[] expectedScores = {7, 8, 8, 9, 9, 7, 0, 9, 9, 9};
        int[] actualScores = Game.getCalculatedPointsForEachFrame(classifiedScores);

        Assertions.assertArrayEquals(expectedScores, actualScores, "The scores should be calculated correctly for non-bonus frames");
    }

    @Test
    public void testFramesWithSpare() {
        Countable[] classifiedScores = {
                new Spare(5, 5),
                new NonBonusScore(3, 4, false),
                new Spare(6, 4),
                new NonBonusScore(2, 5, false),
                new NonBonusScore(6, 3, false),
                new Spare(7, 3),
                new NonBonusScore(5, 1, false),
                new NonBonusScore(4, 5, false),
                new NonBonusScore(8, 1, false),
                new NonBonusScore(3, 5, false)
        };
        int[] expectedScores = {13, 7, 12, 7, 9, 15, 6, 9, 9, 8};
        int[] actualScores = Game.getCalculatedPointsForEachFrame(classifiedScores);
        Assertions.assertArrayEquals(expectedScores, actualScores, "The scores should be calculated correctly with spare");
    }

    @Test
    public void testFramesWithStrike() {
        Countable[] classifiedScores = {
                new Strike(10),
                new NonBonusScore(3, 4, false),
                new Strike(10),
                new NonBonusScore(5, 2, false),
                new Strike(10),
                new NonBonusScore(4, 5, false),
                new NonBonusScore(8, 1, false),
                new NonBonusScore(3, 6, false),
                new NonBonusScore(2, 7, false),
                new NonBonusScore(6, 3, false)
        };
        int[] expectedScores = {17, 7, 17, 7, 19, 9, 9, 9, 9, 9};
        int[] actualScores = Game.getCalculatedPointsForEachFrame(classifiedScores);
        Assertions.assertArrayEquals(expectedScores, actualScores, "The scores should be calculated correctly with strikes");
    }

    @Test
    public void testFramesWithStrikeAndSpare() {
        Countable[] classifiedScores = {
                new Strike(10),
                new Spare(5, 5),
                new NonBonusScore(4, 3, false),
                new Strike(10),
                new NonBonusScore(6, 2, false),
                new Spare(7, 3),
                new NonBonusScore(8, 1, false),
                new NonBonusScore(5, 4, false),
                new NonBonusScore(3, 6, false),
                new NonBonusScore(2, 7, false)
        };

        int[] expectedScores = {20, 14, 7, 18, 8, 18, 9, 9, 9, 9};
        int[] actualScores = Game.getCalculatedPointsForEachFrame(classifiedScores);

        Assertions.assertArrayEquals(expectedScores, actualScores, "The scores should be calculated correctly with strikes and spares");
    }

    @Test
    public void testLastFrameWithExtraRoll() {
        Countable[] classifiedScores = {
                new NonBonusScore(3, 4, false),
                new NonBonusScore(2, 6, false),
                new NonBonusScore(7, 1, false),
                new NonBonusScore(4, 5, false),
                new NonBonusScore(6, 3, false),
                new NonBonusScore(5, 2, false),
                new NonBonusScore(8, 0, false),
                new NonBonusScore(4, 5, false),
                new NonBonusScore(3, 6, false),
                new NonBonusScore(7, 3, 6, true)
        };
        int[] expectedScores = {7, 8, 8, 9, 9, 7, 8, 9, 9, 16};
        int[] actualScores = Game.getCalculatedPointsForEachFrame(classifiedScores);
        Assertions.assertArrayEquals(expectedScores, actualScores, "The scores should be calculated correctly for the last frame with extra roll");
    }
}
