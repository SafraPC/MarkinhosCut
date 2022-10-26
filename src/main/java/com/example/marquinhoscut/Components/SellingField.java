package com.example.marquinhoscut.Components;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SellingField {
	private String[] services = {
			"Cabelo",
			"Barba",
			"Sobrancelha"
	};
	private ArrayList<String> listServices =  new ArrayList();
	private ObservableList<String> observablelistServices;
	private ArrayList<SellingField> controllers = new ArrayList();
	private GridPane gridParent;

	
	@FXML
	private AnchorPane anchorId;
	
	@FXML
	private TextField qtdField,priceField;
	
	@FXML
	private ChoiceBox serviceCB;

	@FXML
	public void loadChoiceBox() {
		for (String services : services) {
			listServices.add(services);
		}

		observablelistServices = FXCollections.observableArrayList(listServices);
		serviceCB.setItems(observablelistServices);
	}
	
	@FXML
	private void initialize(){
		loadChoiceBox();
	}
	
	public void handleDelete() {
		this.gridParent.getChildren().remove(anchorId);
		this.controllers.remove(this);
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
