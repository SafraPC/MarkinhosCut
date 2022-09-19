package com.example.marquinhoscut;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
	
	@Override
	public void start(Stage stage) throws IOException {
		Scene scene = new Scene(loadFXML("home"));
		stage.setScene(scene);
		stage.setTitle("Bem-vindo!");
		stage.show();
	}
	
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}
	
}
