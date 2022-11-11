package com.example.marquinhoscut.Model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PaymentMethod {
    private String name;

    public PaymentMethod(String name){
        this.name = new SimpleStringProperty(name).get();
    }
    public String getName() {
        return name;
    }
}
