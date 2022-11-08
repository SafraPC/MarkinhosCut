package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.Utils.Bar.AdminBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateProfessionalsController extends AdminBar {
    @FXML
    private Button exitButton,servicesButton,professionalButton,resultsButton,back;

    @FXML
    private TextField nameField,cpfField;
    @FXML
    void initialize(){
        handleNavigationBar(exitButton,servicesButton,professionalButton,resultsButton);
        back.setOnAction(event->{
            goTo(back,"professionals.fxml","Profissionais");
        });
    }

    @FXML
    public void handleCreateProfessional(ActionEvent actionEvent) {
        System.out.println(nameField.getText());
        System.out.println(cpfField.getText());
    }
}
