package com.example.marquinhoscut.Utils;

import javafx.scene.control.Button;

import java.util.Locale;

public class BarberBar extends Bar{
	
	@Override
	void navigate(String name) {
	 if(equalRoute(name,barberOptions.ADMINISTRADOR)){
		
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
}
