package com.example.marquinhoscut.Model;

public class Services {
    private String name;
    double value;
    private boolean active;

    public Services(String name, double value, boolean active){
        this.active = active;
        this.value = value;
        this.name = name;
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
