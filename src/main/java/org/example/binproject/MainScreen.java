package org.example.binproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.binproject.util.CsvConverter;


import java.io.IOException;

public class MainScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws Exception {
        //launch();

        //test connection Connection connection = SQLManager.getConnection();
        CsvConverter csvConverter = new CsvConverter();

        // Test CSV Converter (right now only path to arraylist conversion)
        csvConverter.importCsv("src/main/resources/org/example/binproject/test.csv");
        System.out.println(csvConverter.getRecords());

    }
}