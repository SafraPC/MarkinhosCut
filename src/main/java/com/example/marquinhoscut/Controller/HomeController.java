package com.example.marquinhoscut.Controller;
import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Components.SellingField;
import com.example.marquinhoscut.Dao.PaymentMethodDao;
import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Dao.SellingDao;
import com.example.marquinhoscut.Dao.ServiceDao;
import com.example.marquinhoscut.Model.PaymentMethod;
import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.Model.Services;
import com.example.marquinhoscut.Utils.Bar.BarberBar;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Callable;


public class HomeController extends BarberBar {

	private ArrayList<Professional> professionals =  new ArrayList();
	private ArrayList<Services> services = new ArrayList();
	private SellingDao sellingDao = new SellingDao();

	private ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();
	private ArrayList<SellingField> controllers = new ArrayList();

	@FXML
	private Button buttonAdm;
	@FXML
	private GridPane gridPane;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ChoiceBox<String> CBbarber;
	@FXML
	private ChoiceBox<String> CBPaymentMethod;
	@FXML
	public Label toReceive;


	@FXML
	void initialize() {
		try{
			handleNavigationBar(buttonAdm);
			handleAddNewSection();
			datePicker.setValue(LocalDate.now());
			loadChoiceBox();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	@FXML
	private void handleAddNewSection() {
		try{
			
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("sellingField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			SellingField controller = fxmlLoader.getController();
			controller.setParentCallback( new Thread(()->toReceive.setText(sumToReceive(false))));
			controller.setGridParent(gridPane,controllers);
			controllers.add(controller);
			gridPane.add(ap, 0, gridPane.getRowCount());
			
		}catch(Exception e){
			DialogMessage.show("Erro ao adicionar seção!","Houve um erro ao adicionar uma nova seção!", Alert.AlertType.ERROR);
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void handleSubmit() {
		if(!isFormValid()){
			return;
		}
		Professional selectedBarber = null;
		for(Professional professional : professionals){
			if(professional.getName().equals(CBbarber.getSelectionModel().getSelectedItem())){
				selectedBarber = professional;
			}
		}
		String paymentType = CBPaymentMethod.getSelectionModel().getSelectedItem();
		double total = Double.parseDouble(sumToReceive(true));
		String dateValue = datePicker.getValue().toString();

		try {
			int sellingId = sellingDao.handleSelling(selectedBarber.getCpf(),paymentType,total,dateValue);
			handleQuantitySubmit(sellingId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void handleQuantitySubmit(int id){
		if(id == -1){
			System.out.println("sem id");
			return;
		}
		try{
			boolean success = true;
			for(SellingField fields: controllers) {
				int serviceId = -1;
				int qtd = Integer.parseInt(fields.getQtdField().getText());
				double price = Double.parseDouble(fields.getPriceField().getText());
				for(Services service : services){
					if(service.getName().equals(fields.getServiceCB().getSelectionModel().getSelectedItem().toString())){
						serviceId = service.getId();
						break;
					}
				}
				if(serviceId == -1){
					break;
				}
				success = sellingDao.handleSellingService(id, serviceId,qtd,price);
				if(!success){
					break;
				}
			}
			if(success){
				gridPane.getChildren().clear();
				controllers.clear();
			}
		}catch (Exception err){
			System.out.println(err.getMessage());
		}

	}

	@FXML
	public String sumToReceive(boolean pure) {
		try{
			double valueService = 0;
			for(SellingField fields: controllers){
				valueService += fields.toReceive(fields.getQtdField(),fields.getPriceField());
			}
			if(pure){
				return valueService+"";
			}
			return NumberFormat.getCurrencyInstance().format(valueService);
		}catch(Exception err){
			System.out.println(err.getMessage());
			return "";
		}
	}

	private boolean isFormValid(){
		try{

			if (CBbarber.getSelectionModel().isEmpty()) {
				DialogMessage.errorMessage("Erro de validação!","Informe o profissional que realizou a venda!");
				return false;
			}
			if (CBPaymentMethod.getSelectionModel().isEmpty()) {
				DialogMessage.errorMessage("Erro de validação!","Informe o tipo de pagamento!");
				return false;
			}
			if(controllers.isEmpty()){
				DialogMessage.errorMessage("Erro de validação!","É necessário conter pelo menos um serviço para iniciar a venda!");
				return false;
			}
			if(datePicker.getValue() == null){
				DialogMessage.errorMessage("Erro de validação!","Insira uma data válida!");
				return false;
			}
			if(toReceive.getText().isEmpty() || (!sumToReceive(true).isEmpty() &&
					Double.parseDouble(sumToReceive(true)) <= 0 )){
				DialogMessage.errorMessage("Erro de validação!","Valor de venda inválido!!");
				return false;
			}
			for(SellingField fields: controllers){
				if(!fields.getQtdField().getText().isEmpty() && Integer.parseInt(fields.getQtdField().getText()) <= 0 ){
					DialogMessage.errorMessage("Erro de validação!","Foi identificada uma quantidade vazia!");
					return false;
				}
				if (fields.getPriceField().getText().isEmpty()) {
					DialogMessage.errorMessage("Erro de validação!","Foi identificado um preço vazio!");
					return false;
				}
				if(fields.getServiceCB().getSelectionModel().isEmpty()){
					DialogMessage.errorMessage("Erro de validação!","Foi identificado um serviço em Branco!");
					return false;
				}
			}
		}catch (Exception err){
			System.out.println(err.getMessage()+"aq");
			DialogMessage.errorMessage("Excessão!",err.getMessage());
			return false;
		}
		return true;
	}

	@FXML
	void loadChoiceBox() {

		try{
			ProfessionalDao pDao = new ProfessionalDao();
			professionals.addAll(pDao.getListProfessional());

			PaymentMethodDao payDao = new PaymentMethodDao();
			paymentMethods.addAll(payDao.getListPaymentMethod());

			ServiceDao serviceDao = new ServiceDao();
			services.addAll(serviceDao.getListServices());

		}catch(Exception err){
			System.out.println(err.getMessage());
		}

		for(Professional professional : professionals ){
			if(professional.isActive()){
				CBbarber.getItems().add(professional.getName());
			}

		}
		for(PaymentMethod paymentMethod : paymentMethods ){
			CBPaymentMethod.getItems().add(paymentMethod.getName());
		}
	}
}
