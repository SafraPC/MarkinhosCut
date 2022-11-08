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
	
	private String name,cpf,inactiveButtonName;

	private boolean isActivated = false;
	private boolean isEditing = false;
	
	@FXML
	private TextField nameField,cpfField;
	@FXML
	private Button deleteButton,editButton;


	private void handleInputs(boolean active){
		cpfField.setDisable(active);
		nameField.setDisable(active);
	}

	private void correctButtonStatus(){
		if(isEditing){
			deleteButton.setText(inactiveButtonName);
			editButton.setText("Editar");
			handleInputs(true);
		}
	}
	@FXML
	void handleDelete() {
		correctButtonStatus();
		if(isEditing){
			nameField.setText(name);
			cpfField.setText(cpf);
			isEditing = false;
			return;
		}

	}
	
	@FXML
	void handleEdit() {
		correctButtonStatus();
		if(isEditing){
			setCpf(cpfField.getText());
			setName(nameField.getText());
			isEditing = false;
			return;
		}
		isEditing = true;
		handleInputs(false);
		editButton.setText("Salvar");
		deleteButton.setText("Cancelar");
	}

	public void setActivated(boolean activated) {
		isActivated = activated;
		System.out.println(activated);
		inactiveButtonName = activated ? "Inativar" : "Ativar";
		this.deleteButton.setText(inactiveButtonName);
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
