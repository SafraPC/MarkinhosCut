package com.example.marquinhoscut.Components;
import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.Model.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.text.NumberFormat;
import java.util.ArrayList;

public class SellingField {
	private String[] services = {
			"Cabelo",
			"Barba",
			"Sobrancelha"
	};
	private ArrayList<Services> listServices =  new ArrayList();
	private ObservableList<Services> observablelistServices;
	private ArrayList<SellingField> controllers = new ArrayList();
	private GridPane gridParent;

	
	@FXML
	private AnchorPane anchorId;
	
	@FXML
	private TextField qtdField,priceField;
	
	@FXML
	private ChoiceBox<String> serviceCB;

	@FXML
	public void loadChoiceBox() {

		listServices.add(new Services("Corte",25,true));
		listServices.add(new Services("Sobracelha",10,true));
		listServices.add(new Services("Barba",20,true));
		listServices.add(new Services("Mechas",35,true));

		for(int i=0; i< listServices.size(); i++){
			serviceCB.getItems().add(listServices.get(i).getName());
		}
		serviceCB.setTooltip(new Tooltip("Selecione um serviço"));
//		observablelistServices = FXCollections.observableArrayList(listServices);
//		serviceCB.setItems(observablelistServices);
	}
	
	@FXML
	private void initialize(){
		loadChoiceBox();
		priceField.setEditable(false);
	}
	
	public void handleDelete() {
		this.gridParent.getChildren().remove(anchorId);
		this.controllers.remove(this);
	}

	public void editValue(ActionEvent event){
		if(priceField.isEditable()){
			priceField.setEditable(false);
		}
		else{
			priceField.setEditable(true);
		}
	}
	public void getKeyboard(ActionEvent event){

	}
	public void getValue(ActionEvent event){
		double valueService;
		int index = serviceCB.getSelectionModel().getSelectedIndex();
		valueService = listServices.get(index).getValue();
		priceField.setText(Double.toString(valueService));

	}
	public double toReceive(TextField priceField,TextField qtdField){
		double price = Double.parseDouble(priceField.getText());
		double qtd = Double.parseDouble(qtdField.getText());
		return qtd *price;
	}
	public void setGridParent(GridPane pane, ArrayList<SellingField> controllers){
		this.gridParent = pane;
		this.controllers = controllers;
	}
	
	public TextField getQtdField(){
		return qtdField;
	}
	
	public TextField getPriceField(){
		return priceField;
	}
	
	public ChoiceBox getServiceCB(){
		return serviceCB;
	}


}
