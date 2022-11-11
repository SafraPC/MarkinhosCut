package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.Dao.ServiceDao;
import com.example.marquinhoscut.Utils.Bar.AdminBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CreateServicesController  extends AdminBar {
    private ServiceDao serviceDao = new ServiceDao();
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
        try {
            if(serviceDao.handleCreateService(nameField.getText(),Double.parseDouble(priceField.getText()))){
                goTo(back,"services.fxml","Serviços");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
