package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("I D E  Upwind Language");
        int screenWidth = (int) Screen.getPrimary().getBounds().getWidth() - 300;
        int screenHeight = (int) Screen.getPrimary().getBounds().getHeight() - 70;

        primaryStage.setScene(new Scene(root, screenWidth, screenHeight));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
