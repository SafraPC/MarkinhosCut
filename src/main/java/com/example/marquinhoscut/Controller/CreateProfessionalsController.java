package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.Utils.Bar.AdminBar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CreateProfessionalsController extends AdminBar {
    @FXML
    private Button exitButton,servicesButton,professionalButton,resultsButton,back;

    @FXML
    void initialize(){
        handleNavigationBar(exitButton,servicesButton,professionalButton,resultsButton);
        back.setOnAction(event->{
            goTo(back,"professionals.fxml","Profissionais");
        });
    }
}
