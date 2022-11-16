package com.example.marquinhoscut.Components;

import com.example.marquinhoscut.Controller.ResultsController;
import com.example.marquinhoscut.Dao.PaymentMethodDao;
import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Dao.ResultChartsDao;
import com.example.marquinhoscut.Dao.SellingDao;
import com.example.marquinhoscut.Model.PaymentMethod;
import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.Model.ResultCharts;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class ResultChartsField{

    @FXML
    private CategoryAxis Date;
    @FXML
    private LineChart<String,Number> ResultChart;
    @FXML
    private NumberAxis Value;
    ArrayList<ResultCharts> listResultCharts = new ArrayList<>();
    @FXML
    void initialize(){
        System.out.println("entrei");
        Results();
        CreateGraphics(ResultChart);
    }
    public void CreateGraphics(LineChart resultChart){
        XYChart.Series<String,Double> invoicing = new XYChart.Series<>();
        invoicing.setName("vendas");
        for(ResultCharts resultCharts: listResultCharts){
            invoicing.getData().add(new XYChart.Data<>(resultCharts.getSellingDate().toString(),resultCharts.getTotalDate()));
            System.out.println(resultCharts.getSellingDate());
        }
        resultChart.getData().addAll(invoicing);
    }

    public void Results(){
        try{

            ResultChartsDao resultChartsDao = new ResultChartsDao();
            listResultCharts.addAll(resultChartsDao.getListTotalDay("2022-11-13", "2022-11-15",null,"Pix"));
            System.out.println("hello");

        }catch(Exception err){
            System.out.println(err.getMessage());
        }

    }
}
