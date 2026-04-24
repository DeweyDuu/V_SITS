package tournament;

import game.Game;
import game.GameResult;
import participants.Participant;
import observer.GameObserver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundRobin implements TournamentFormat {
    
    @Override
    public TournamentResult run(List<Participant> participants, Game game) { 
        Map<String, Integer> scores = new HashMap<>();
        for (int i = 0; i < participants.size(); i++) {
            Participant p = participants.get(i);
            scores.put(p.getName(), 0);
        } 
        for (int i = 0; i < participants.size(); i++) {
            for (int j = i + 1; j < participants.size(); j++) {
                Participant p1 = participants.get(i);
                Participant p2 = participants.get(j); 
                GameResult result = game.play(p1, p2); 
                int currentP1Score = scores.get(result.getP1Name());
                scores.put(result.getP1Name(), currentP1Score + result.getTotalScoreP1());
                
                int currentP2Score = scores.get(result.getP2Name());
                scores.put(result.getP2Name(), currentP2Score + result.getTotalScoreP2());
                
                
            }
        }
        TournamentResult finalResult = new TournamentResult(scores);
        
        // Notify the loggers that the tournament has concluded 
        for (int i = 0; i < game.getObservers().size(); i++) {
            GameObserver obs = game.getObservers().get(i);
            obs.onTournamentOver(finalResult);
        }
        return finalResult;
    }
}