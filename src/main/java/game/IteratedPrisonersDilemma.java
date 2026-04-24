package game;

import participants.Participant;

public class IteratedPrisonersDilemma extends Game {
    private int maxRounds; 

    public IteratedPrisonersDilemma(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    @Override
    public RoundResult doRound(Participant p1, Participant p2, GameHistory history) { 
    	// 1. Ask both bots what they want to do
        Action p1Choice = p1.chooseAction(history);
        Action p2Choice = p2.chooseAction(history);
        
        // 2. Figure out the points based on their choices
        int[] payoffs = getPayoff(p1Choice, p2Choice); 
        int p1Score = payoffs[0];
        int p2Score = payoffs[1];
        
        // 3. Calculate which round we are currently on
        int currentRoundNumber = history.getRounds().size() + 1;
        
        // 4. Return the fully packaged result back to the Game Loop
        return new RoundResult(p1Choice, p2Choice, p1Score, p2Score, currentRoundNumber);
    }

    @Override
    public boolean isOver(GameHistory history) { 
        // The Template Method loop will keep asking this method if it should stop.
        // We stop the game if we hit the maximum number of rounds.
        if (history.getRounds().size() >= maxRounds) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public GameResult computeFinalResult(GameHistory history) { 
        int totalScore1 = 0;
        int totalScore2 = 0;
        
        // Loop through every round in the history to add up the points
        for (int i = 0; i < history.getRounds().size(); i++) {
            RoundResult r = history.getRounds().get(i);
            totalScore1 = totalScore1 + r.getScoreP1();
            totalScore2 = totalScore2 + r.getScoreP2();
        }
        
       
        String winnerName = "Tie";
        if (totalScore1 > totalScore2) {
            winnerName = history.getP1Name();
        } else if (totalScore2 > totalScore1) {
            winnerName = history.getP2Name();
        }

        return new GameResult(history.getP1Name(), history.getP2Name(), totalScore1, totalScore2, winnerName);
    }

    private int[] getPayoff(Action a1, Action a2) { 
        if (a1 == PrisonerAction.COOPERATE && a2 == PrisonerAction.COOPERATE) {
            return new int[]{3, 3}; 
        }
        if (a1 == PrisonerAction.COOPERATE && a2 == PrisonerAction.DEFECT) {
            return new int[]{0, 5}; 
        }
        if (a1 == PrisonerAction.DEFECT && a2 == PrisonerAction.COOPERATE) {
            return new int[]{5, 0}; 
        }
        if (a1 == PrisonerAction.DEFECT && a2 == PrisonerAction.DEFECT) {
            return new int[]{1, 1}; 
        }
        
        throw new IllegalArgumentException("wrong move " + a1.getLabel() + ", " + a2.getLabel()); 
    }
}