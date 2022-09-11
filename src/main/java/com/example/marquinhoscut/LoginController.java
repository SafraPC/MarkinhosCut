package com.example.marquinhoscut;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
	@FXML
	protected void handleSubmit(ActionEvent event) throws IOException {
		Stage stage = App.createStage(event);
		FXMLLoader root = App.createLoader("logged");
		stage.setScene(new Scene(root.load()));
		LoggedController controller = root.getController();
		controller.setLoginLabel("teste");
		stage.show();
	}
}
