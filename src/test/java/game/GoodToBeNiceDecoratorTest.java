package game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import participants.AlwaysCooperate;
import participants.AlwaysDefect;

public class GoodToBeNiceDecoratorTest {
	
    @Test
    public void testCoBonusApplied() {
        Game game = new GoodToBeNiceDecorator(new IteratedPrisonersDilemma(10), 5, 10);
        GameResult result = game.play(new AlwaysCooperate(), new AlwaysDefect());
        assertEquals(10, result.getTotalScoreP1());
        assertEquals(50, result.getTotalScoreP2());
        assertEquals("Always Defect", result.getWinner());
    }
    
    
    @Test
    public void testNoBonusNotMet() {
        Game game = new GoodToBeNiceDecorator(new IteratedPrisonersDilemma(10), 15, 10);
        GameResult result = game.play(new AlwaysCooperate(), new AlwaysCooperate());
        assertEquals(30, result.getTotalScoreP1());
        assertEquals(30, result.getTotalScoreP2());
    }
    
    
    @Test
    public void testWinnerdAfterBonus() {
        Game game = new GoodToBeNiceDecorator(new IteratedPrisonersDilemma(1), 1, 20);
        GameResult result = game.play(new AlwaysDefect(), new AlwaysCooperate());
        assertEquals(5, result.getTotalScoreP1());
        assertEquals(20, result.getTotalScoreP2());
        assertEquals("Always Cooperate", result.getWinner());
    }
}
