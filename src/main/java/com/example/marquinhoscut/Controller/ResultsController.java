
package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Components.ProfessionalField;
import com.example.marquinhoscut.Components.RegisterSellingField;
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

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;

public class ResultsController extends AdminBar {

	private ArrayList<RegisterSellingField> controllers = new ArrayList();
	private ArrayList<Professional> professionals = new ArrayList<>();
	private ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();

	ArrayList<Selling> listRegisterSelling = new ArrayList<>();

	@FXML
	private GridPane gridPane;
	@FXML
	private DatePicker datePickerInitial,datePickerEnd;

	@FXML
	private ChoiceBox<String> CBPaymentMethod,CBbarber,CBview;

	@FXML
	private Label toReceive;

	@FXML
	private Button exitButton,servicesButton,professionalButton,resultsButton;
	
	@FXML
	void initialize(){
		handleNavigationBar(exitButton,servicesButton,professionalButton,resultsButton);
		PopulateOptionsResults();
		handleWatch();

		handleAddView();

	}

	private void handleWatch(){
		this.CBbarber.setOnAction(item->{
			this.handleAddView();
		});
		this.CBPaymentMethod.setOnAction(item->{
			this.handleAddView();
		});
		this.datePickerInitial.setOnAction(item->{
			this.handleAddView();
		});
		this.datePickerEnd.setOnAction(item->{
			this.handleAddView();
		});
		this.CBview.setOnAction(item->{
			this.handleAddView();
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
	public void handleAddRegister(int id, String professional, String payment, String date, Double total){

		try{
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("RegisterSellingField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			RegisterSellingField controller = fxmlLoader.getController();
			controller.setId(id);
			controller.setProfessional(professional);
			controller.setPayment(payment);
			controller.setDate(date);
			controller.setTotal(total);
			controllers.add(controller);

			gridPane.add(ap, 0, gridPane.getRowCount());
		}catch(Exception err){
			DialogMessage.show("Erro ao adicionar registros!","Houve um erro ao adicionar uma nova registros!", Alert.AlertType.ERROR);
			System.out.println(err.getMessage());
		}

	}
	@FXML
	private void handleAddView() {
		try{
			queryResults();
			String selectedView = CBview.getSelectionModel().getSelectedItem();
			if(selectedView.equals("Linhas")){
				gridPane.getChildren().clear();
				System.out.println("entrei");
				for(Selling selling : listRegisterSelling) {
					int id = selling.getSellingId();
					String professional = selling.getProfessional();
					String payment = selling.getpaymentName();
					String date = selling.getSellingDate();
					Double total = selling.getTotal();
					handleAddRegister(id, professional, payment, date, total);
				}

				return;
			}
			if(selectedView.equals("Gráfico")){
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
			datePickerInitial.setValue(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
			datePickerEnd.setValue(LocalDate.now());

			ProfessionalDao professionalDao = new ProfessionalDao();
			professionals.addAll(professionalDao.getListProfessional());

			PaymentMethodDao paymentMethodDao = new PaymentMethodDao();
			paymentMethods.addAll(paymentMethodDao.getListPaymentMethod());

			
			CBview.getItems().addAll("Linhas","Gráfico");
			CBview.setValue("Linhas");
			

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
	private void queryResults() throws SQLException {
		try {
			System.out.println("hello");
			SellingDao sellingDao = new SellingDao();
			listRegisterSelling.clear();
			listRegisterSelling.addAll(sellingDao.getListSelling(datePickerInitial.getValue().toString(),
					datePickerEnd.getValue().toString(),
					CBbarber.getSelectionModel().getSelectedItem(),
					CBPaymentMethod.getSelectionModel().getSelectedItem()));
		}catch (Exception err){
			DialogMessage.show("Erro ao adicionar registros!","Houve um erro ao adicionar uma nova registros!", Alert.AlertType.ERROR);
			System.out.println(err);
		}

	}
	public String Sumtotal(){
		try {
			double sumTotal=0;
			for(Selling selling:listRegisterSelling){
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
