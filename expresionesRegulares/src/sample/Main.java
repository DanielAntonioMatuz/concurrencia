package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("E X P R E S I O N E S    R E G U L A R E S");
        primaryStage.setScene(new Scene(root, 1300, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
