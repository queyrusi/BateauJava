package server.entities;

import java.util.ArrayList;
import java.util.Calendar;

import server.patterns.ShipState;
import server.shipStates.NotMonitoredState;

/**
 * <strong>Description : </strong> Classe héritant de Client et définissant la structure d'un bateau.
 * @author C.Silva, R.Cuinat
 */
public class Ship extends Client {
	private String immatriculation;
	private String nom;
	private String modele;
	private String type;
	private String endroit_stationnement;
	private Position position;
	private ShipState state;
	private Calendar date;
	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de Client et définissant la structure d'un bateau.
	 * Ce constructeur permet d'initialiser le bateau avec une position courante.
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Immatriculation du bateau
	 * @param nom Nom du bateau
	 * @param modele Modèle du bateau
	 * @param type Type de bateau
	 * @param station Lieu de stationnement du bateau
	 * @param pos Position actuelle du bateau
	 */
	public Ship(String immatriculation,String nom,String modele, String type, String station,Position pos) {
		this.immatriculation=immatriculation;
		this.nom=nom;
		this.modele=modele;
		this.type=type;
		this.endroit_stationnement=station;
		this.position=pos;
		this.state= new NotMonitoredState(this);
		this.date = Calendar.getInstance();
	}
	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de Client et définissant la structure d'un bateau.
	 * Ce constructeur permet de créer un bateau sans nom et sans position intiale.
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Immatriculation du bateau
	 * @param modele Modèle du bateau
	 * @param type Type de bateau
	 * @param station Lieu de stationnement du bateau
	 * 
	 */
	public Ship(String immatriculation,String modele, String type, String station) {
		this(immatriculation,null,modele,type,station,null);
		this.setDate(null);
	}
	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de Client et définissant la structure d'un bateau.
	 * Ce constructeur permet de créer un bateau sans position initiale.
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Immatriculation du bateau
	 * @param modele Modèle du bateau
	 * @param nom Nom du bateau
	 * @param type Type de bateau
	 * @param station Lieu de stationnement du bateau
	 * 
	 */
	public Ship(String immatriculation,String nom,String modele, String type, String station) {
		this(immatriculation,nom,modele,type,station,null);
		this.setDate(null);
	}
	
	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de Client et définissant la structure d'un bateau.
	 * Ce constructeur permet de créer un bateau sans nom.
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Immatriculation du bateau
	 * @param modele Modèle du bateau
	 * @param type Type de bateau
	 * @param station Lieu de stationnement du bateau
	 * @param pos Position actuelle du bateau
	 */
	public Ship(String immatriculation , String modele, String type, String station, Position pos) {
		this(immatriculation,null,modele,type,station,pos);
	}
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance date
	 * @author C.Silva, R.Cuinat
	 * @return Date de la dernière mise à jour de position
	 */
	public synchronized Calendar getDate() {
		return date;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance date
	 * @author C.Silva, R.Cuinat
	 * @param date Date de la dernière mise à jour de position
	 */
	public synchronized void setDate(Calendar date) {
		this.date = date;
	}
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance immatriculation
	 * @author C.Silva, R.Cuinat
	 * @return Immatriculation du bateau
	 */
	public synchronized String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance immatriculation
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Immatriculation du bateau
	 */
	public synchronized void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance nom
	 * @author C.Silva, R.Cuinat
	 * @return Nom du bateau
	 */
	public synchronized String getNom() {
		return nom;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance nom
	 * @author C.Silva, R.Cuinat
	 * @param nom Nom du bateau
	 */
	public synchronized void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance modele
	 * @author C.Silva, R.Cuinat
	 * @return Modèle du bateau
	 */
	public synchronized String getModele() {
		return modele;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance modele
	 * @author C.Silva, R.Cuinat
	 * @param modele Modèle du bateau
	 */
	public synchronized void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance type
	 * @author C.Silva, R.Cuinat
	 * @return Type du bateau
	 */
	public synchronized String getType() {
		return type;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance type
	 * @author C.Silva, R.Cuinat
	 * @param type Type du bateau
	 */
	public synchronized void setType(String type) {
		this.type = type;
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance endroit_stationnement
	 * @author C.Silva, R.Cuinat
	 * @return Lieu de stationnement du bateau
	 */
	public synchronized String getEndroit_stationnement() {
		return endroit_stationnement;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance endroit_stationnement
	 * @author C.Silva, R.Cuinat
	 * @param endroit_stationnement Lieu de stationnement du bateau
	 */
	public synchronized void setEndroit_stationnement(String endroit_stationnement) {
		this.endroit_stationnement = endroit_stationnement;
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance position
	 * @author C.Silva, R.Cuinat
	 * @return Position courante du bateau
	 */
	public synchronized Position getPosition() {
		return position;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance position
	 * @author C.Silva, R.Cuinat
	 * @param position Position courante du bateau
	 */
	public synchronized void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance state
	 * @author C.Silva, R.Cuinat
	 * @return Etat du bateau
	 */
	public synchronized ShipState getState() {
		return state;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance state
	 * @author C.Silva, R.Cuinat
	 * @param state Etat du bateau
	 */
	public synchronized void setState(ShipState state) {
		this.state = state;
	}
	
	public String toString() {
		return "Bateau immatriculé : "+immatriculation;
	}
}
