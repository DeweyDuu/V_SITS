package game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import participants.AlwaysCooperate;

public class BonusRoundDecoratorTest {
	
    @Test
    public void testBonusRoundDoublesScores() {
        Game game = new BonusRoundDecorator(new IteratedPrisonersDilemma(4), 2);
        GameResult result = game.play(new AlwaysCooperate(), new AlwaysCooperate());
        assertEquals(18, result.getTotalScoreP1());
        assertEquals(18, result.getTotalScoreP2());
    }
    
    @Test
    public void testNonBonusRoundUnchanged() {
        Game game = new BonusRoundDecorator(new IteratedPrisonersDilemma(4), 10);
        GameResult result = game.play(new AlwaysCooperate(), new AlwaysCooperate());
        assertEquals(12, result.getTotalScoreP1());
        assertEquals(12, result.getTotalScoreP2());
    }
}
