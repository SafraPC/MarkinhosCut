package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.Utils.Bar.AdminBar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Services extends AdminBar {
	public Services(){
	
	}
	
	@FXML
	private Button exitButton,servicesButton,professionalButton,resultsButton;
	
	@FXML
	void initialize(){
		handleNavigationBar(exitButton,servicesButton,professionalButton,resultsButton);
	}
}
