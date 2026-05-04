package game;

public class RacingDecorator extends GameDecorator {

    private int goalScore;

    public RacingDecorator(Game game, int goalScore) {
        super(game);
        this.goalScore = goalScore;
    }

    @Override
    public boolean isOver(GameHistory history) {
        int p1Total = 0, p2Total = 0;
        for (RoundResult r : history.getRounds()) {
            p1Total += r.getScoreP1();
            p2Total += r.getScoreP2();
        }
        if (p1Total >= goalScore || p2Total >= goalScore) return true;
        return game.isOver(history);
    }
}
