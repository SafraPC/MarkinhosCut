package com.example.marquinhoscut.Controller;
import com.example.marquinhoscut.Utils.BarberBar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Home extends BarberBar {
	@FXML
	private Button buttonAdm;
	
	@FXML
	void initialize(){
	handleNavigationBar(buttonAdm);
	}

}
