package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Controller.Home;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
	@FXML
	protected void handleSubmit(ActionEvent event) throws IOException {
		Stage stage = App.createStage(event);
		FXMLLoader root = App.createLoader("home");
		stage.setScene(new Scene(root.load()));
		stage.show();
	}
}
