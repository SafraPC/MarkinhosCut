package com.example.marquinhoscut.Utils.Dialog;

import com.example.marquinhoscut.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;

public class DialogMessage {

	public static void show(String title, String headerText, Alert.AlertType type) {
		Alert dialog = new Alert(type);
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.show();
	}

	public static void errorMessage(String title, String headerText) {
		show(title, headerText, Alert.AlertType.ERROR);
	}

	public static void successMessage(String title, String headerText) {
		show(title, headerText, Alert.AlertType.INFORMATION);
	}

	public static boolean confirmationDialog(String title, String headerText) {
		Alert dialog = new Alert(Alert.AlertType.WARNING);
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.getDialogPane().getButtonTypes().removeAll(dialog.getButtonTypes());
		dialog.getDialogPane().getButtonTypes().addAll(new ButtonType("Não"), new ButtonType("Sim"));
		Optional<ButtonType> result = dialog.showAndWait();
		if (result.isPresent()) {
			try {
				if (result.get().getText().equals("Sim")) {
					return true;
				}
				return false;
			} catch (Exception ex) {
				DialogMessage.show("Erro", ex.getMessage(), Alert.AlertType.ERROR);
				return false;
			}
		}
		return false;
	}
}