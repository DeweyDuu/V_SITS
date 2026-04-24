package game;

import java.util.ArrayList;
import java.util.List;

public class GameHistory {
    private List<RoundResult> rounds; 
    private String p1Name; 
    private String p2Name; 
    
    public GameHistory(String p1Name, String p2Name) {
        this.rounds = new ArrayList<>();
        this.p1Name = p1Name;
        this.p2Name = p2Name;
    }

    public List<RoundResult> getRounds() { return rounds; } 
    public String getP1Name() { return p1Name; } 
    public String getP2Name() { return p2Name; } 
}

