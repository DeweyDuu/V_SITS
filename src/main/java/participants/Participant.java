package participants;
import game.Action;
import game.GameHistory;

public interface Participant {
	String getName(); 
    Action chooseAction(GameHistory h); 
    void reset();
}
