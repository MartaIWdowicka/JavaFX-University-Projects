package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    Button addButton;
    @FXML
    Button deleteButton;
    @FXML
    Button editButton;
    @FXML
    TextField enterPhoneField;
    @FXML
    TextField enterNameField;
    @FXML
    TableView<PhoneBookEntry> phoneBookTable;
    @FXML
    TableColumn nameBook;
    @FXML
    TableColumn phoneBook;
    @FXML
    Pane wrapperPane;
    @FXML
    Button applyButton;
    @FXML
    AnchorPane rootPane;
    @FXML
    Label errorMessage;

    PhoneBookEntry phoneBookEntry;
    private ObservableList<PhoneBookEntry> data = FXCollections.observableArrayList();
    private List<PhoneBookEntry> datatemp = new ArrayList<>();

    @FXML
    private void initialize() {
        setupStage();

        addButton.setOnMouseClicked(event -> {
            addEntryIfNotEmpty();
        });

        deleteButton.setOnMouseClicked(event -> {
            deleteSelected();
            errorMessage.setText(" ");
        });

        editButton.setOnMouseClicked(event -> {
            chooseItemForEditing();
            errorMessage.setText(" ");
        });

        applyButton.setOnMouseClicked(event -> {
            replace();
        });

        phoneBookTable.setEditable(true);
        nameBook.setCellValueFactory(new PropertyValueFactory<PhoneBookEntry, String>("name"));
        phoneBook.setCellValueFactory(new PropertyValueFactory<PhoneBookEntry, String>("phoneNumber"));
        phoneBookTable.setItems(data);
    }

    public void setupStage() {
        try { data.addAll(readFile(datatemp)); } catch (IOException e){ }
        wrapperPane.getChildren().clear();
        wrapperPane.getChildren().add(editButton);
    }

    public void deleteSelected() {
        data.remove(phoneBookTable.getSelectionModel().getSelectedItem());
        try { writeToFile(data); }catch(Exception e){errorMessage.setText("Could not save to file.");}
    }

    public void addEntryIfNotEmpty() {
        if (validateFields()) {
                data.add(new PhoneBookEntry(enterNameField.getText(), enterPhoneField.getText()));
                errorMessage.setText(" ");
            errorMessage.setText(" ");
            try { writeToFile(data); }catch(Exception e){ errorMessage.setText("Could not save to file."); }
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("((\\+?|\\d)(-?|\\d\\d|\\d)*){9,14}");  //match a telephone number with optional '+' and "-" and decimal
    }

    public boolean validateFields(){
        if (enterNameField.getText().equals("") || enterPhoneField.getText().equals("")) {
            errorMessage.setText("Name and Number cannot be Empty.");
            return false;
        }else{
            if(!isNumeric(enterPhoneField.getText())) {
                errorMessage.setText("Phone Number: digits, '+' and '-' ");
                return false;
            }
        }
        return true;
    }

    public void chooseItemForEditing() {
        phoneBookEntry = phoneBookTable.getSelectionModel().getSelectedItem();
        if (phoneBookEntry!=null) {
            enterNameField.clear();
            enterNameField.appendText(phoneBookEntry.getName());
            enterPhoneField.clear();
            enterPhoneField.appendText(phoneBookEntry.getPhoneNumber());
            wrapperPane.getChildren().clear();
            wrapperPane.getChildren().add(applyButton);
        }
    }

    public void replace() {
        if(validateFields()) {
            for (PhoneBookEntry a : data) {
                if (a.getName().equals(phoneBookEntry.getName()) && a.phoneNumber == phoneBookEntry.phoneNumber) {
                    datatemp.add(new PhoneBookEntry(enterNameField.getText(), enterPhoneField.getText()));
                } else {
                    datatemp.add(a);
                }
            }
            wrapperPane.getChildren().clear();
            wrapperPane.getChildren().add(editButton);
            data.clear();
            data.addAll(datatemp);
            datatemp.clear();
            try {
                writeToFile(data);
            } catch (Exception e) {
                errorMessage.setText("Could not save to file.");
            }
        }
    }

    public static boolean writeToFile(ObservableList<PhoneBookEntry> data) throws IOException {
        BufferedWriter outputWriter = null;
        try{
            outputWriter = new BufferedWriter( new FileWriter("PhoneRecords.txt"));
        }catch (Exception e){
            return false;
        }
        for (PhoneBookEntry a : data) {
            outputWriter.write(a.getName() + "<" + a.getPhoneNumber());
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
        return true;
    }

    public static List<PhoneBookEntry> readFile(final List<PhoneBookEntry> datatemp) throws IOException {
        try{
            BufferedReader br = new BufferedReader(new FileReader("PhoneRecords.txt"));
            String[] sb;
            String line = br.readLine();
            while (line != null) {
                sb = line.split("<");
                datatemp.add(new PhoneBookEntry(sb[0], sb[1]));
                line = br.readLine();
            }
        }catch(IOException e){}
        return datatemp;
    }
}
