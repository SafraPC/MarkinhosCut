package com.example.marquinhoscut.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class SellingField {
	
	private GridPane gridParent;
	
	@FXML
	private AnchorPane anchorId;
	
	public void setGridParent(GridPane pane){
		this.gridParent = pane;
	}
	
	public void handleDelete() {
		this.gridParent.getChildren().remove(anchorId);
	}
}
