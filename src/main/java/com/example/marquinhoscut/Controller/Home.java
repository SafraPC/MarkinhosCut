package com.example.marquinhoscut.Controller;
import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Utils.Bar.BarberBar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Home extends BarberBar {
	@FXML
	private Button buttonAdm;
	@FXML private GridPane gridPane;
	
	private Pane paneContainer;
	private Label paneLabel;
	@FXML
	void initialize() {ad
		try{

			handleNavigationBar(buttonAdm);
		
			for (int i = 0; i < 2; i++) {
				FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("testee.fxml"));
				AnchorPane scene = fxmlLoader.load();
				AnchorPane ap = scene;
				gridPane.add(ap, 0, i);
			}
		}catch (Exception e){
		
		}

		
		
	}

}
