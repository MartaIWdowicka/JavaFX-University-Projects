package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Random;

public class Controller {

    @FXML
    Button button;

    @FXML
    private void initialize() {
        button.setOnMouseEntered(event -> changeThePositionOfButton());
    }

    private void changeThePositionOfButton() {
        Random generator = new Random();
        button.setTranslateX(generator.nextInt(350));
        button.setTranslateY(generator.nextInt(250));
    }
}