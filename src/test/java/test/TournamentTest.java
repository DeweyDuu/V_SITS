package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import game.*;
import participants.*;
import observer.*;
import tournament.*;

public class TournamentTest {

    @Test
    public void testRoundRobinRun() {
        Game game = new IteratedPrisonersDilemma(1); 
        GameObserver observer = new GameObserver() {
            @Override public void onMoveMade(MoveEvent e) {}
            @Override public void onGameOver(GameResult e) {}
            @Override public void onTournamentOver(TournamentResult e) {}
        };
        game.addObserver(observer);

        Participant p1 = new AlwaysCooperate();
        Participant p2 = new AlwaysDefect();
        Participant p3 = new TitForTat();
        assertEquals("Tit For Tat", p3.getName());
        p3.reset();
        List<Participant> bots = Arrays.asList(p1, p2, p3);
        TournamentFormat tournament = new RoundRobin();
        TournamentResult result = tournament.run(bots, game);

        List<String> rankings = result.getRankings();
        assertNotNull(rankings);
        assertEquals(3, rankings.size());

        assertEquals(3, result.getScore(p1.getName()));
        assertEquals(10, result.getScore(p2.getName()));
        assertEquals(3, result.getScore(p3.getName()));
        // check sorting
        assertEquals(p2.getName(), rankings.get(0));
        // check player not in the game(default 0)
        assertEquals(0, result.getScore("outside player"));
    }
    
}