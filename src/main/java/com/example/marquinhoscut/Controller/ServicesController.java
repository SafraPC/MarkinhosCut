package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Components.ServiceField;
import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Dao.ServiceDao;
import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.Model.Services;
import com.example.marquinhoscut.Utils.Bar.AdminBar;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ServicesController extends AdminBar {
	public ServicesController(){

	}
	@FXML
	private Button exitButton,servicesButton,professionalButton,resultsButton;
	private boolean preventSearch = false;
	@FXML
	private TextField searchField;
	@FXML
	private GridPane gridPane;

	private ArrayList<ServiceField> controllers = new ArrayList();
	private ArrayList<Services> services = new ArrayList<>();

	private void handleCreatePane(String name, double priceService){
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("serviceField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			ServiceField controller = fxmlLoader.getController();
			controller.setName(name);
			controller.setPrice(priceService);
			controllers.add(controller);
			gridPane.add(ap, 0, gridPane.getRowCount());

		}catch(Exception e){
			System.out.println(e.getMessage());
			DialogMessage.show("Erro ao adicionar seção!","Houve um erro ao adicionar uma nova seção!", Alert.AlertType.ERROR);
		}
	}
	private void populateSevices(){
		try{
			ServiceDao sDao = new ServiceDao();
			services.addAll(sDao.getListServices());
		}catch(Exception err){
			System.out.println(err.getMessage());
		}

	}
	@FXML
	void initialize(){
		handleNavigationBar(exitButton,servicesButton,professionalButton,resultsButton);
		populateSevices();
		onChange();
	}
	@FXML
	void onChange() {
		if(preventSearch){
			return;
		}
		gridPane.getChildren().clear();
		ArrayList<Services> filteredList = new ArrayList();
		filteredList.addAll(services);
		if(searchField.getText().length() >0){
			filteredList.removeIf(item->
					!item.getName().toLowerCase().contains(searchField.getText().toLowerCase()));
		}
		if(filteredList.size() > 0){
			filteredList.sort((a,b)->a.getName().compareTo(b.getName()));
			for (Services services : filteredList){
				handleCreatePane(services.getName(),services.getValue());
			}
		}
	}
}
