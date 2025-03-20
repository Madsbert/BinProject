package org.example.binproject.Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import org.example.binproject.Domain.Measurements;
import org.example.binproject.Persistance.MeasurementInterface;
import org.example.binproject.Persistance.MeasurementsDatabase;
import org.example.binproject.Services.Calculations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private CategoryAxis axisTimeline;
    @FXML
    private NumberAxis axisValue;
    @FXML
    private ChoiceBox selectTimePeriod;
    @FXML
    private StackedBarChart barChart;
    @FXML
    private DatePicker selectDate;
    @FXML
    private ChoiceBox selectViewedData;
    @FXML
    private Button btnImport;

    private ObservableList<String> timePeriod = FXCollections.observableArrayList();
    private LocalDate selectedDate = LocalDate.now();
    private ObservableList<String> viewedData = FXCollections.observableArrayList();


    public void initialize() {
        viewedData.addAll("Status of Bins", "Driven Kilometer", "Saved Kilometer");
        selectViewedData.setItems(viewedData);
        selectViewedData.setValue("Select Information ");

        timePeriod.addAll("Day", "Week", "Month", "Year");
        selectTimePeriod.setItems(timePeriod);
        selectTimePeriod.setValue("Select Time Period");

        selectTimePeriod.setOnAction(e -> {
            try {
                onTimePeriodChange();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    //Actions
    public void onTimePeriodChange() throws Exception {
        int selected = selectTimePeriod.getSelectionModel().getSelectedIndex();
        LocalDate start, end;
        switch (selected) {
            case 0://dag
                start = selectedDate;
                end = selectedDate;
                break;
            case 1://uge
                start = selectedDate.minusDays(selectedDate.getDayOfWeek().getValue() - 1);
                end = start.plusDays(6);
                break;
            case 2://månede
                start = selectedDate.withDayOfMonth(1);
                end = start.plusMonths(1).minusDays(1);
                break;
            case 3:// år
                start = selectedDate.withDayOfYear(1);
                end = start.plusYears(1).minusDays(1);
                break;
            default:
                return;
        }
        showDataForTimePeriod(start, end);
    }

    //
    public void showDataForTimePeriod(LocalDate from, LocalDate to) throws Exception {
        MeasurementInterface measurementInterface = new MeasurementsDatabase();
        List<Measurements> list = measurementInterface.readAll();
        List<Measurements> resultList = new ArrayList<>();
        for (Measurements measurement : list) {
            LocalDate measurementDate = LocalDate.parse(measurement.getTimeStamp().substring(0, 10));
            if (!measurementDate.isBefore(from) && !measurementDate.isAfter(to)) {
                resultList.add(measurement);
            }
        }
        long countDays = ChronoUnit.DAYS.between(from, to) + 1;  // Antal dage
        List<XYChart.Series<String, Number>> seriesList = new ArrayList<>();

        for (int i = 0; i < countDays; i++) {
            LocalDate currentDate = from.plusDays(i);
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(currentDate.toString());

            List<Measurements> measurementsDay = new ArrayList<>();
            for (Measurements m : resultList) {
                LocalDate measurementDate = LocalDate.parse(m.getTimeStamp().substring(0, 10));
                if (measurementDate.equals(currentDate)) {
                    measurementsDay.add(m);
                }
            }
            List<Integer> resultDays = Calculations.calculateStatistics(measurementsDay);

            series.getData().add(new XYChart.Data<>("Red", resultDays.get(0)));
            series.getData().add(new XYChart.Data<>("Yellow", resultDays.get(1)));
            series.getData().add(new XYChart.Data<>("Green", resultDays.get(2)));

            seriesList.add(series);
        }

        barChart.getData().clear();
        barChart.getData().addAll(seriesList);
    }

}

