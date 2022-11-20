package com.example.marquinhoscut.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Selling {
    private int sellingId,quantity;
    private String professional,paymentName,serviceName;
    private Double total,productPrice;
    private Date sellingDate;

    public Selling(int sellingId, String professional, String paymentName, Double total, Date sellingDate) {
        this.sellingId = new SimpleIntegerProperty(sellingId).get();
        this.professional = new SimpleStringProperty(professional).get();
        this.paymentName = new SimpleStringProperty(paymentName).get();
        this.total = new SimpleDoubleProperty(total).get();
        this.sellingDate = sellingDate;
    }

    public Selling(int sellingId, String professional, String paymentName, Date sellingDate,
                   String serviceName, Double productPrice, int quantity) {
        this.sellingId = new SimpleIntegerProperty(sellingId).get();
        this.professional = new SimpleStringProperty(professional).get();
        this.paymentName = new SimpleStringProperty(paymentName).get();
        this.total = new SimpleDoubleProperty(total).get();
        this.sellingDate = sellingDate;
        this.serviceName = new SimpleStringProperty(serviceName).get();
        this.productPrice = new SimpleDoubleProperty(productPrice).get();
        this.quantity = new SimpleIntegerProperty(quantity).get();
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
    
    public String getServiceName() {
        return serviceName;
    }
    
    public Double getProductPrice() {
        return productPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
