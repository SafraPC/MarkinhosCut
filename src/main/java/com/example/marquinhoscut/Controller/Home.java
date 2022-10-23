package com.example.marquinhoscut.Controller;
import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Components.SellingField;
import com.example.marquinhoscut.Utils.Bar.BarberBar;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.time.LocalDate;
import java.util.ArrayList;


public class Home extends BarberBar {
	
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
	private ArrayList<String> listBarber =  new ArrayList();
	private ObservableList<String> observablelistBarber,observablelistPaymentMethod;
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
				if(Integer.parseInt(fields.getQtdField().getText()) <= 0){
					System.out.println("A quantidade tem que ser maior que 0");
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
		
		for(String barber : barbers ){
			listBarber.add(barber);
		}
		for(String payment : payments ){
			listPaymentMethod.add(payment);
		}
		
		observablelistBarber = FXCollections.observableArrayList(listBarber);
		CBbarber.setItems(observablelistBarber);
	
		observablelistPaymentMethod = FXCollections.observableArrayList(listPaymentMethod);
		CBPaymentMethod.setItems(observablelistPaymentMethod);
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
