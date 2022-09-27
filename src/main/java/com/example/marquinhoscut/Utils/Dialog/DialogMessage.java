package com.example.marquinhoscut.Utils.Dialog;

import javafx.scene.control.Alert;

public class DialogMessage {
	public DialogMessage(String title, String headerText, Alert.AlertType type){
		Alert dialog = new Alert(type);
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.show();
	}
}