package com.example.marquinhoscut.Model;

import javafx.beans.property.SimpleDoubleProperty;

import java.util.Date;

import com.example.marquinhoscut.Interfaces.ChartModel;

public class ResultCharts implements ChartModel {
    private Date sellingDate;
    private Double totalDate;

    public ResultCharts(Double totalDate, Date sellingDate) {
        this.totalDate = new SimpleDoubleProperty(totalDate).get();
        this.sellingDate = sellingDate;
    }

    public Date getSellingDate() {
        return sellingDate;
    }

    public Double getTotalDate() {
        return totalDate;
    }

}
