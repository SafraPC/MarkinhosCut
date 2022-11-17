package com.example.marquinhoscut.Model;

import com.dlsc.formsfx.view.controls.SimpleDateControl;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Selling {
    private int sellingId;
    private String professional;
    private String paymentName;
    private Double total;
    private Date sellingDate;
    //private int serviceId;
    //private int quantity;

    public Selling( int sellingId,String professional, String paymentName, Double total, Date sellingDate){
        this.sellingId = new SimpleIntegerProperty(sellingId).get();
        this.professional = new SimpleStringProperty(professional).get();
        this.paymentName = new SimpleStringProperty(paymentName).get();
        this.total = new SimpleDoubleProperty(total).get();
        this.sellingDate = sellingDate;
    }
    public int getSellingId(){
        return sellingId;
    }
    public String getProfessional(){
        return professional;
    }
    public String getpaymentName(){
        return paymentName;
    }
    public Double getTotal(){
        return total;
    }

    public String getSellingDate() {
        return sellingDate.toString();
    }
}
