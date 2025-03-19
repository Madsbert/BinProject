package org.example.binproject.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.binproject.Foundation.SQLManager;

import java.io.IOException;
import java.sql.Connection;

public class MainScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/org/example/binproject/Main.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws Exception {
        //System.out.println(MainScreen.class.getResource("/org/example/binproject/Main.fxml"));
        launch();
        //test connection
        Connection connection = SQLManager.getConnection();
    }
}