package fr.ensta.fx.boatmonitoring.demo;

import java.util.Observable;

public class TotoHomeModel extends Observable {

    private String accountName = "Toto";
    private String password = "0000";
    private String boatName = "Foobar";
    
	public String getAccountName() {
		return accountName;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getBoatName() {
		return boatName;
	}
	
	public void setBoatName(String boatName) {
		this.boatName = boatName;
		this.setChanged();
		this.notifyObservers();
		
	}

}
