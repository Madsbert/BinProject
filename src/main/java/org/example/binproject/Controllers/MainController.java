package org.example.binproject.Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import org.example.binproject.Domain.Measurements;
import org.example.binproject.Persistance.MeasurementInterface;
import org.example.binproject.Persistance.MeasurementsDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private BarChart barChart;
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
    public void showDataForTimePeriod(LocalDate from, LocalDate to) throws Exception {
        LocalDateTime dtFrom = from.atStartOfDay();
        LocalDateTime dtTo = to.atStartOfDay();

        MeasurementInterface measurementInterface = new MeasurementsDatabase();
        List<Measurements> list = measurementInterface.readAll();
        List<Measurements> resultList = new ArrayList<Measurements>();
        for(Measurements measurement : list) {
            if (LocalDateTime.parse(measurement.getTimeStamp()).isAfter(dtFrom) && LocalDateTime.parse(measurement.getTimeStamp()).isBefore(dtTo)) {
                resultList.add(measurement);
            }
        }
    }

}