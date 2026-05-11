package game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import participants.AlwaysCooperate;
import participants.AlwaysDefect;

public class RacingDecoratorTest {
	
    @Test
    public void testEndsGoalReached() {
        Game game = new RacingDecorator(new IteratedPrisonersDilemma(60), 10);
        GameResult result = game.play(new AlwaysDefect(), new AlwaysCooperate());
        assertEquals(10, result.getTotalScoreP1());
        assertEquals(0, result.getTotalScoreP2());
        assertEquals("Always Defect", result.getWinner());
    }
    
    @Test
    public void testNormalGoalNotReached() {
        Game game = new RacingDecorator(new IteratedPrisonersDilemma(4), 1000);
        GameResult result = game.play(new AlwaysCooperate(), new AlwaysCooperate());
        assertEquals(12, result.getTotalScoreP1());
        assertEquals(12, result.getTotalScoreP2());
    }
    @Test
    public void testP2HitsGoalFirst() {
        Game game = new RacingDecorator(new IteratedPrisonersDilemma(60), 10);
        GameResult result = game.play(new AlwaysCooperate(), new AlwaysDefect());
        assertEquals(0, result.getTotalScoreP1());
        assertEquals(10, result.getTotalScoreP2());
        assertEquals("Always Defect", result.getWinner());
    }
}
