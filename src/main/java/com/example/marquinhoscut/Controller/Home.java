package com.example.marquinhoscut.Controller;
import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Utils.Bar.BarberBar;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
	private ArrayList<TextField> fieldList = new ArrayList();
	private ArrayList<String> listBarber =  new ArrayList();
	private ObservableList<String> observablelistBarber,observablelistPaymentMethod;
	private ArrayList<String> listPaymentMethod =  new ArrayList();
	
	
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
	private ChoiceBox<String> CBservice;

	@FXML
	private void handleAddNewSection() {
		try{
			
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("sellingField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			SellingField controller = fxmlLoader.getController();
			controller.setGridParent(gridPane);
			
			fieldList.add(((TextField)ap.getChildren().get(3)));
			gridPane.add(ap, 0, gridPane.getRowCount());
			
		}catch(Exception e){
			DialogMessage.show("Erro ao adicionar seção!","Houve um erro ao adicionar uma nova seção!", Alert.AlertType.ERROR);
		}
	}
	
	@FXML
	void handleSubmit(ActionEvent event) {
		for(TextField arg: fieldList){
			System.out.println(arg.getText());
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
