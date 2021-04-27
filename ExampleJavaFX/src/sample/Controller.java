package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button startButton;
    @FXML
    private Parent loginPane;

    @FXML
    public void buttonClicked(ActionEvent event)throws IOException
    {
        Parent login = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
        loginPane.getChildrenUnmodifiable().setAll(login);
    }
}
