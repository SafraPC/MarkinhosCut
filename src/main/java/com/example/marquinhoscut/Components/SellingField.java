package com.example.marquinhoscut.Components;
import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Dao.ServiceDao;
import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.Model.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class SellingField {
	private ArrayList<Services> listServices =  new ArrayList();
	private ArrayList<SellingField> controllers = new ArrayList();
	private GridPane gridParent;
	private Thread callback;

	@FXML
	private AnchorPane anchorId;
	
	@FXML
	private TextField qtdField,priceField;
	
	@FXML
	private ChoiceBox<String> serviceCB;



	@FXML
	private void initialize(){
		loadChoiceBox();
		priceField.setEditable(false);
		serviceCB.setOnAction(event->{
			getValue();
			qtdField.setText("1");
			this.getCallback();
		});
	}

	@FXML
	public void loadChoiceBox() {
		try{
			ServiceDao sDao = new ServiceDao();
			listServices.addAll(sDao.getListServices());
			for(Services service : listServices){
				serviceCB.getItems().add(service.getName());
			}
			serviceCB.setTooltip(new Tooltip("Selecione um serviço"));
		}catch(Exception err){
			System.out.println(err.getMessage());
		}
	}

	@FXML
	void onPriceChanged(KeyEvent event) {
		this.getCallback();
	}

	@FXML
	void onQtdChanges() {
		this.getCallback();
	}

	public void setParentCallback(Thread thread){
		this.callback = thread;
	}

	public void editValue(){
		if(priceField.isEditable()){
			priceField.setEditable(false);
		}
		else{
			priceField.setEditable(true);
		}
	}

	public void handleDelete() {
		this.gridParent.getChildren().remove(anchorId);
		this.controllers.remove(this);
		this.getCallback();
	}

	public void getValue(){
		double valueService;
		int index = serviceCB.getSelectionModel().getSelectedIndex();
		valueService = listServices.get(index).getValue();
		priceField.setText(Double.toString(valueService));
		this.getCallback();
	}

	private void getCallback(){
		try {
			new Thread(()->{
				this.callback.run();
			}).run();
		} catch (Exception e) {
			e.getMessage();
		}
	}


	public double toReceive(TextField priceField,TextField qtdField){
		double price = Double.parseDouble(priceField.getText());
		double qtd = Double.parseDouble(qtdField.getText());
		return qtd *price;
	}

	public void setGridParent(GridPane pane, ArrayList<SellingField> controllers){
		this.gridParent = pane;
		this.controllers = controllers;
	}

	public TextField getQtdField(){
		return qtdField;
	}
	
	public TextField getPriceField(){
		return priceField;
	}
	
	public ChoiceBox getServiceCB(){
		return serviceCB;
	}

}
