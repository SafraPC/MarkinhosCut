package com.example.marquinhoscut.Controller;
import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Utils.Bar.BarberBar;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Home extends BarberBar {
	@FXML
	private Button buttonAdm;
	@FXML private GridPane gridPane;
	int lastGridIndex = 1;
	
	private ArrayList<TextField> fieldList = new ArrayList();
	
	@FXML
	void handleAddNewSection(ActionEvent event) {
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("sellingField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			
			fieldList.add(((TextField)ap.getChildren().get(3)));
			gridPane.add(ap, 0, lastGridIndex);
			lastGridIndex++;
		}catch(Exception e){
		
		}
	}
	@FXML
	void initialize() {
		try{
			handleNavigationBar(buttonAdm);
		}catch (Exception e){
		}
	}
	
	@FXML
	void handleSubmit(ActionEvent event) {
		//gridPane.getChildren().remove(gridPane.getChildren().get(selectedDeleteButton));
		for(TextField arg: fieldList){
			System.out.println(arg.getText());
		}
	}
}
