package com.example.marquinhoscut;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoggedController extends Parent {

	@FXML
	private Label loginLabel,passwordLabel;
	
	public void setLoginLabel(String login){
		this.loginLabel.setText(login);
	}
	public void setPasswordLabel(String login){
		this.passwordLabel.setText(login);
	}
}
