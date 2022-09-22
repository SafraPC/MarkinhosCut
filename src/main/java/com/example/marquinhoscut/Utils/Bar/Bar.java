package com.example.marquinhoscut.Utils.Bar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public abstract class Bar {
	
	public enum adminOptions{
	SAIR,PROFISSIONAIS,SERVIÇOS,RESULTADOS;
	}
	
	public enum barberOptions{
		ADMINISTRADOR
	}
	
	public Boolean equalRoute(String name,adminOptions option){
		return name.toUpperCase().equals(option.toString());
	}
	
	public Boolean equalRoute(String name,barberOptions option){
		return name.toUpperCase().equals(option.toString());
	}
	
	abstract void navigate(String name);
	
	abstract void handleNavigationBar(Button...args);

}
