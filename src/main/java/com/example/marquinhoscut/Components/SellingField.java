package com.example.marquinhoscut.Components;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SellingField {
	
	private GridPane gridParent;
	private ArrayList<SellingField> controllers = new ArrayList();
	
	@FXML
	private AnchorPane anchorId;
	
	@FXML
	private TextField qtdField,priceField;
	
	@FXML
	private ChoiceBox serviceCB;
	
	
	public void setGridParent(GridPane pane, ArrayList<SellingField> controllers){
		this.gridParent = pane;
		this.controllers = controllers;
	}
	
	public void handleDelete() {
		this.gridParent.getChildren().remove(anchorId);
		this.controllers.remove(this);
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
