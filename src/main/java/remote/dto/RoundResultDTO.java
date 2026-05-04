package remote.dto;

public class RoundResultDTO {
    public String p1Name;
    public String p2Name;
    public String actionP1;
    public String actionP2;
    public int scoreP1;
    public int scoreP2;

    public RoundResultDTO() {}

    public RoundResultDTO(String p1Name, String p2Name, String actionP1, String actionP2, int scoreP1, int scoreP2) {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
        this.actionP1 = actionP1;
        this.actionP2 = actionP2;
        this.scoreP1 = scoreP1;
        this.scoreP2 = scoreP2;
    }
}