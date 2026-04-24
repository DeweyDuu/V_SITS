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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import remote.dto.RoundResultDTO;
import remote.server.NetworkedTournament;

@ExtendWith(ApplicationExtension.class)
public class TestConnectView implements ViewerModelInterface {

    int connectCalled = 0;
    int showTournamentListCalled = 0;

    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ConnectController.class.getResource("ConnectView.fxml"));
        BorderPane view = loader.load();
        ConnectController cont = loader.getController();
        cont.setModel(this);
        stage.setScene(new Scene(view));
        stage.show();
    }

    @Test
    public void testConnect(FxRobot robot) {
        robot.clickOn("#ipField").write("localhost");
        robot.clickOn("#portField").write("8080");
        robot.clickOn("Connect");
        Assertions.assertThat(connectCalled).isEqualTo(1);
        Assertions.assertThat(showTournamentListCalled).isEqualTo(1);
    }

    @Override public void connect(String ip, int port) { connectCalled++; }
    @Override public void showConnect() {}
    @Override public void showTournamentList() { showTournamentListCalled++; }
    @Override public void showMoves(String id) {}
    @Override public List<NetworkedTournament> fetchTournaments() { return List.of(); }
    @Override public List<RoundResultDTO> getMoves(String id) { return List.of(); }
    @Override public void startTournament(String id) {}
}