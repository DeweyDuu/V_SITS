package game;

public class GoodToBeNiceDecorator extends GameDecorator {

    private int cooperateTimes;
    private int bonusPoints;

    public GoodToBeNiceDecorator(Game game, int cooperateTimes, int bonusPoints) {
        super(game);
        this.cooperateTimes = cooperateTimes;
        this.bonusPoints = bonusPoints;
    }
    
    
    @Override
    public GameResult computeFinalResult(GameHistory history) {
        GameResult base = game.computeFinalResult(history);
        int p1Coops = 0;
        int p2Coops = 0;
        for (RoundResult r : history.getRounds()) {
            if (r.getActionP1() == PrisonerAction.COOPERATE) {
                p1Coops++;
            }
            if (r.getActionP2() == PrisonerAction.COOPERATE) {
                p2Coops++;
            }
        }
        int p1Total = base.getTotalScoreP1();
        if (p1Coops >= cooperateTimes) {
            p1Total = p1Total + bonusPoints;
        }

        int p2Total = base.getTotalScoreP2();
        if (p2Coops >= cooperateTimes) {
            p2Total = p2Total + bonusPoints;
        }
        String winner = "Tie";
        if (p1Total > p2Total) {
            winner = base.getP1Name();
        } else if (p2Total > p1Total) {
            winner = base.getP2Name();
        }
        
        return new GameResult(base.getP1Name(), base.getP2Name(), p1Total, p2Total, winner);
    }
}
