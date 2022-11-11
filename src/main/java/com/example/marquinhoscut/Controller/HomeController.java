package com.example.marquinhoscut.Controller;
import com.example.marquinhoscut.App;
import com.example.marquinhoscut.Components.SellingField;
import com.example.marquinhoscut.Dao.PaymentMethodDao;
import com.example.marquinhoscut.Dao.ProfessionalDao;
import com.example.marquinhoscut.Dao.SellingDao;
import com.example.marquinhoscut.Model.PaymentMethod;
import com.example.marquinhoscut.Model.Professional;
import com.example.marquinhoscut.Utils.Bar.BarberBar;
import com.example.marquinhoscut.Utils.Dialog.DialogMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Callable;


public class HomeController extends BarberBar {

	private ArrayList<Professional> professionals =  new ArrayList();
	private SellingDao sellingDao = new SellingDao();

	private ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();
	private ArrayList<SellingField> controllers = new ArrayList();

	@FXML
	private Button buttonAdm;
	@FXML
	private GridPane gridPane;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ChoiceBox<String> CBbarber;
	@FXML
	private ChoiceBox<String> CBPaymentMethod;
	@FXML
	public Label toReceive;


	@FXML
	void initialize() {
		try{
			handleNavigationBar(buttonAdm);
			handleAddNewSection();
			datePicker.setValue(LocalDate.now());
			loadChoiceBox();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	@FXML
	private void handleAddNewSection() {
		try{
			
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("sellingField.fxml"));
			AnchorPane scene = fxmlLoader.load();
			AnchorPane ap = scene;
			SellingField controller = fxmlLoader.getController();
			controller.setParentCallback( new Thread(()->toReceive.setText(sumToReceive(false))));
			controller.setGridParent(gridPane,controllers);
			controllers.add(controller);
			gridPane.add(ap, 0, gridPane.getRowCount());
			
		}catch(Exception e){
			DialogMessage.show("Erro ao adicionar seção!","Houve um erro ao adicionar uma nova seção!", Alert.AlertType.ERROR);
			System.out.println(e.getMessage());
		}
	}
	
	private boolean isFormValid(){
		try{
			for(SellingField fields: controllers){
				if( Integer.parseInt(fields.getQtdField().getText()) <= 0 ){
					System.out.println("Preencha corretamente o campo, Valor deve ser maior que 0.");
					return false;
				}
				if (fields.getServiceCB().getSelectionModel().isEmpty()) {
					System.out.println("Preencha corretamente o campo Serviço.");
					return false;
				}
				if (CBbarber.getSelectionModel().isEmpty()) {
					System.out.println("Preencha corretamente o campo Barbeiro.");
					return false;
				}
				if (CBPaymentMethod.getSelectionModel().isEmpty()) {
					System.out.println("Preencha corretamente o campo Método de Pagamento.");
					return false;
				}
			}
		}catch (Exception err){
			System.out.println(err.getMessage());
			return false;
		}
		return true;
	}
	
	@FXML
	void handleSubmit() {
		if(isFormValid()){
			Professional selectedBarber = null;
			for(Professional professional : professionals){
				if(professional.getName() == CBbarber.getSelectionModel().getSelectedItem()){
					selectedBarber = professional;
				}
			}
			String paymentType = CBPaymentMethod.getSelectionModel().getSelectedItem();
			double total = Double.parseDouble(sumToReceive(true));
			String dateValue = datePicker.getValue().toString();
			
			try {
				sellingDao.handleSelling(selectedBarber.getCpf(),paymentType,total,dateValue);
			} catch (SQLException e) {
				System.out.println(e);
				throw new RuntimeException(e);
			}
		}
	}

	@FXML
	void loadChoiceBox() {

		try{
			ProfessionalDao pDao = new ProfessionalDao();
			professionals.addAll(pDao.getListProfessional());

			PaymentMethodDao payDao = new PaymentMethodDao();
			paymentMethods.addAll(payDao.getListPaymentMethod());

		}catch(Exception err){
			System.out.println(err.getMessage());
		}
		for(Professional professional : professionals ){
			if(professional.isActive()){
				CBbarber.getItems().add(professional.getName());
			}

		}

		for(PaymentMethod paymentMethod : paymentMethods ){
			CBPaymentMethod.getItems().add(paymentMethod.getName());
		}

	}

	@FXML
	public String sumToReceive(boolean pure) {
		try{
			double valueService = 0;
			for(SellingField fields: controllers){
				valueService += fields.toReceive(fields.getQtdField(),fields.getPriceField());
			}
			System.out.println(NumberFormat.getCurrencyInstance().format(valueService));
			if(pure){
				return valueService+"";
			}
			return NumberFormat.getCurrencyInstance().format(valueService);
		}catch(Exception err){
			System.out.println(err.getMessage());
			return "";
		}
	}
}
