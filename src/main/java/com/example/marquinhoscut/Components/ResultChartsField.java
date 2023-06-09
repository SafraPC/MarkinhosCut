package com.example.marquinhoscut.Components;


import com.example.marquinhoscut.Dao.ResultChartsDao;

import com.example.marquinhoscut.Interfaces.ChartModel;
import com.example.marquinhoscut.Model.ResultCharts;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import com.example.marquinhoscut.Utils.Hover;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class ResultChartsField{

    @FXML
    private CategoryAxis Date;
    @FXML
    private LineChart<String,Number> ResultChart;
    @FXML
    private NumberAxis Value;
    
    private String initialDate, finalDate, professionalName, paymentName;
    ArrayList<ChartModel> listResultCharts = new ArrayList<>();

    public void CreateGraphics(LineChart resultChart){
        try{
            XYChart.Series<String,Double> invoicing = new XYChart.Series<>();
            invoicing.setName("Evolução");
            for(ChartModel resultCharts: listResultCharts){
                invoicing.getData().add(new XYChart.Data<>(resultCharts.getSellingDate().toString(),resultCharts.getTotalDate()));
            }
            for(int i = 0; i < invoicing.getData().stream().count(); i++){
                invoicing.getData().get(i).setNode(new Hover(i,Integer.parseInt(String.format("%.0f"
                        , invoicing.getData().get(i).getYValue()))));
            }
            resultChart.getData().addAll(invoicing);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    
    public void createGraph(String initialDate, String finalDate, String professional, String paymentMethod){
        try{
            this.paymentName = paymentMethod;
            this.professionalName = professional;
            this.initialDate = initialDate;
            this.finalDate = finalDate;
            Results();
            CreateGraphics(ResultChart);
        }catch (Exception err){
            DialogMessage.errorMessage("Erro","Falha ao carregar o gráfico!");
        }
       
    }

    public void Results(){
        try{
            ResultChartsDao resultChartsDao = new ResultChartsDao();
            listResultCharts.addAll(resultChartsDao.getListTotalDay(initialDate, finalDate,professionalName,paymentName));
        }catch(Exception err){
            System.out.println(err.getMessage());
        }

    }
}
