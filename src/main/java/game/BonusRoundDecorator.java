package game;

import participants.Participant;

public class BonusRoundDecorator extends GameDecorator {
	
	
    private int bonusCheck;
    public BonusRoundDecorator(Game game, int bonusCheck) {
        super(game);
        this.bonusCheck = bonusCheck;
    }
    
    @Override
    public RoundResult doRound(Participant p1, Participant p2, GameHistory history) {
        RoundResult original = game.doRound(p1, p2, history);
        if (original.getRoundNumber() % bonusCheck == 0) {
            return new RoundResult(
                original.getActionP1(),
                original.getActionP2(),
                original.getScoreP1() * 2,
                original.getScoreP2() * 2,
                original.getRoundNumber()
            );
        }
        return original;
    }
}
