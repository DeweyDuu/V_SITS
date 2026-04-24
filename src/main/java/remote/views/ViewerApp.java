package remote.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewerApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewerApp.class.getResource("MainView.fxml"));
        BorderPane view = loader.load();
        ViewerModel model = new ViewerModel(view);
        model.showConnect();
        
        Scene scene = new Scene(view);
        stage.setTitle("Tournament Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
