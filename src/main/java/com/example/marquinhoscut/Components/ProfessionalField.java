package com.example.marquinhoscut.Components;
import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Utils.Bar.AdminBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessionalField extends AdminBar {
	
	private String name,cpf,inactiveButtonName;
	private int id;

	private boolean isActivated = false;
	private boolean isEditing = false;
	private ProfessionalDao professionalDao = new ProfessionalDao();
	
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
		try {
			if(professionalDao.handleChangeProfessionalStatus(!isActivated,id)){
				setActivated(!isActivated);
				inactiveButtonName = isActivated ? "Inativar" : "Ativar";
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	@FXML
	void handleEdit() {
		correctButtonStatus();
		if(isEditing){
			try {
				if(professionalDao.handleEditProfessional(nameField.getText(),cpfField.getText(),id)){
					setCpf(cpfField.getText());
					setName(nameField.getText());
					return;
				}
				setCpf(cpf);
				setName(name);
			} catch (Exception e) {
				//failQ
			}finally {
				isEditing = false;
			}
			return;
		}
		isEditing = true;
		handleInputs(false);
		editButton.setText("Salvar");
		deleteButton.setText("Cancelar");
	}



	public void setActivated(boolean activated) {
		isActivated = activated;
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

	public void setId(int id) {
		this.id = id;
	}
}
