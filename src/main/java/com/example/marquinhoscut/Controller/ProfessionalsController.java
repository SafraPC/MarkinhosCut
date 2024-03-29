package com.example.marquinhoscut.Controller;

import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Components.ProfessionalField;
import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Model.Professional;
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

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;

public class ProfessionalsController extends AdminBar {
	private ArrayList<ProfessionalField> controllers = new ArrayList();
	private ArrayList<Professional> professionals = new ArrayList<>();
	private boolean preventSearch = false;
	
	@FXML
	private Button exitButton,servicesButton,professionalButton,resultsButton;
	@FXML
	private GridPane gridPane;
	@FXML
	private TextField searchField;

	@FXML
	void initialize(){
		handleNavigationBar(exitButton,servicesButton,professionalButton,resultsButton);
		populateProfessionals();
		onChange();

	}

	@FXML
	void renderCreateProfessionals(ActionEvent event) {
		goTo(professionalButton,"createProfessional.fxml","Criar novo profissional");
	}

	public void populateProfessionals(){
		try{
			ProfessionalDao pDao = new ProfessionalDao();
			professionals.addAll(pDao.getListProfessional());
		}catch(Exception err){
			System.out.println(err.getMessage());
		}

	}
	private void handleCreatePane(String name, String cpf, int id,boolean isActivated){
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("professionalField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			ProfessionalField controller = fxmlLoader.getController();
			controller.setName(name);
			controller.setCpf(cpf);
			controller.setId(id);
			controller.setActivated(isActivated);
			controllers.add(controller);
			gridPane.add(ap, 0, gridPane.getRowCount());
			
		}catch(Exception e){
			DialogMessage.show("Erro ao adicionar seção!","Houve um erro ao adicionar uma nova seção!", Alert.AlertType.ERROR);
		}
	}
	
	@FXML
	void onChange() {
		if(preventSearch){
			return;
		}
		gridPane.getChildren().clear();
		ArrayList<Professional> filteredList = new ArrayList();
		filteredList.addAll(professionals);
		if(searchField.getText().length() > 0){
			filteredList.removeIf(item->
					!item.getName().toLowerCase().contains(searchField.getText().toLowerCase()) &&
					!item.getCpf().contains(searchField.getText()));
		}
		if(filteredList.size() > 0){
			filteredList.sort((a,b)->Boolean.compare(!a.isActive(),!b.isActive()));
			filteredList.sort(Comparator.comparing(Professional::getName));
			for (Professional professional : filteredList){
				handleCreatePane(professional.getName(),professional.getCpf(),professional.getId(),professional.isActive());
			}
		}
	}
	
}
