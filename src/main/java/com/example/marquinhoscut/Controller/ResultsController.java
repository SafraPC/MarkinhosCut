
package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Components.ResultChartsField;
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
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLOutput;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;

public class ResultsController extends AdminBar {


	@FXML
	private GridPane gridPane;
	@FXML
	private DatePicker datePickerInitial,datePickerEnd;

	@FXML
	private ChoiceBox<String> CBPaymentMethod,CBbarber,CBview;

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
		this.handleWatch();
		//CreateGraphics(ResultChart);
	}

	private void handleWatch(){
		this.CBbarber.setOnAction(item->{
			this.handleAddGraphics();
		});
		this.CBPaymentMethod.setOnAction(item->{
			this.handleAddGraphics();
		});
		this.datePickerInitial.setOnAction(item->{
			this.handleAddGraphics();
		});
		this.datePickerEnd.setOnAction(item->{
			this.handleAddGraphics();
		});
		this.CBview.setOnAction(item->{
			this.handleAddGraphics();
		});
	}
	
	
	private void handleCreateLineGraph(){
		try{
			gridPane.getChildren().clear();
			
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("resultChartsField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			ResultChartsField controller = fxmlLoader.getController();
			String selectedProfessional = CBbarber.getSelectionModel().getSelectedItem();
			String selectedPayment = CBPaymentMethod.getSelectionModel().getSelectedItem();
			String initialDate = datePickerInitial.getValue().toString();
			String finalDate = datePickerEnd.getValue().toString();
			
			controller.createGraph(initialDate,
					finalDate,selectedProfessional,selectedPayment);
			
			gridPane.add(ap, 0, gridPane.getRowCount());
		}catch(Exception e){
			DialogMessage.show("Erro ao adicionar seção!","Houve um erro ao adicionar uma nova seção!", Alert.AlertType.ERROR);
			System.out.println(e.getMessage());
		}
		
	}
	
	@FXML
	private void handleAddGraphics() {
		try{
			String selectedView = CBview.getSelectionModel().getSelectedItem();
			if(selectedView.equals("Linhas")){
				this.handleCreateLineGraph();
				return;
			}
			this.handleCreateLineGraph();
		}catch (Exception err){
			System.out.println(err.getMessage());
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
			
			CBview.getItems().addAll("Linhas","Gráfico");
			CBview.setValue("Linhas");
			
			datePickerInitial.setValue(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
			datePickerEnd.setValue(LocalDate.now());
			

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
