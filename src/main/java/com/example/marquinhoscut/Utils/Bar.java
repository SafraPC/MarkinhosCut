package com.example.marquinhoscut.Utils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public abstract class Bar {
	
	
	abstract void navigate(String name);
	
	abstract void handleNavigationBar(Button...args);

}
