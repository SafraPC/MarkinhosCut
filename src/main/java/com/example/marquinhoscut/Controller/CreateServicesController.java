package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.Utils.Bar.AdminBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateServicesController  extends AdminBar {
    @FXML
    private Button exitButton,servicesButton,professionalButton,resultsButton,back;

    @FXML
    private TextField nameField,priceField;

    @FXML
    void initialize(){
        handleNavigationBar(exitButton,servicesButton,professionalButton,resultsButton);
        back.setOnAction(event->{
            goTo(back,"services.fxml","Serviços");
        });
    }

    @FXML
    public void handleCreateNewService(ActionEvent actionEvent) {
        System.out.println(nameField.getText());
        System.out.println(priceField.getText());
    }
}
