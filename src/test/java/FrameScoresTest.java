import model.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameScoresTest {


    @Test
    public void shouldGetExtraScoreInLastFrameIfStrike() {
        int[] wyniki = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 10};
        Score[] frames = Game.getFramesScores(wyniki);
        assertTrue(frames[9].hasExtraScore());
        Assertions.assertEquals(10, frames[9].getFirstScore());
        Assertions.assertEquals(10, frames[9].getThirdScore());
    }

    @Test
    public void shouldGetExtraScoreInLastFrameIfSpare() {
        int[] wyniki = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 10};
        Score[] frames = Game.getFramesScores(wyniki);
        assertTrue(frames[9].hasExtraScore());
        Assertions.assertEquals(10, frames[9].getFirstScore() + frames[9].getSecondScore());
        Assertions.assertEquals(10, frames[9].getThirdScore());
    }

    @Test
    public void shouldNotGetExtraScoreInLastFrameIfIfNonBonusScore() {
        int[] wyniki = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 5, 10};
        Score[] frames = Game.getFramesScores(wyniki);
        Assertions.assertFalse(frames[9].hasExtraScore());
    }


    @Test
    public void shouldGet10FramesArrayIfShorterScoresArray() {
        int[] wyniki = {10, 0};
        Score[] frames4 = Game.getFramesScores(wyniki);
        Assertions.assertEquals(10, frames4.length);
    }

    @Test
    public void shouldGet10FramesArrayIfLongerScoresArray() {
        int[] wyniki = {10, 0, 6, 8, 2, 7, 5, 3, 6, 8, 3, 6, 2, 3, 4, 6, 4, 1, 3};
        Score[] frames4 = Game.getFramesScores(wyniki);
        Assertions.assertEquals(10, frames4.length);
    }
    @Test
    public void should() {
        int[] wyniki = {};
        Score[] frames4 = Game.getFramesScores(wyniki);
        Assertions.assertEquals(10, frames4.length);
    }
}
