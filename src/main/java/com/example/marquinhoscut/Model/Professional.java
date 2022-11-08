package com.example.marquinhoscut.Model;
import javafx.beans.property.*;
public class Professional {

	private String name;
	private String cpf;
	private boolean active;

	public Professional(String name, String cpf, boolean active){
		this.active = new SimpleBooleanProperty(active).get();
		this.cpf = new SimpleStringProperty(cpf).get();
		this.name = new SimpleStringProperty(name).get();
	}
	
	public String getName() {
		return name;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public boolean isActive(){return this.active;}
}
