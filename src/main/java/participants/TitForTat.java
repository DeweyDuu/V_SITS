package participants;

import game.Action;
import game.GameHistory;
import game.PrisonerAction;
import game.RoundResult;

public class TitForTat implements Participant {
    
    @Override 
    public String getName() { 
        return "Tit For Tat"; 
    } 
    
    @Override 
    public void reset() {
 
    } 

    @Override
    public Action chooseAction(GameHistory h) { 
        if (h.getRounds().isEmpty()) {
            
            return PrisonerAction.COOPERATE; 
        }
        
        // Find out how many rounds have been played so far
        int totalRoundsPlayed = h.getRounds().size();
        RoundResult lastRound = h.getRounds().get(totalRoundsPlayed - 1);
        // Mirror the opponent's last move 
        return lastRound.getActionP2(); 
    }
}