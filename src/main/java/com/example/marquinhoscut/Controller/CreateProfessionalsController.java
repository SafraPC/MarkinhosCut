package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Utils.Bar.AdminBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CreateProfessionalsController extends AdminBar {

    private ProfessionalDao professionalDao = new ProfessionalDao();
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
        try {
            if(professionalDao.handleCreateProfessional(nameField.getText(),cpfField.getText())){
                goTo(back,"professionals.fxml","Profissionais");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
