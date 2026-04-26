package remote.views;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import remote.dto.RoundResultDTO;
import remote.server.NetworkedTournament;

@ExtendWith(ApplicationExtension.class)
public class TestLiveGameView implements ViewerModelInterface {

    int showTournamentListCalled = 0;
    boolean emptyMoves = false;

    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LiveGameController.class.getResource("MovesView.fxml"));
        BorderPane view = loader.load();
        LiveGameController cont = loader.getController();
        cont.setModel(this);
        cont.setTournamentId("t1");
        stage.setScene(new Scene(view));
        stage.show();
    }

    @Test
    public void testLabel(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#tournamentLabel").queryAs(Label.class))
            .hasText("Tournament: t1");
    }
    
    @SuppressWarnings("unchecked")
    private ListView<String> getMovesList(FxRobot robot) {
        return (ListView<String>) robot.lookup("#movesListView").queryAll().iterator().next();
    }
    
    @Test
    public void testMovesDisplay(FxRobot robot) {
        WaitForAsyncUtils.waitForFxEvents();
        Assertions.assertThat(getMovesList(robot))
            .hasListCell("P1: COOPERATE | P2: DEFECT | Score: 0-5");
    }
    @Test
    public void testEmptyMovesDisplay(FxRobot robot) throws InterruptedException {
        emptyMoves = true;
        Thread.sleep(1100);
        WaitForAsyncUtils.waitForFxEvents();
        Assertions.assertThat(getMovesList(robot)).hasExactlyNumItems(0);
    }
    
    @Test
    public void testBack(FxRobot robot) {
        robot.clickOn("Back");
        WaitForAsyncUtils.waitForFxEvents();
        Assertions.assertThat(showTournamentListCalled).isEqualTo(1);
    }
    
    @Override public void connect(String ip, int port) {}
    @Override public void showConnect() {}
    @Override public void showTournamentList() { showTournamentListCalled++; }
    @Override public void showMoves(String id) {}
    @Override public List<NetworkedTournament> fetchTournaments() { return List.of(); }
    @Override public List<RoundResultDTO> getMoves(String id) {
    	if (emptyMoves) return List.of();
        return List.of(new RoundResultDTO("COOPERATE", "DEFECT", 0, 5));
    }
    @Override public void startTournament(String id) {}
}
