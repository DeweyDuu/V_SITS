package game;

public class GameResult {
    private String p1Name; 
    private String p2Name; 
    private int totalScoreP1; 
    private int totalScoreP2; 
    private String winner; 

    public GameResult(String p1Name, String p2Name, int totalScoreP1, int totalScoreP2, String winner) {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
        this.totalScoreP1 = totalScoreP1;
        this.totalScoreP2 = totalScoreP2;
        this.winner = winner;
    }

    public String getP1Name() { return p1Name; }
    public String getP2Name() { return p2Name; }
    public int getTotalScoreP1() { return totalScoreP1; }
    public int getTotalScoreP2() { return totalScoreP2; }
    public String getWinner() { return winner; }
}

