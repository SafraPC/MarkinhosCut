
package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Components.SellingField;
import com.example.marquinhoscut.Dao.PaymentMethodDao;
import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Dao.ResultChartsDao;
import com.example.marquinhoscut.Dao.SellingDao;
import com.example.marquinhoscut.Model.PaymentMethod;
import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.Model.ResultCharts;
import com.example.marquinhoscut.Model.Selling;
import com.example.marquinhoscut.Utils.Bar.AdminBar;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ResultsController extends AdminBar {


	@FXML
	private GridPane gridPane;
	@FXML
	private DatePicker datePickerInitial;

	@FXML
	private DatePicker datePickerEnd;

	@FXML
	private ChoiceBox<String> CBPaymentMethod;

	@FXML
	private ChoiceBox<String> CBbarber;
	@FXML
	private Label toReceive;


	private ArrayList<Professional> professionals = new ArrayList<>();
	private ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();

	ArrayList<Selling> sellings = new ArrayList<>();


	@FXML
	private Button exitButton,servicesButton,professionalButton,resultsButton;
	
	@FXML
	void initialize(){
		handleNavigationBar(exitButton,servicesButton,professionalButton,resultsButton);
		PopulateOptionsResults();
		//CreateGraphics(ResultChart);
	}

	@FXML
	private void handleAddGraphics() {
		try{
			//System.out.println("getDateInitial:"+getDateInitial()+"getDateEnd:"+ getDateEnd()+"getProfessional:"+getProfessional()+"getPaymentMethod:"+getPaymentMethod());
			gridPane.getChildren().clear();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("resultChartsField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;

			gridPane.add(ap, 0, gridPane.getRowCount());

		}catch(Exception e){
			DialogMessage.show("Erro ao adicionar seção!","Houve um erro ao adicionar uma nova seção!", Alert.AlertType.ERROR);
			System.out.println(e.getMessage());
		}
	}
	public void PopulateOptionsResults(){


		try{
			ProfessionalDao professionalDao = new ProfessionalDao();
			professionals.addAll(professionalDao.getListProfessional());

			PaymentMethodDao paymentMethodDao = new PaymentMethodDao();
			paymentMethods.addAll(paymentMethodDao.getListPaymentMethod());

			SellingDao sellingDao = new SellingDao();
			sellings.addAll(sellingDao.getListSelling());


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
