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
	private static String admPassword;
	
	@Override
	public void start(Stage stage) throws IOException {
		Scene scene = new Scene(loadFXML("home"));
		stage.setScene(scene);
		stage.setTitle("Cadastrar nova venda");
		stage.setResizable(false);
		stage.show();
	}
	
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}
	
	public static boolean validateAdmPassword(String password){
	if(password.equals(admPassword)){
		return true;
	}
	return false;
	}
	public static void main(String[] args) {
		try{
			App.admPassword = args[0];
			launch();
		}catch (Exception err){
			if(args.length == 0){
				System.out.println("Crie um argumento com a senha do adm.");
			}
		}
	
	}
	
}
