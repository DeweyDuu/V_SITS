package remote.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import remote.dto.RoundResultDTO;
import remote.server.NetworkedTournament;

import java.io.IOException;
import java.util.List;

public class ViewerModel implements ViewerModelInterface {

    private BorderPane mainview;
    private ServerConnection connection;

    public ViewerModel(BorderPane mainview) {
        this.mainview = mainview;
    }

    @Override
    public void connect(String ip, int port) {
        this.connection = new ServerConnection("http://" + ip + ":" + port);
    }

    @Override
    public void showConnect() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewerModel.class.getResource("ConnectView.fxml"));
        try {
            Node view = loader.load();
            mainview.setCenter(view);
            ConnectController cont = loader.getController();
            cont.setModel(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showTournamentList() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewerModel.class.getResource("TournamentListView.fxml"));
        try {
            Node view = loader.load();
            mainview.setCenter(view);
            LobbyController cont = loader.getController();
            cont.setModel(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMoves(String tournamentId) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewerModel.class.getResource("MovesView.fxml"));
        try {
            Node view = loader.load();
            mainview.setCenter(view);
            LiveGameController cont = loader.getController();
            cont.setModel(this);
            cont.setTournamentId(tournamentId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<NetworkedTournament> fetchTournaments() {
        return connection.fetchTournaments();
    }

    @Override
    public List<RoundResultDTO> getMoves(String tournamentId) {
        return connection.getMoves(tournamentId);
    }

    @Override
    public void startTournament(String tournamentId) {
        new Thread(() -> connection.startTournament(tournamentId)).start();
    }
}
