package observer;

import game.GameHistory;
import game.RoundResult;

// Packages the round data together so the Game can hand it to the Loggers
public class MoveEvent {
    private RoundResult roundResult; 
    private GameHistory gameHistory;

    public MoveEvent(RoundResult roundResult, GameHistory gameHistory) {
        this.roundResult = roundResult;
        this.gameHistory = gameHistory;
    }

    public RoundResult getRoundResult() { return roundResult; }
    public GameHistory getGameHistory() { return gameHistory; }
}