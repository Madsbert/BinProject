package org.example.binproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.binproject.Persistance.MeasurementsDatabase;
import org.example.binproject.Domain.Measurements;
import org.example.binproject.Persistance.MeasurementsDatabase;
import org.example.binproject.Services.CsvConverter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        //CsvConverter csvConverter = new CsvConverter();

        // Test CSV Converter (right now only path to arraylist conversion)
        //csvConverter.importCsv("src/main/resources/org/example/binproject/test.csv");

        //Measurements measurement = new Measurements("19-03-2025 10:55:00", 50, "10-03-2025 10:00:00", false, false, 1);
        //MeasurementsDatabase.createMeasurement(measurement);

        //System.out.println(MeasurementsDatabase.read(1));
        MeasurementsDatabase db = new MeasurementsDatabase();
        List<Measurements> measurementsList = db.readAll();
        for (Measurements measurements : measurementsList) {
            System.out.println(measurements);
        }



    }
}