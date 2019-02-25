package server.entities;

import java.util.ArrayList;

import server.patterns.ShipState;
import server.shipStates.NotMonitoredState;

public class Ship extends Client {
	private String immatriculation;
	private String nom;
	private String modele;
	private String type;
	private String endroit_stationnement;
	private Position position;
	private ShipState state;
	private ArrayList<String> dates_derniers_vols = new ArrayList<String>();
	/**
	 * <strong>Description : </strong> Classe héritant de Client et définissant la structure d'un bateau.
	 * @author C.Silva, R.Cuinat
	 */
	public Ship(String immatriculation,String nom,String modele, String type, String station,Position pos) {
		this.immatriculation=immatriculation;
		this.nom=nom;
		this.modele=modele;
		this.type=type;
		this.endroit_stationnement=station;
		this.position=pos;
		this.state= new NotMonitoredState(this);
	}
	
	public Ship(String immatriculation,String modele, String type, String station) {
		this(immatriculation,null,modele,type,station,null);
	}
	//getters and setters ...	
	public synchronized String getImmatriculation() {
		return immatriculation;
	}

	public synchronized void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public synchronized String getNom() {
		return nom;
	}

	public synchronized void setNom(String nom) {
		this.nom = nom;
	}

	public synchronized String getModele() {
		return modele;
	}

	public synchronized void setModele(String modele) {
		this.modele = modele;
	}

	public synchronized String getType() {
		return type;
	}

	public synchronized void setType(String type) {
		this.type = type;
	}

	public synchronized String getEndroit_stationnement() {
		return endroit_stationnement;
	}

	public synchronized void setEndroit_stationnement(String endroit_stationnement) {
		this.endroit_stationnement = endroit_stationnement;
	}

	public synchronized Position getPosition() {
		return position;
	}

	public synchronized void setPosition(Position position) {
		this.position = position;
	}

	public synchronized ShipState getState() {
		return state;
	}

	public synchronized void setState(ShipState state) {
		this.state = state;
	}

	public synchronized ArrayList<String> getDates_derniers_vols() {
		return dates_derniers_vols;
	}

	public synchronized void setDates_derniers_vols(ArrayList<String> dates_derniers_vols) {
		this.dates_derniers_vols = dates_derniers_vols;
	}
	
	public Ship(String immatriculation,String modele, String type, String station,Position pos) {
		this(immatriculation,null,modele,type,station,pos);
	}
	
	public String toString() {
		return "Bateau immatriculé : "+immatriculation;
	}
}
