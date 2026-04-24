package game;

import participants.Participant;
import observer.GameObserver;
import observer.MoveEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    
    private List<GameObserver> observers = new ArrayList<>();

  
    public GameResult play(Participant p1, Participant p2) {
        p1.reset();
        p2.reset();
        
        GameHistory history = new GameHistory(p1.getName(), p2.getName());
        
        while (isOver(history) == false) {
            RoundResult roundResult = doRound(p1, p2, history);
            history.getRounds().add(roundResult); 
            
            MoveEvent currentMove = new MoveEvent(roundResult, history);
            notifyMoveMade(currentMove);
        }
        
        GameResult finalResult = computeFinalResult(history);
        notifyGameOver(finalResult);
        
        return finalResult;
    }

    public abstract RoundResult doRound(Participant p1, Participant p2, GameHistory history); 
    public abstract boolean isOver(GameHistory history);
    public abstract GameResult computeFinalResult(GameHistory history); 

    // --- Observer Notification Methods ---
    
    public void addObserver(GameObserver o) { 
    	observers.add(o);
    }

    public void removeObserver(GameObserver o) { 
        observers.remove(o);
    }

    private void notifyMoveMade(MoveEvent e) { 
        for (GameObserver obs : observers) {
            obs.onMoveMade(e);
        }
    }

    private void notifyGameOver(GameResult e) {
        for (GameObserver obs : observers) {
            obs.onGameOver(e);
        }
    }
    
    // Helper to let the Tournament notify loggers when it's done
    public List<GameObserver> getObservers() {
        return observers;
    }
}