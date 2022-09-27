package com.example.marquinhoscut.Utils.Bar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public abstract class Bar {
	
	protected enum adminOptions{
	EXIT("Sair"),EMPLOYEES("Profissionais"),SERVICES("Serviços"),RESULTS("Resultados");
		
		public final String label;
		
		adminOptions(String label) {
			this.label = label;
		}
	}
	
	protected enum barberOptions{
		ADM("Administrador");
		
		public final String label;
		
		barberOptions(String label) {
			this.label = label;
		}
	}
	
	abstract void navigate(String name);
	
	abstract void handleNavigationBar(Button...args);

}
