
package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.Dao.PaymentMethodDao;
import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Dao.SellingDao;
import com.example.marquinhoscut.Model.PaymentMethod;
import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.Model.ResultCharts;
import com.example.marquinhoscut.Model.Selling;
import com.example.marquinhoscut.Utils.Bar.AdminBar;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

public class ResultsController extends AdminBar {

	@FXML
	private CategoryAxis Date;

	@FXML
	private LineChart<String,Number> ResultChart;

	@FXML
	private NumberAxis Value;
	@FXML
	private ChoiceBox<String> CBPaymentMethod;

	@FXML
	private ChoiceBox<String> CBbarber;
	@FXML
	private Label toReceive;


	private ArrayList<Professional> professionals = new ArrayList<>();
	private ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();

	ArrayList<Selling> sellings = new ArrayList<>();
	ArrayList<ResultCharts> listResultCharts = new ArrayList<>();

	@FXML
	private Button exitButton,servicesButton,professionalButton,resultsButton;
	
	@FXML
	void initialize(){
		handleNavigationBar(exitButton,servicesButton,professionalButton,resultsButton);
		PopulateOptionsResults();
		CreateGraphics(ResultChart);
	}

	public void PopulateOptionsResults(){
		try{
			ProfessionalDao pDao = new ProfessionalDao();
			professionals.addAll(pDao.getListProfessional());

			PaymentMethodDao payDao = new PaymentMethodDao();
			paymentMethods.addAll(payDao.getListPaymentMethod());

			SellingDao sellingDao = new SellingDao();
			sellings.addAll(sellingDao.getListSelling());

			listResultCharts.addAll(sellingDao.getListTotalDay());

		}catch(Exception err){
			System.out.println(err.getMessage());
		}
		for(Professional professional : professionals ){
			CBbarber.getItems().add(professional.getName());
		}

		for(PaymentMethod paymentMethod : paymentMethods ){

			CBPaymentMethod.getItems().add(paymentMethod.getName());
		}
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
	public String Sumtotal(){
		try {
			double sumTotal=0;
			for(Selling selling:sellings){
				sumTotal+= selling.getTotal();
				System.out.println(selling.getSellingDate());
			}
			return NumberFormat.getCurrencyInstance().format(sumTotal);
		}catch(Exception err){
		System.out.println(err.getMessage());
		return "";
		}

	}
}
