package com.example.marquinhoscut.Components;

import com.example.marquinhoscut.Dao.ResultChartsDao;
import com.example.marquinhoscut.Dao.SellingDao;
import com.example.marquinhoscut.Model.ResultCharts;
import com.example.marquinhoscut.Model.Selling;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class RegisterSellingField {

    @FXML
    private Label paymentMethod, professionalSelling, sellingDate, sellingId, totalSelling;

    private String payment, professional, date;
    int id;

    double total;



    public void setId(int id){
        this.id = id;
        this.sellingId.setText(Integer.toString(id));
    }

    public void setProfessional(String professional) {
        this.professional = professional;
        this.professionalSelling.setText(professional);
    }
    public void setPayment(String payment) {
        this.payment = payment;
        this.paymentMethod.setText(payment);
    }
    public void setDate(String date) {
        this.date = date;
        this.sellingDate.setText(date);
    }
    public void setTotal(double total){
        this.total = total;
        this.totalSelling.setText(Double.toString(total));
    }


}
