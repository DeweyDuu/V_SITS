package remote.views;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import remote.dto.RoundResultDTO;
import remote.server.NetworkedTournament;

@ExtendWith(ApplicationExtension.class)
public class TestLobbyView implements ViewerModelInterface {

    int showMovesCalled = 0;
    int startTournamentCalled = 0;
    int fetchTournamentsCalled = 0;
    String lastTournamentId;

    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LobbyController.class.getResource("TournamentListView.fxml"));
        BorderPane view = loader.load();
        LobbyController cont = loader.getController();
        cont.setModel(this);
        stage.setScene(new Scene(view));
        stage.show();
    }

    @SuppressWarnings("unchecked")
    private ListView<String> getList(FxRobot robot) {
        return (ListView<String>) robot.lookup("#tournamentListView").queryAll().iterator().next();
    }

    @Test
    public void testListLoadsOnStart(FxRobot robot) {
        Assertions.assertThat(getList(robot)).hasExactlyNumItems(2);
    }
    
    @Test
    public void testWatchSelected(FxRobot robot) {
        robot.clickOn("Tournament One [REGISTERING]");
        robot.clickOn("View Moves");
        Assertions.assertThat(showMovesCalled).isEqualTo(1);
        Assertions.assertThat(lastTournamentId).isEqualTo("t1");
    }
    
    @Test
    public void testStart(FxRobot robot) {
        robot.clickOn("Tournament One [REGISTERING]");
        robot.clickOn("Start Tournament");
        Assertions.assertThat(startTournamentCalled).isEqualTo(1);
        Assertions.assertThat(showMovesCalled).isEqualTo(1);
        Assertions.assertThat(lastTournamentId).isEqualTo("t1");
    }
    
    @Test
    public void testRefresh(FxRobot robot) {
        robot.clickOn("Refresh");
        Assertions.assertThat(fetchTournamentsCalled).isEqualTo(2);
        Assertions.assertThat(getList(robot)).hasExactlyNumItems(2);
    }

    @Override public void connect(String ip, int port) {}
    @Override public void showConnect() {}
    @Override public void showTournamentList() {}
    @Override public void showMoves(String id) { showMovesCalled++; lastTournamentId = id; }
    @Override public List<NetworkedTournament> fetchTournaments() {
        fetchTournamentsCalled++;
        return List.of(
            new NetworkedTournament("t1", "Tournament One", null, null, null),
            new NetworkedTournament("t2", "Tournament Two", null, null, null)
        );
    }
    @Override public List<RoundResultDTO> getMoves(String id) { return List.of(); }
    @Override public void startTournament(String id) { startTournamentCalled++; lastTournamentId = id; }
}
