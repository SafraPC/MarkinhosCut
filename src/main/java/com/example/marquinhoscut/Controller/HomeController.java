package com.example.marquinhoscut.Controller;
import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Components.SellingField;
import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.Utils.Bar.BarberBar;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Callable;


public class HomeController extends BarberBar {
	private String[] payments = {
			"Cartão de Crédito",
			"Dinheiro",
			"Pix",
			"Débito"
	};
	private ArrayList<Professional> professionals =  new ArrayList();

	private ObservableList<String> observablelistPaymentMethod;
	private ArrayList<String> listPaymentMethod =  new ArrayList();
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
			controller.setParentCallback( new Thread(()->toReceive.setText(sumToReceive())));
			controller.setGridParent(gridPane,controllers);
			controllers.add(controller);
			gridPane.add(ap, 0, gridPane.getRowCount());
			
		}catch(Exception e){
			DialogMessage.show("Erro ao adicionar seção!","Houve um erro ao adicionar uma nova seção!", Alert.AlertType.ERROR);
			System.out.println(e.getMessage());
		}
	}
	
	private boolean isFormValid(){
		try{
			for(SellingField fields: controllers){
				if( Integer.parseInt(fields.getQtdField().getText()) <= 0 ){
					System.out.println("Preencha corretamente o campo, Valor deve ser maior que 0.");
					return false;
				}
				if (fields.getServiceCB().getSelectionModel().isEmpty()) {
					System.out.println("Preencha corretamente o campo Serviço.");
					return false;
				}
				if (CBbarber.getSelectionModel().isEmpty()) {
					System.out.println("Preencha corretamente o campo Barbeiro.");
					return false;
				}
				if (CBPaymentMethod.getSelectionModel().isEmpty()) {
					System.out.println("Preencha corretamente o campo Método de Pagamento.");
					return false;
				}
			}
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
		return true;
	}
	
	@FXML
	void handleSubmit() {
		if(isFormValid()){
			System.out.println("deu certo");
		}
	}

	@FXML
	void loadChoiceBox() {

		try{
			ProfessionalDao pDao = new ProfessionalDao();
			professionals.addAll(pDao.getListProfessional());
		}catch(Exception err){
			System.out.println(err.getMessage());
		}
		for(Professional professional : professionals ){
			CBbarber.getItems().add(professional.getName());
		}

		for(String payment : payments ){
			listPaymentMethod.add(payment);
		}
	
		observablelistPaymentMethod = FXCollections.observableArrayList(listPaymentMethod);
		CBPaymentMethod.setItems(observablelistPaymentMethod);
	}

	@FXML
	public String sumToReceive() {
		try{
			double valueService = 0;
			for(SellingField fields: controllers){
				valueService += fields.toReceive(fields.getQtdField(),fields.getPriceField());
			}
			System.out.println(NumberFormat.getCurrencyInstance().format(valueService));
			return NumberFormat.getCurrencyInstance().format(valueService);
		}catch(Exception err){
			System.out.println(err.getMessage());
			return "";
		}
	}
}
