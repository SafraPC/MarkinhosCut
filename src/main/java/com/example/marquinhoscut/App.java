package com.example.marquinhoscut;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
	
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("home.fxml"));
		AnchorPane page = fxmlLoader.load();
		Scene scene = new Scene(page);
		stage.setScene(scene);
		stage.setTitle("Bem-vindo!");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
}
