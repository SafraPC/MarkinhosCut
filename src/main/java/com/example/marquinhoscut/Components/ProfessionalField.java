package com.example.marquinhoscut.Components;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ProfessionalField {
	
	private String name,cpf;
	private boolean isEditing = false;
	
	
	@FXML
	private TextField nameField,cpfField;
	@FXML
	private Button deleteButton,editButton;
	
	

	@FXML
	void handleDelete() {
		if(isEditing){
			deleteButton.setText("Inativar");
			editButton.setText("Editar");
			
			nameField.setText(name);
			cpfField.setText(cpf);
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
			setCpf(cpfField.getText());
			setName(nameField.getText());
			return;
		}
		isEditing = true;
		handleInputs(false);
		editButton.setText("Salvar");
		deleteButton.setText("Cancelar");
	}
	
	private void handleInputs(boolean active){
		cpfField.setDisable(active);
		nameField.setDisable(active);
	}
	
	public void setName(String name) {
		this.name = name;
		this.nameField.setText(name);
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
		this.cpfField.setText(cpf);
	}

}
