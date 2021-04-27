package sample;

import sample.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("R E A D E R  |  W R I T E R");
        primaryStage.setScene(new Scene(root, 1100, 695));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
