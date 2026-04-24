package game;

public enum PrisonerAction implements Action {
    COOPERATE, DEFECT; 

    @Override
    public String getLabel() { 
        return name();
    }
}