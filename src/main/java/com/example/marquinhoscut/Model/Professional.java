package com.example.marquinhoscut.Model;
import javafx.beans.property.*;
public class Professional {

	private final StringProperty name,cpf;
	private final BooleanProperty active;

	
	public Professional(StringProperty name, StringProperty cpf, BooleanProperty active){
		this.active = active;
		this.cpf = cpf;
		this.name = name;
	}
	public Professional(String name, String cpf, boolean active){
		this.active = new SimpleBooleanProperty(active);
		this.cpf = new SimpleStringProperty(cpf);
		this.name = new SimpleStringProperty(name);
	}
	public void setName(String name) {
		this.name.set(name);
	}
	
	public void setCpf(String cpf) { this.cpf.set(cpf);}
	
	public void setActive(boolean active) {
		this.active.set(active);
	}
	
	
	public StringProperty getName() {
		return name;
	}
	
	public StringProperty getCpf() {
		return cpf;
	}
	
	public BooleanProperty getActive(){
		return active;
	}
	
}
