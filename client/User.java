/**
 * 
 */
package client;

import java.util.Date;

import fr.ensta.fx.boatmonitoring.FXHomeUI;

//==================
//TODO 20/3/19 21h15
//==================

/** 
 *   
 * -le user envoit -@req au serveur qui passe au bateau, quel format ? Pour les formats 
 * 
 * -le user peut envoyer -@get pour avoir toutes les donnees relatives au user : format 
 * -@get      id monId      nom monNom      adresse mon_adresse     email monEmail1     email monEmail2    bateau_immatriculation monImmatriculation 
 * bateau_state monEtat (Monitored, NotMonitored, Stolen)       date_abo maDateDabonnement (au format degueu, faire :
 * Date date_abo = date.parse(la chaine)      bateau_lieu lieuDuBateau (genre ponton, port etc.)      bateau_pos lat/long (ou bateau_pos null)
 * 
 * - le serveur me renvoit alors -@get avec TOUT ! pour confirmer la mise a jour. 
 * 
 * Si email ou ... n'a pas ete rempli, il n'apparait pas dans la reponse du serveur 
 * 
 * -@set nom newName adresse newAdresse (attention underscore) 
 * POUR AJOUTER : email newEmail
 * POUR SUPPRIMER : email ancienEmail/
 * POUR REMPLACER : email ancienEmail/newEmail
 * 
 * bateau_nom newNom bateau_lieu newLieu 
 * 
 */

/**
 * <strong>Description : </strong> ClientTCP associ�e au User � la maison
 * 
 * 
 * @author P. Lledo, S. Queyrut
 *
 */
public class User extends Client {
	
	//variable discriminant la pr�sence d'un affichage
	private boolean hasUI;
	
	// Runnable en paramètre du thread pour gérer les requêtes
	RequestHandlerUser requestHandler;
	
	// Thread à executer à l'instantiation 
	Thread requestHandlerThread;

	// boolean attestant de l'ecoute (permet de sortir proprement du thread)
	public boolean handling;
	
	// donnees personnelles user:
	String id, nom, adresse, bateauIm, bateauState, bateauLieu, bateauPos, bateauNom;

	// date d'abonnement
	Date dateAbo;
	
	// liste email:
	String [] emailArray = new String[10];
	
	/**
	 * <strong>Description : </strong> Constructeur pour le User
	 * 
	 * <strong>Exemple : </strong> User("monServeur", "newLogin",
	 * "monmdp")
	 * 
	 * @param unNomServeur - hostname du serveur que le user cherche à joindre.
	 * @param unNumero - numéro de port du serveur que le use cherche à joindre.
	 * @param unLogin - login d'accès au serveur.
	 * @param unPassword - mot de passe associ� au login d'acc�s.
	 * 
	 */
  public User(String unNomServeur, int unNumero, String unLogin, String unPassword) {
	  	super(unNomServeur,unNumero,unLogin,unPassword);
    	setTypeConnexion("@User");
    	hasUI = false;
    	
    	// TODO
    	nom = "Pineau";
    	
		requestHandler = new RequestHandlerUser(this);
		requestHandlerThread = new Thread(requestHandler);
  }
  
  	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the bateau_lieu
	 */
	public String getBateauLieu() {
		return bateauLieu;
	}

	/**
	 * @param bateau_lieu the bateau_lieu to set
	 */
	public void setBateauLieu(String bateau_lieu) {
		this.bateauLieu = bateau_lieu;
	}

	/**
	 * @return the bateau_pos
	 */
	public String getBateauPos() {
		return bateauPos;
	}

	/**
	 * @param bateau_pos the bateau_pos to set
	 */
	public void setBateauPos(String bateau_pos) {
		this.bateauPos = bateau_pos;
	}

	/**
	 * @return the date_abo
	 */
	public Date getDateAbo() {
		return dateAbo;
	}

	/**
	 * @param date_abo the date_abo to set
	 */
	public void setDateAbo(Date date_abo) {
		this.dateAbo = dateAbo;
	}

	/**
	 * @return the emailArray
	 */
	public String[] getEmailArray() {
		return emailArray;
	}

	/**
	 * @param emailArray the emailArray to set
	 */
	public void setEmailArray(String[] emailArray) {
		this.emailArray = emailArray;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
		
	/**
	 * @return the bateauIm
	 */
	public String getBateauIm() {
		return bateauIm;
	}

	/**
	 * @param bateauIm the bateauIm to set
	 */
	public void setBateauIm(String bateauIm) {
		this.bateauIm = bateauIm;
	}

	/**
	 * @return the bateauState
	 */
	public String getBateauState() {
		return bateauState;
	}

	/**
	 * @param bateauState the bateauState to set
	 */
	public void setBateauState(String bateauState) {
		this.bateauState = bateauState;
	}

	/**
	 * @return the bateauNom
	 */
	public String getBateauNom() {
		return bateauNom;
	}

	/**
	 * @param bateauNom the bateauNom to set
	 */
	public void setBateauNom(String bateauNom) {
		this.bateauNom = bateauNom;
	}

	/**
	 * <strong>Description : </strong> Constructeur pour le User avec gestion UI
	 * 
	 * <strong>Exemple : </strong> User("monServeur", "newLogin",
	 * "monmdp")
	 * 
	 * @param unNomServeur - hostname du serveur que le user cherche à joindre.
	 * @param unNumero - numéro de port du serveur que le user cherche à joindre.
	 * @param unLogin - login d'accès au serveur.
	 * @param unPassword - mot de passe associ� au login d'acc�s.
	 * @param unUI - UI associ�e � l'User
	 * 
	 */
  public User(String unNomServeur, int unNumero, String unLogin, String unPassword, FXHomeUI unUI) {
	  	super(unNomServeur,unNumero,unLogin,unPassword,unUI);
	  	setTypeConnexion("@User");
	  	hasUI = true;
	 
		requestHandler = new RequestHandlerUser(this);
		requestHandlerThread = new Thread(requestHandler);
  }

	/**
	 * @return the handling
	 */
	public boolean isHandling() {
		return handling;
	}

	/**
	 * @param handling the handling to set
	 */
	public void setHandling(boolean handling) {
		this.handling = handling;
		System.out.println("AVANT START");
		this.requestHandlerThread.start();
		System.out.println("APRES START");
	}
  
  

}
