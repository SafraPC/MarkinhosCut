package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Components.ProfessionalField;
import com.example.marquinhoscut.Components.SellingField;
import com.example.marquinhoscut.Utils.Bar.AdminBar;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Professionals extends AdminBar {
	
	private ArrayList<ProfessionalField> controllers = new ArrayList();
	
	@FXML
	private Button exitButton,servicesButton,professionalButton,resultsButton;
	@FXML
	private GridPane gridPane;
	
	private void handleCreatePane(){
		try{
			
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("professionalField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			ProfessionalField controller = fxmlLoader.getController();
			//controller.setGridParent(gridPane,controllers);
			controllers.add(controller);
			gridPane.add(ap, 0, gridPane.getRowCount());
			
		}catch(Exception e){
			DialogMessage.show("Erro ao adicionar seção!","Houve um erro ao adicionar uma nova seção!", Alert.AlertType.ERROR);
		}
	}
	
	@FXML
	void initialize(){
		handleNavigationBar(exitButton,servicesButton,professionalButton,resultsButton);
		for(int i = 0; i < 10; i++){
			handleCreatePane();
		}
	}
	public Professionals(){

	}
}
