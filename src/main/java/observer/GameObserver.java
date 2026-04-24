package observer;

import game.GameResult;
import tournament.TournamentResult;

// The Observer Pattern interface
public interface GameObserver {
    void onMoveMade(MoveEvent e); 
    void onGameOver(GameResult e); 
    void onTournamentOver(TournamentResult e);
}