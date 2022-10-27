package com.example.marquinhoscut.Model;

public class Professional {
	private String name,cpf;
	private boolean active;
	
	public Professional(String name, String cpf, boolean active){
		this.active = active;
		this.cpf = cpf;
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public boolean getActive(){
		return active;
	}
	
}
