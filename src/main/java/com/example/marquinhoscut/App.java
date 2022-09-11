package com.example.marquinhoscut;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
	
	static Scene scene;
	
	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("login"));
		stage.setScene(scene);
		stage.setTitle("Tela de Login");
		stage.show();
	}
	
	static void setRoot(String fxml) throws IOException {
		scene.setRoot(createLoader(fxml).load());
	}
	
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static FXMLLoader createLoader(String fxml) throws IOException{
		FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml+".fxml"));
		return loader;
	}
	
	public static void main(String[] args) {
		launch();
	}
	
}
