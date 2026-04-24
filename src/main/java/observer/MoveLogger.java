package observer;

import game.GameHistory;
import game.GameResult;
import game.RoundResult;
import tournament.TournamentResult;

import java.io.FileWriter;
import java.io.IOException;

public class MoveLogger implements GameObserver {
    private String filePath; 
    private FileWriter writer; 

    public MoveLogger(String filePath) {
        this.filePath = filePath;
        try {
            // The "true" parameter tells Java to append to the file, not overwrite
            this.writer = new FileWriter(filePath, true);
        } catch (IOException e) {
            System.out.println("Failed to open the move logger file.");
        }
    }

    @Override
    public void onMoveMade(MoveEvent e) {
        try {
            RoundResult r = e.getRoundResult();
            GameHistory h = e.getGameHistory();
            
            String logLine = "Round " + r.getRoundNumber() + " | " + 
                             h.getP1Name() + " played " + r.getActionP1().getLabel() + " | " +
                             h.getP2Name() + " played " + r.getActionP2().getLabel() + "\n";
                             
            writer.write(logLine);
            
        } catch (IOException ex) {
            System.out.println("Failed to write the move to the file.");
        }
    }

    @Override
    public void onGameOver(GameResult e) {
    }

    @Override
    public void onTournamentOver(TournamentResult e) { 
        try {
            writer.write("--- TOURNAMENT FINISH ---\n\n");
            writer.close();
        } catch (IOException ex) {
            System.out.println("Failed to close the move logger file.");
        }
    }
}