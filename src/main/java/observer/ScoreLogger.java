package observer;

import game.GameResult;
import tournament.TournamentResult;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ScoreLogger implements GameObserver {
    private String filePath; 
    private FileWriter writer; 

    public ScoreLogger(String filePath) {
        this.filePath = filePath;
        try {
            this.writer = new FileWriter(filePath, true);
        } catch (IOException e) {
            System.out.println("Oops! Could not open the score logger file.");
        }
    }

    @Override
    public void onMoveMade(MoveEvent e) { 
        // We only care about final scores, not individual rounds 
    }

    @Override
    public void onGameOver(GameResult e) {
        try {
            // Write the final score of the match 
            String logLine = "Match Over -> " + 
                             e.getP1Name() + ": " + e.getTotalScoreP1() + " points | " +
                             e.getP2Name() + ": " + e.getTotalScoreP2() + " points | " +
                             "Winner: " + e.getWinner() + "\n";
                             
            writer.write(logLine);
            writer.flush();
        } catch (IOException ex) {
            System.out.println("Failed to write the score to the file.");
        }
    }

    @Override
    public void onTournamentOver(TournamentResult e) { 
        try {
            writer.write("\n=== FINAL TOURNAMENT STANDINGS ===\n");
            
            // Get the sorted list of names (highest score to lowest)
            List<String> rankings = e.getRankings();
            
            // Loop through the rankings and print each score
            for (int i = 0; i < rankings.size(); i++) {
                String botName = rankings.get(i);
                int botScore = e.getScore(botName);
                writer.write((i + 1) + ". " + botName + " - " + botScore + " total points\n");
            }
            
            writer.write("==================================\n\n");
            writer.close();
            
        } catch (IOException ex) {
            System.out.println("Failed to close the score logger file.");
        }
    }
}