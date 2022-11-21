
package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Components.RegisterSellingDetailedField;
import com.example.marquinhoscut.Components.RegisterSellingField;
import com.example.marquinhoscut.Components.ResultChartsField;
import com.example.marquinhoscut.Dao.PaymentMethodDao;
import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Dao.SellingDao;
import com.example.marquinhoscut.Model.PaymentMethod;
import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.Model.Selling;
import com.example.marquinhoscut.Model.SellingDetailed;
import com.example.marquinhoscut.Utils.Bar.AdminBar;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class ResultsController extends AdminBar {

	private ArrayList<Professional> professionals = new ArrayList<>();
	private ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();

	ArrayList<Selling> listRegisterSelling = new ArrayList<>();
	ArrayList<SellingDetailed> listRegisterSellingDetailed= new ArrayList<>();

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
	
	
	private void handleCreateGraph(){
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
		}
	}
	
	private void handleCreateLineGraph(){
		gridPane.getChildren().clear();

		for(int i = 0; i < listRegisterSelling.size();i++) {
			int id = listRegisterSelling.get(i).getSellingId();
			
			String professional = listRegisterSelling.get(i).getProfessional();
			String payment = listRegisterSelling.get(i).getpaymentName();
			
			if(professional == null || professional.equals("Todos")){
				professional = "";
			}
			if(payment == null || payment.equals("Todos")){
				professional = "";
			}
			boolean isOdd = i%2 == 0 ? false : true;
			String date = listRegisterSelling.get(i).getSellingDate();
			Double total = listRegisterSelling.get(i).getTotal();
			handleAddRegister(id, professional, payment, date, total,isOdd);
		}
	}
	
	private void handleCreateLineDetailedGraph(){
		gridPane.getChildren().clear();

		for(int i = 0; i < listRegisterSellingDetailed.size();i++) {
			int id = listRegisterSellingDetailed.get(i).getSellingId();
			String name = listRegisterSellingDetailed.get(i).getServiceName();
			int qtd = listRegisterSellingDetailed.get(i).getQuantity();
			String professional = listRegisterSellingDetailed.get(i).getProfessional();
			String payment = listRegisterSellingDetailed.get(i).getpaymentName();
			
			if(professional == null || professional.equals("Todos")){
				professional = "";
			}
			if(payment == null || payment.equals("Todos")){
				professional = "";
			}
			
			boolean isOdd = i%2 == 0 ? false : true;
			String date = listRegisterSellingDetailed.get(i).getSellingDate();
			Double total = listRegisterSellingDetailed.get(i).getProductPrice();

			handleAddDetailedRegister(id, professional, payment, date, total,name,qtd,isOdd);
		}
	}
	
	public void handleAddRegister(int id, String professional, String payment, String date, Double total,boolean isOdd){

		try{
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registerSellingField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			RegisterSellingField controller = fxmlLoader.getController();
			
			if(isOdd){
				ap.setStyle("-fx-background-color: #f5f5f5");
			}
			
			controller.setProfessional(professional);
			controller.setPayment(payment);
			controller.setDate(date);
			controller.setTotal(total);
		
			gridPane.add(ap, 0, gridPane.getRowCount());
		}catch(Exception err){
			DialogMessage.show("Erro ao adicionar registros!","Houve um erro ao adicionar um novo registro!", Alert.AlertType.ERROR);
		}

	}
	
	public void handleAddDetailedRegister(int id, String professional, String payment, String date, Double total,
	                                      String name, int qtd, boolean isOdd){
		
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("registerSellingDetailedFIeld.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			RegisterSellingDetailedField controller = fxmlLoader.getController();
			
			if(isOdd){
				ap.setStyle("-fx-background-color: #FFF");
			}
			
			controller.setProfessional(professional);
			controller.setPayment(payment);
			controller.setDate(date);
			controller.setTotal(total);
			controller.setName(name);
			controller.setQtd(qtd+"");
			
			gridPane.add(ap, 0, gridPane.getRowCount());
		}catch(Exception err){
			DialogMessage.show("Erro ao adicionar registros!","Houve um erro ao adicionar um novo registro!", Alert.AlertType.ERROR);
		}
		
	}
	@FXML
	private void handleAddView() {
		try{
			String selectedView = CBview.getSelectionModel().getSelectedItem();
			if(selectedView.isEmpty()){
				return;
			}
			this.queryResults();
			toReceive.setText(this.sumtotal());
			
			if(selectedView.equals("Linhas")){
				this.handleCreateLineGraph();
				return;
			}
			if(selectedView.equals("Detalhes")){
				this.handleCreateLineDetailedGraph();
				return;
			}
			if(selectedView.equals("Gráfico")){
				this.handleCreateGraph();
			}
		}catch (Exception err){
			System.out.println(err);
		}
	}
	
	
	public void PopulateOptionsResults(){

		try{
			CBbarber.getItems().add("Todos");
			CBbarber.setValue("Todos");
			CBPaymentMethod.getItems().add("Todos");
			CBPaymentMethod.setValue("Todos");
			
			datePickerInitial.setValue(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
			datePickerEnd.setValue(LocalDate.now());

			ProfessionalDao professionalDao = new ProfessionalDao();
			professionals.addAll(professionalDao.getListProfessional());

			PaymentMethodDao paymentMethodDao = new PaymentMethodDao();
			paymentMethods.addAll(paymentMethodDao.getListPaymentMethod());

			
			CBview.getItems().addAll("Linhas","Detalhes","Gráfico");
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
			SellingDao sellingDao = new SellingDao();
			listRegisterSelling.clear();
			listRegisterSellingDetailed.clear();
			
			
			String dateInitial = datePickerInitial.getValue().toString();
			String dateFinal = datePickerEnd.getValue().toString();
			String selectedBarber = CBbarber.getSelectionModel().getSelectedItem();
			String selectedView = CBview.getSelectionModel().getSelectedItem();
			String selectedPaymentMethod = CBPaymentMethod.getSelectionModel().getSelectedItem();
			
			if(selectedBarber == null || selectedBarber.equals("Todos")){
				selectedBarber = "";
			}
			if(selectedPaymentMethod == null || selectedPaymentMethod.equals("Todos")){
				selectedPaymentMethod = "";
			}
			if(selectedView.equals("Detalhes")){
				listRegisterSellingDetailed.addAll(sellingDao.getDetailedListSelling(dateInitial,dateFinal,
						selectedBarber, selectedPaymentMethod));
				return;
			}
			listRegisterSelling.addAll(sellingDao.getListSelling(dateInitial,dateFinal,
					selectedBarber, selectedPaymentMethod));
					
		}catch (Exception err){
			DialogMessage.show("Erro ao adicionar registros!","Houve um erro ao adicionar um novo registro!", Alert.AlertType.ERROR);
		}

	}
	public String sumtotal(){
		try {
			double sumTotal=0;
			if(CBview.getSelectionModel().getSelectedItem() == null){
				return NumberFormat.getCurrencyInstance().format(sumTotal);
			}
			if(CBview.getSelectionModel().getSelectedItem().equals("Detalhes")){
				for(SellingDetailed selling:listRegisterSellingDetailed){
					if(selling.getProductPrice() > 0){
						sumTotal += selling.getProductPrice();
					}
				}
				return NumberFormat.getCurrencyInstance().format(sumTotal);
			}
			for(Selling selling:listRegisterSelling){
				if(selling.getTotal() > 0){
					sumTotal += selling.getTotal();
				}
			}
			return NumberFormat.getCurrencyInstance().format(sumTotal);

		}catch(Exception err){
		System.out.println(err.getMessage());
		return "";
		}

	}



}
