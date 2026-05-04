package remote.views;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import remote.server.ServerApp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ServerApp.class)
@ExtendWith(ApplicationExtension.class)
public class TestRunningNetworkedViews {
	
    @LocalServerPort
    private int port;
    ViewerModel model;
    
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewerApp.class.getResource("MainView.fxml"));
        BorderPane view = loader.load();
        model = new ViewerModel(view);
        model.connect("localhost", port);
        model.showTournamentList();
        stage.setScene(new Scene(view));
        stage.show();
    }

    @SuppressWarnings("unchecked")
    private ListView<String> getList(FxRobot robot) {
        return (ListView<String>) robot.lookup("#tournamentListView").queryAll().iterator().next();
    }

    @Test
    public void testTournamentListLoads(FxRobot robot) {
        Assertions.assertThat(getList(robot)).hasExactlyNumItems(3);
    }
    
    
    @SuppressWarnings("unchecked")
    private ListView<String> getMovesList(FxRobot robot) {
        return (ListView<String>) robot.lookup("#movesListView").queryAll().iterator().next();
    }
    
    @Test
    public void testStartShowsMoves(FxRobot robot) throws InterruptedException {
        robot.clickOn("Defect vs Cooperate [REGISTERING]");
        robot.clickOn("Start Tournament");
        Thread.sleep(2100);
        WaitForAsyncUtils.waitForFxEvents();
        Assertions.assertThat(getMovesList(robot))
            .hasListCell("Always Defect: DEFECT | Always Cooperate: COOPERATE | Score: 5-0");
    }
    
    @Test
    public void testBack(FxRobot robot) {
        robot.clickOn("Cooperate vs TitForTat [REGISTERING]");
        robot.clickOn("View Moves");
        robot.clickOn("Back");
        WaitForAsyncUtils.waitForFxEvents();
        Assertions.assertThat(getList(robot)).hasExactlyNumItems(3);
    }
}
