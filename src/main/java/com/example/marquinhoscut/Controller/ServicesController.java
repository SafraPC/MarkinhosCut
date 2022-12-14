package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Components.ServiceField;
import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Dao.ServiceDao;
import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.Model.Services;
import com.example.marquinhoscut.Utils.Bar.AdminBar;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Comparator;

public class ServicesController extends AdminBar {
	public ServicesController(){

	}
	@FXML
	private Button exitButton,servicesButton,professionalButton,resultsButton;
	@FXML
	private TextField searchField;
	@FXML
	private GridPane gridPane;

	private ArrayList<ServiceField> controllers = new ArrayList();
	private ArrayList<Services> services = new ArrayList<>();


	@FXML
	void initialize(){
		handleNavigationBar(exitButton,servicesButton,professionalButton,resultsButton);
		populateSevices();
		onChange();
	}

	@FXML
	public void renderCreateServices(ActionEvent actionEvent) {
		goTo(servicesButton,"createService.fxml","Criar novo serviço");
	}

	private void populateSevices(){
		try{
			ServiceDao sDao = new ServiceDao();
			services.addAll(sDao.getListServices());
		}catch(Exception err){
			System.out.println(err.getMessage());
		}
	}

	private void handleCreatePane(String name, double priceService, boolean isActive, int id){
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("serviceField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			ServiceField controller = fxmlLoader.getController();
			controller.setName(name);
			controller.setPrice(priceService);
			controller.setActive(isActive);
			controller.setId(id);
			controllers.add(controller);
			gridPane.add(ap, 0, gridPane.getRowCount());

		}catch(Exception e){
			System.out.println(e.getMessage());
			DialogMessage.show("Erro ao adicionar seção!","Houve um erro ao adicionar uma nova seção!", Alert.AlertType.ERROR);
		}
	}

	@FXML
	void onChange() {
		gridPane.getChildren().clear();
		ArrayList<Services> filteredList = new ArrayList();
		filteredList.addAll(services);
		if(searchField.getText().length() >0){
			filteredList.removeIf(item->
					!item.getName().toLowerCase().contains(searchField.getText().toLowerCase()));
		}
		if(filteredList.size() > 0){
			filteredList.sort(Comparator.comparing(Services::getName));
			for (Services services : filteredList){
				handleCreatePane(services.getName(),services.getValue(),services.getIsActive(),services.getId());
			}
		}
	}

}
