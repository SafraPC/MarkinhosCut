package com.example.marquinhoscut.Utils.Bar;
import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.util.Optional;

public class BarberBar extends Bar{
	
	@Override
	void navigate(String name) {
	 if(name.equals(barberOptions.ADM.label)){
		 admPressed();
	 }
	}
	
	@Override
	public void handleNavigationBar(Button... args) {
		for (Button arg : args){
			arg.setOnAction(event -> {
				navigate(arg.getText());
			});
		}
	}
	
	private void admPressed(){
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
				DialogMessage.show("Logado","Bem-vindo!", Alert.AlertType.INFORMATION);
				return;
			}
			DialogMessage.show("Senha inválida","Senha incorreta!", Alert.AlertType.ERROR);
		}
	}
}
