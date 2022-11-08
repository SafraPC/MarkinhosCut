package com.example.marquinhoscut.Utils.Bar;

import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminBar extends Bar{
	
	
	@Override
	void navigate(String name,Button scene) {
		if(name.equals(adminOptions.SERVICES.label)){
			goTo(scene,"services.fxml",adminOptions.SERVICES.label);
			return;
		}
		if(name.equals(adminOptions.EMPLOYEES.label)){
			goTo(scene,"professionals.fxml", adminOptions.EMPLOYEES.label);
			return;
		}
		if(name.equals(adminOptions.RESULTS.label)){
			goTo(scene,"results.fxml",adminOptions.RESULTS.label);
			return;
		}
		if(name.equals(adminOptions.EXIT.label)){
			goTo(scene,"home.fxml","Cadastrar nova venda");
		}
		
	}
	
	@Override
	public void handleNavigationBar(Button... args) {
		for (Button arg : args){
			arg.setOnAction(event -> {
				navigate(arg.getText(),arg);
			});
		}
	}
	
	public void goTo(Button buttonScene,String fxml,String title ){
		try{
			Stage stage = (Stage) buttonScene.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
			AnchorPane anchorPane = loader.load();
			Scene scene = new Scene(anchorPane);
			stage.setScene(scene);
			stage.setTitle(title);
			stage.show();
		}catch(Exception err){
			DialogMessage.show("Houve um erro!!!",err.getMessage(), Alert.AlertType.ERROR);
		}
		
	}
}
