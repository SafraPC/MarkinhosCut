package com.example.marquinhoscut.Utils;

import javafx.scene.control.Button;

public class BarberBar extends Bar{
	
	@Override
	void navigate(String name) {
		System.out.println(name);
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
