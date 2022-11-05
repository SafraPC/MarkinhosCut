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


public class HomeController extends BarberBar {
	
	private String[] barbers = {
			"Leandro",
			"Rhuan",
			"Marcelo"
	};
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

	private ArrayList <String> professionalName = new ArrayList();
	
	
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
	private void handleAddNewSection() {
		try{
			
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("sellingField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			SellingField controller = fxmlLoader.getController();
			controller.setGridParent(gridPane,controllers);
			controllers.add(controller);
			gridPane.add(ap, 0, gridPane.getRowCount());
			
		}catch(Exception e){
			DialogMessage.show("Erro ao adicionar seção!","Houve um erro ao adicionar uma nova seção!", Alert.AlertType.ERROR);
		}
	}
	
	
	private boolean isFormValid(){
		try{
			for(SellingField fields: controllers){
				if( Integer.parseInt(fields.getQtdField().getText()) <= 0 ){
					System.out.println("Preencha corretamente o campo, Valor deve ser maior que 0.");
					return false;
				}else if (fields.getServiceCB().getSelectionModel().isEmpty()) {
					System.out.println("Preencha corretamente o campo Serviço.");
					return false;
				} else if (CBbarber.getSelectionModel().isEmpty()) {
					System.out.println("Preencha corretamente o campo Barbeiro.");
					return false;
				}else if (CBPaymentMethod.getSelectionModel().isEmpty()) {
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
			toReceive.setText(sumToReceive());
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
		double valueService = 0;
		for(SellingField fields: controllers){
			 valueService += fields.toReceive(fields.getQtdField(),fields.getPriceField());
		}
		System.out.println(NumberFormat.getCurrencyInstance().format(valueService));
		return NumberFormat.getCurrencyInstance().format(valueService);

	}
	@FXML
	void initialize() {
		try{
			handleNavigationBar(buttonAdm);
			handleAddNewSection();
			datePicker.setValue(LocalDate.now());
			loadChoiceBox();
		}catch (Exception e){
		}
	}
	
	
}
