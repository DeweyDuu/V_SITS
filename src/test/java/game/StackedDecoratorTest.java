package game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import participants.AlwaysCooperate;

public class StackedDecoratorTest {
	
    @Test
    public void testStackedDecorators() {
        Game game = new BonusRoundDecorator(
                        new GoodToBeNiceDecorator(
                            new IteratedPrisonersDilemma(4), 2, 10), 2);
        GameResult result = game.play(new AlwaysCooperate(), new AlwaysCooperate());
        assertEquals(28, result.getTotalScoreP1());
        assertEquals(28, result.getTotalScoreP2());
        assertEquals("Tie", result.getWinner());
    }
}
