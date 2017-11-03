package sample;

import javafx.beans.property.SimpleStringProperty;

public class PhoneBookEntry {

    public SimpleStringProperty name;
    public SimpleStringProperty phoneNumber;

    public PhoneBookEntry(String name, String number) {
        this.name = new SimpleStringProperty(name);
        this.phoneNumber = new SimpleStringProperty(number);
    }

    public String getName() {
        return name.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }
}