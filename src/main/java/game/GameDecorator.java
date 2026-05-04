package game;

import participants.Participant;

public abstract class GameDecorator extends Game {

    protected Game game;

    public GameDecorator(Game game) {
        this.game = game;
    }

    @Override
    public RoundResult doRound(Participant p1, Participant p2, GameHistory history) {
        return game.doRound(p1, p2, history);
    }

    @Override
    public boolean isOver(GameHistory history) {
        return game.isOver(history);
    }

    @Override
    public GameResult computeFinalResult(GameHistory history) {
        return game.computeFinalResult(history);
    }
}
