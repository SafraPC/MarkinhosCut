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
	@FXML
	private
	Button buttonAdm;
	@FXML
	private GridPane gridPane;
	
	private ArrayList<TextField> fieldList = new ArrayList();
	private ArrayList<String> listBarber =  new ArrayList();
	private ObservableList<String> observablelistBarber;
	private ArrayList<String> listPaymentMethod =  new ArrayList();
	private ObservableList<String> observablelistPaymentMethod;
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

			
			fieldList.add(((TextField)ap.getChildren().get(3)));
			((Button)ap.getChildren().get(7)).setOnAction(buttonEvent -> {
				
				gridPane.getChildren().remove(ap);
			});
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
		listBarber.add("Marcelo");
		listBarber.add("Rhuan");
		listBarber.add("Marcos");
		listBarber.add("Leadrin");
		observablelistBarber = FXCollections.observableArrayList(listBarber);
		CBbarber.setItems(observablelistBarber);
		listPaymentMethod.add("Cartão de Crédito");
		listPaymentMethod.add("Dinheiro");
		listPaymentMethod.add("Pix");
		listPaymentMethod.add("Debito");
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
