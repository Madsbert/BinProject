package org.example.binproject.Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import org.example.binproject.Domain.Measurements;
import org.example.binproject.Persistance.MeasurementsDatabase;
import org.example.binproject.Services.Calculations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    protected static CategoryAxis axisTimeline;
    @FXML
    protected static NumberAxis axisValue;
    @FXML
    protected static ChoiceBox selectTimePeriod;
    @FXML
    protected static StackedBarChart barChart;
    @FXML
    protected static DatePicker selectDate;
    @FXML
    protected static ChoiceBox selectViewedData;
    @FXML
    protected static Button btnImport;

    protected static ObservableList<String> timePeriod = FXCollections.observableArrayList();
    protected static LocalDate selectedDate = LocalDate.now();
    protected static ObservableList<String> viewedData = FXCollections.observableArrayList();


    public static void initialize() {

        viewedData.add("Status of Bins");
        viewedData.add("Driven Kilometer");
        viewedData.add("Saved Kilometer");
        for (String s: viewedData) {
            viewedData.add(s);
        }
        timePeriod.add("Day");
        timePeriod.add("Week");
        timePeriod.add("Month");
        timePeriod.add("Year");
        selectTimePeriod.setOnAction(e -> {
            try {
                onTimePeriodChange();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    //Actions
    public static void onTimePeriodChange() throws Exception {
        int selected = selectTimePeriod.getSelectionModel().getSelectedIndex();
        switch (selected) {
            case 0:
                showDataForTimePeriod(selectedDate, selectedDate);
                break;
            case 1:

                    LocalDate startOfWeek = selectedDate.minusDays(7);
                    LocalDate endOfWeek = selectedDate;

                    showDataForTimePeriod(startOfWeek, endOfWeek);
                    break;
            case 2:
                        int month = selectedDate.getMonthValue();
                        int year = selectedDate.getYear();
                        LocalDate startOfMonth = LocalDate.of(year, month, 1);
                        LocalDate endOfMonth = LocalDate.of(year, month, 31);
                        showDataForTimePeriod(startOfMonth, endOfMonth);
                        break;
            case 3:
                        int yearMonth = selectedDate.getMonthValue();
                        LocalDate startOfYear = LocalDate.of(yearMonth, 1, 1);
                        LocalDate endOfYear = LocalDate.of(yearMonth, 12, 31);
                        showDataForTimePeriod(startOfYear, endOfYear);


                        break;



        }
    }






    //
    public static void showDataForTimePeriod(LocalDate from, LocalDate to) throws Exception {
        LocalDateTime dtFrom = from.atStartOfDay();
        LocalDateTime dtTo = to.atStartOfDay();
        MeasurementsDatabase db = new MeasurementsDatabase();
        List<Measurements> list = db.readAll();
        List<Measurements> resultList = new ArrayList<Measurements>();
        for(Measurements measurement : list) {
            if (LocalDateTime.parse(measurement.getTimeStamp()).isAfter(dtFrom) && LocalDateTime.parse(measurement.getTimeStamp()).isBefore(dtTo)) {
                resultList.add(measurement);
            }
        }
        int countDays = dtFrom.compareTo(dtTo);
        List<XYChart.Series> seriesList = new ArrayList<XYChart.Series>();
        for (int i = 0; i < countDays; i++) {
            LocalDateTime dtIndex = dtFrom;
            seriesList.get(i).setName(dtIndex.toString());
            List<Measurements> measurementsDay = new ArrayList<>();
            for (Measurements m : resultList) {
                if (m.getTimeStamp().equals(dtIndex.toString())) {
                    measurementsDay.add(m);
                }
            }
            List<Integer> resultDays = new ArrayList<>();
            resultDays = Calculations.calculateStatistics(measurementsDay);

            seriesList.get(i).getData().add(new XYChart.Data<String, Number>("Red", resultDays.get(0)));
            seriesList.get(i).getData().add(new XYChart.Data<String, Number>("Yellow", resultDays.get(1)));
            seriesList.get(i).getData().add(new XYChart.Data<String, Number>("Green", resultDays.get(2)));

        }
        for (XYChart.Series series : seriesList) {
            barChart.getData().addAll(series);
        }








    }







}