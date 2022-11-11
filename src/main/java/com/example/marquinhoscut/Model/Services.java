package com.example.marquinhoscut.Model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Services {
    private int id;
    private String name;
    double value;
    private boolean active;

    public Services(String name, double value, boolean active, int id){
        this.active = new SimpleBooleanProperty(active).get();
        this.value = new SimpleDoubleProperty(value).getValue();
        this.name = new SimpleStringProperty(name).get();
        this.id = new SimpleIntegerProperty(id).get();
    }


    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public boolean getIsActive(){return active;}

}
