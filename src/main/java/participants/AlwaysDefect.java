package participants;

import game.Action;
import game.GameHistory;
import game.PrisonerAction;

public class AlwaysDefect implements Participant {
    
    @Override 
    public String getName() { 
        return "Always Defect"; 
    } 
    
    @Override 
    public void reset() {
    } 

    @Override
    public Action chooseAction(GameHistory h) { 
        return PrisonerAction.DEFECT;
    }
}