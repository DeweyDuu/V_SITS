package remote.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ConnectController {

    ViewerModelInterface model;

    @FXML
    private TextField ipField;
    @FXML
    private TextField portField;

    public void setModel(ViewerModelInterface newModel) {
        model = newModel;
    }

    @FXML
    void onConnect(ActionEvent event) {
    	String ip = ipField.getText();
        int port = Integer.parseInt(portField.getText());
        model.connect(ip, port);
        model.showTournamentList();
    }
}
