package com.example.marquinhoscut.Controller;
import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Utils.Bar.BarberBar;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Home extends BarberBar {
	@FXML
	private
	Button buttonAdm;
	@FXML
	private GridPane gridPane;
	private int lastGridIndex = 1;
	
	private ArrayList<TextField> fieldList = new ArrayList();
	
	
	private void handleDeleteRow(AnchorPane ap){
		try{
			int index = gridPane.getRowIndex(ap);
			gridPane.getChildren().remove(index-1);
			lastGridIndex--;
			System.out.println("--");
			System.out.println(index);
			System.out.println(lastGridIndex);
		}catch(Exception err){
			System.out.println(err);
		}
	
	}
	
	@FXML
	private void handleAddNewSection() {
		try{
			System.out.println(lastGridIndex);
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("sellingField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			
			fieldList.add(((TextField)ap.getChildren().get(3)));
			
			((Button)ap.getChildren().get(7)).setOnAction(buttonEvent -> {
				handleDeleteRow(ap);
			});

			gridPane.add(ap, 0, lastGridIndex);
			lastGridIndex++;
		}catch(Exception e){
			DialogMessage.show("Erro ao adicionar seção!","Houve um erro ao adicionar uma nova seção!", Alert.AlertType.ERROR);
		}
	}
	
	@FXML
	void handleSubmit(ActionEvent event) {
		for(TextField arg: fieldList){
			System.out.println(arg.getText());
		}
	}
	
	@FXML
	void initialize() {
		try{
			handleNavigationBar(buttonAdm);
			handleAddNewSection();
		}catch (Exception e){
		}
	}
	
	
}
