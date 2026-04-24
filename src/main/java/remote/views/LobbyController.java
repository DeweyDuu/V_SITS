package remote.views;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import remote.server.NetworkedTournament;

import java.util.ArrayList;
import java.util.List;

public class LobbyController {

    ViewerModelInterface model;
    private List<NetworkedTournament> tournaments;

    @FXML private ListView<String> tournamentListView;

    public void setModel(ViewerModelInterface newModel) {
        model = newModel;
        refresh();
    }

    @FXML
    void refresh() {
        tournaments = model.fetchTournaments();
        List<String> names = new ArrayList<>();
        for (NetworkedTournament t : tournaments) {
            names.add(t.getName() + " [" + t.getStatus() + "]");
        }
        tournamentListView.setItems(FXCollections.observableArrayList(names));
    }

    @FXML
    void watchSelected(ActionEvent event) {
        int idx = tournamentListView.getSelectionModel().getSelectedIndex();
        if (idx >= 0) {
            NetworkedTournament selected = tournaments.get(idx);
            model.showMoves(selected.getId());
        }
    }

    @FXML
    void onStart(ActionEvent event) {
        int idx = tournamentListView.getSelectionModel().getSelectedIndex();
        if (idx >= 0) {
            NetworkedTournament selected = tournaments.get(idx);
            model.startTournament(selected.getId());
            model.showMoves(selected.getId());
        }
    }
}
