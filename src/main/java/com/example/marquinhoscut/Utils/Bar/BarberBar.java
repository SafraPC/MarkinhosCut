package com.example.marquinhoscut.Utils.Bar;
import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Optional;

public class BarberBar extends Bar{
	
	@Override
	void navigate(String name,Button scene) {
	 if(name.equals(barberOptions.ADM.label)){
		 admPressed(scene);
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
	
	private void admPressed(Button buttonScene){
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Login");
		dialog.setHeaderText("Insira a senha do administrador");
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		
		PasswordField pwd = new PasswordField();
		HBox content = new HBox();
		content.setAlignment(Pos.CENTER_LEFT);
		content.setSpacing(10);
		content.getChildren().addAll(new Label("Senha:"), pwd);
		dialog.getDialogPane().setContent(content);
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				return pwd.getText();
			}
			return null;
		});
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			if(App.validateAdmPassword(result.get())){
				try{
					DialogMessage.show("Logado","Bem-vindo!", Alert.AlertType.INFORMATION);
					Stage stage = (Stage) buttonScene.getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(App.class.getResource("services.fxml"));
					AnchorPane anchorPane = loader.load();
					Scene scene = new Scene(anchorPane);
					
					stage.setScene(scene);
					stage.show();

				}catch(Exception ex){
					DialogMessage.show("Erro",ex.getMessage(), Alert.AlertType.ERROR);
					return;
				}
				return;
			}
			DialogMessage.show("Senha inválida","Senha incorreta!", Alert.AlertType.ERROR);
		}
	}
}
