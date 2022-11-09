package com.example.marquinhoscut.Model;
import javafx.beans.property.*;
public class Professional {

	private int id;
	private String name;
	private String cpf;
	private boolean active;

	public Professional(String name, String cpf, boolean active,int id){
		this.active = new SimpleBooleanProperty(active).get();
		this.cpf = new SimpleStringProperty(cpf).get();
		this.name = new SimpleStringProperty(name).get();
		this.id = new SimpleIntegerProperty(id).get();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public boolean isActive(){return this.active;}
}
