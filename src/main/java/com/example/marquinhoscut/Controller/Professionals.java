package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.Utils.Bar.AdminBar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Professionals extends AdminBar {
	@FXML
	private Button exitButton,servicesButton,professionalButton,resultsButton;
	
	@FXML
	void initialize(){
		handleNavigationBar(exitButton,servicesButton,professionalButton,resultsButton);
	}
	public Professionals(){

	}
}
