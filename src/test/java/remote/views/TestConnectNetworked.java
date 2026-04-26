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

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import remote.server.ServerApp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ServerApp.class)
@ExtendWith(ApplicationExtension.class)
public class TestConnectNetworked {

    @LocalServerPort
    private int port;

    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewerApp.class.getResource("MainView.fxml"));
        BorderPane view = loader.load();
        ViewerModel model = new ViewerModel(view);
        model.showConnect();
        stage.setScene(new Scene(view));
        stage.show();
    }

    @SuppressWarnings("unchecked")
    private ListView<String> getList(FxRobot robot) {
        return (ListView<String>) robot.lookup("#tournamentListView").queryAll().iterator().next();
    }

    @Test
    public void testConnect(FxRobot robot) {
        robot.clickOn("#ipField").write("localhost");
        robot.clickOn("#portField").write(String.valueOf(port));
        robot.clickOn("Connect");
        Assertions.assertThat(getList(robot)).hasExactlyNumItems(3);
    }
}
