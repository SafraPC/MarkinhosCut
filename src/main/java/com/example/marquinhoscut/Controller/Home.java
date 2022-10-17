package com.example.marquinhoscut.Controller;
import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Utils.Bar.BarberBar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Home extends BarberBar {
	@FXML
	private Button buttonAdm;
	@FXML private GridPane gridPane;
	
	@FXML
	void initialize() {
		try{

			handleNavigationBar(buttonAdm);
		
			for (int i = 1; i < 5; i++) {
				FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("sellingField.fxml"));
				AnchorPane scene = fxmlLoader.load();
				AnchorPane ap = scene;
				gridPane.add(ap, 0, i);
			}
		}catch (Exception e){
		
		}
	}
}
