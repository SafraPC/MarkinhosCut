package com.example.marquinhoscut.Components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ServiceField {

	private String name;
	double price;

	private boolean isEditing = false;

	@FXML
	private TextField nameServiceField,priceServiceField;
	@FXML
	private Button deleteButton,editButton;
	@FXML
	private AnchorPane anchorId;
	
	@FXML
	private TextField qtdField;
	
	@FXML
	private TextField qtdField1;

	public void setName(String name) {
		this.name = name;
		this.nameServiceField.setText(name);
	}
	public void setPrice(Double price) {
		this.price = price;
		this.priceServiceField.setText(Double.toString(price));
	}
	@FXML
	void handleDelete() {
		if(isEditing){
			deleteButton.setText("Inativar");
			editButton.setText("Editar");

			nameServiceField.setText(name);
			priceServiceField.setText(Double.toString(price));
			handleInputs(true);
			isEditing = false;
		}
	}

	@FXML
	void handleEdit() {
		if(isEditing){
			deleteButton.setText("Inativar");
			editButton.setText("Editar");
			handleInputs(true);
			isEditing = false;
			setPrice(Double.parseDouble(priceServiceField.getText()));
			setName(nameServiceField.getText());
			return;
		}
		isEditing = true;
		handleInputs(false);
		editButton.setText("Salvar");
		deleteButton.setText("Cancelar");
	}
	private void handleInputs(boolean active){
		nameServiceField.setDisable(active);
		priceServiceField.setDisable(active);
	}

}
