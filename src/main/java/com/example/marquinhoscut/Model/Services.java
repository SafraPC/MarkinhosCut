package com.example.marquinhoscut.Model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Services {
    private String name;
    double value;
    private boolean active;

    public Services(String name, double value, boolean active){
        this.active = new SimpleBooleanProperty(active).get();
        this.value = new SimpleDoubleProperty(value).getValue();
        this.name = new SimpleStringProperty(name).get();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public boolean getActive(){
        return active;
    }
}
