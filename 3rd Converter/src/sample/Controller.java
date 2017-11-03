package pakage;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    RadioButton celciusFrom;
    @FXML
    RadioButton fahrenheitFrom;
    @FXML
    RadioButton kelvinFrom;
    @FXML
    RadioButton celciusTo;
    @FXML
    RadioButton fahrenheitTo;
    @FXML
    RadioButton kelvinTo;
    @FXML
    TextField output;
    @FXML
    TextField valueTextField;

    @FXML
    private void initialize() {
        selectFirstValue();
        initActions();
    }

    private void selectFirstValue() {
        celciusTo.setSelected(true);
        celciusFrom.setSelected(true);

        valueTextField.setText("0");
        output.setText("0");
    }

    private void initActions() {
        valueTextField.setOnKeyReleased(event -> {
            convertData();
        });

        celciusTo.setOnMouseClicked(event -> convertData());
        celciusFrom.setOnMouseClicked(event -> convertData());
        kelvinTo.setOnMouseClicked(event -> convertData());
        kelvinFrom.setOnMouseClicked(event -> convertData());
        fahrenheitFrom.setOnMouseClicked(event -> convertData());
        fahrenheitTo.setOnMouseClicked(event -> convertData());
    }

    private void convertData() {
        try {
            double degreeFrom = Double.valueOf(valueTextField.getText());
            double degreeTo = 0;
            int switchx = 0;
            switchx += (celciusFrom.isSelected()) ? 10 : (kelvinFrom.isSelected()) ? 20 : (fahrenheitFrom.isSelected()) ? 30 : 0;
            switchx += (celciusTo.isSelected()) ? 1 : (kelvinTo.isSelected()) ? 2 : (fahrenheitTo.isSelected()) ? 3 : 0;

            switch (switchx) {
                case 11:
                    degreeTo = degreeFrom;
                    break;
                case 12:
                    degreeTo = degreeFrom + 273.15;
                    break;
                case 13:
                    degreeTo = (degreeFrom * (9.0 / 5)) + 32;
                    break;
                case 21:
                    degreeTo = degreeFrom - 273.15;
                    break;
                case 22:
                    degreeTo = degreeFrom;
                    break;
                case 23:
                    degreeTo = (degreeFrom * (9.0 / 5)) - 459.67;
                    break;
                case 31:
                    degreeTo = (degreeFrom - 32) / 1.8;
                    break;
                case 32:
                    degreeTo = (degreeFrom + 459.67) / 1.8;
                    break;
                case 33:
                    degreeTo = degreeFrom;
                    break;
                default:
                    output.setText("Nieprzewidziana wartość");
                    break;
            }
            output.setText(String.valueOf(degreeTo));
        } catch (Exception e) {
            output.setText("Nieprzewidziana wartość");
        }
    }
}
