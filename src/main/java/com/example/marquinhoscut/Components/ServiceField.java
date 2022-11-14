package com.example.marquinhoscut.Components;

import com.example.marquinhoscut.Dao.ServiceDao;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class ServiceField {

	private String name,inactiveButtonName;
	private int id;
	private boolean isActive;
	private double price;

	private boolean isEditing = false;

	private ServiceDao serviceDao = new ServiceDao();

	@FXML
	private TextField nameServiceField,priceServiceField;
	@FXML
	private Button deleteButton,editButton;

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
			nameServiceField.setText(name);
			priceServiceField.setText(String.valueOf(price));
			isEditing = false;
			return;
		}
		try {
			if(DialogMessage.confirmationDialog("Deseja realmente "+(isActive?"inativar":"ativar")+": "+name+" ?","Confirme sua ação")){
				if(serviceDao.handleChangeServiceStatus(!isActive,id)){
					setActive(!isActive);
				}
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
				if(serviceDao.handleEditService(nameServiceField.getText(),Double.parseDouble(priceServiceField.getText()),id)){
					setPrice(Double.parseDouble(priceServiceField.getText()));
					setName(nameServiceField.getText());
					return;
				}
			} catch (Exception e) {
				setPrice(price);
				setName(name);
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
	private void handleInputs(boolean active){
		nameServiceField.setDisable(active);
		priceServiceField.setDisable(active);
	}

	public void setName(String name) {
		this.name = name;
		this.nameServiceField.setText(name);
	}

	public void setPrice(Double price) {
		this.price = price;
		this.priceServiceField.setText(Double.toString(price));
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setActive(boolean active) {
		isActive = active;
		inactiveButtonName = active ? "Inativar" : "Ativar";
		this.deleteButton.setText(inactiveButtonName);
	}

}
