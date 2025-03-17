package org.example.binproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TrashController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}