package remote.views;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import remote.dto.RoundResultDTO;

import java.util.ArrayList;
import java.util.List;

public class LiveGameController {

    ViewerModelInterface model;
    private String tournamentId;
    private Thread updateThread;
    private volatile boolean running = false;

    @FXML
    private ListView<String> movesListView;
    @FXML 
    private Label tournamentLabel;
    
    public void setModel(ViewerModelInterface newModel) {
        model = newModel;
    }
    
    public void setTournamentId(String id) {
        this.tournamentId = id;
        tournamentLabel.setText("Tournament: " + id);
        startUpdating();
    }
    
    private void startUpdating() {
        running = true;
        // without a separate thread, the app would hang waiting for the server and the user can't do anything
        updateThread = new Thread(() -> {
            while (running) {
                List<RoundResultDTO> moves = model.getMoves(tournamentId);
                List<String> display = new ArrayList<>();
                for (RoundResultDTO r : moves) {
                    display.add("P1: " + r.actionP1 + " | P2: " + r.actionP2 +
                                " | Score: " + r.scoreP1 + "-" + r.scoreP2);
                }
                Platform.runLater(() ->
                    movesListView.setItems(FXCollections.observableArrayList(display))
                );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        updateThread.start();
    }

    @FXML
    void onBack(ActionEvent event) {
    	// two threads share this variable; volatile makes sure they always see the same value
        running = false;
        // interrupt wakes the thread out of sleep so it stops immediately
        if (updateThread != null) updateThread.interrupt();
        model.showTournamentList();
    }
}
