package server.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import server.patterns.update.IUpdateCenter;
/**
 * <strong>Description : </strong> Classe héritant de Client et définissant la structure d'un utilisateur.
 * @author C.Silva, R.Cuinat
 */
public class User extends Client {

	private static int current_Client = 0;
	private Date date_abo;
	private int password;
	private final String id;
	private String nom;
	private String adresse;
	private ArrayList<TrustedContact> liste_amis;
	private ArrayList<String> liste_emails;
	private Ship bateau;
	private IUpdateCenter updateCenter;
	private boolean online;
	
	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de Client et définissant la structure d'un utilisateur.
	 * @author C.Silva, R.Cuinat
	 * @param nom identité de l'utilisateur
	 * @param adresse adresse de l'utilisateur
	 * @param Lemail liste d'adresses emails de l'utilisateur
	 * @param LPDC liste de personnes de confiance
	 * @param bateau bateau appartenant à l'utilisateur
	 * @param password Mot de passe de connexion du User
	 */
	public User(String nom,String password,String adresse,ArrayList<String> Lemail,ArrayList<TrustedContact> LPDC,Ship bateau) {
		current_Client ++;
		this.id=nom+Integer.toString(current_Client);
		this.password = password.hashCode();
		this.nom = nom ;
		this.adresse = adresse;
		this.liste_emails = new ArrayList<String>();
		this.online = false;
		if (Lemail != null) {
			this.liste_emails=Lemail;
		}
		this.date_abo = Calendar.getInstance().getTime();
		this.liste_amis = new ArrayList<TrustedContact>();
		if (LPDC != null) {
			this.liste_amis=LPDC;
		}
		this.bateau = bateau;
		if (this.bateau != null) {
			this.bateau.setUser(this);
		}
		this.updateCenter = new IUpdateCenter() {};
		System.out.println("Le Client " +this.nom + " a comme identifiant : " + this.id);
	}
	
	/**
	 * <strong>Description : </strong> Renvoie une chaîne détaillant toutes les informations utiles à l'utilisateur.
	 * @author C.Silva, R.Cuinat
	 * 
	 * @return Ligne au format "@get label data label data ..." contenant les informations du User.
	 */
	public final String getAllInfo() {
		String line = "@get ";
		line += "id " + this.getId() + " ";
		line += "nom " + this.getNom().replaceAll(" ", "_") + " ";
		line += "adresse " + this.getAdresse().replaceAll(" ", "_") + " ";
		for (String email : this.liste_emails) {
			line += "email " + email + " ";
		}
		if (this.bateau != null) {
			line += "bateau_immatriculation " + this.bateau.getImmatriculation() + " ";
			line += "bateau_state " + this.bateau.getState().toString() + " ";
			line += "bateau_lieu " + this.bateau.getEndroit_stationnement().replaceAll(" ", "_") +  " ";
			if (this.bateau.getPosition() != null) {
				Position pos = this.bateau.getPosition();
				line += "bateau_pos " + String.valueOf(pos.getCoordsInDegree()[0]) + "/" + String.valueOf(pos.getCoordsInDegree()[1]) + " "; 
			}
			else {
				line += "bateau_pos null ";
			}
			if (this.bateau.getNom() != null) {
				line += "bateau_nom " + this.bateau.getNom().replaceAll(" ", "_") + " "; 
			}
			else {
				line += "bateau_nom null ";
			}
		}
		line += "date_abo " + this.date_abo.toString().replaceAll(" ", "_");
		return line;
	}
	
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance nom
	 * @author C.Silva, R.Cuinat
	 * @return nom - identité de l'utilisateur
	 */
	public final synchronized String getNom() {
		return nom;
	}
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance nom
	 * @author C.Silva, R.Cuinat
	 * @param nom - identité de l'utilisateur
	 */
	public final synchronized void setNom(String nom) {
		String old = this.nom;
		this.nom = nom;
		this.updateCenter.updateUserName(old,this);
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance adresse
	 * @author C.Silva, R.Cuinat
	 * @return adresse adresse de l'utilisateur
	 */
	public final synchronized String getAdresse() {
		return adresse;
	}
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance adresse
	 * @author C.Silva, R.Cuinat
	 * @param adresse adresse de l'utilisateur
	 */
	public final synchronized void setAdresse(String adresse) {
		String old = this.adresse;
		this.adresse = adresse;
		this.updateCenter.updateUserAdresse(old,this);
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance liste_amis
	 * @author C.Silva, R.Cuinat
	 * @return liste_amis liste des personnes de confiance
	 */
	public final synchronized ArrayList<TrustedContact> getListe_amis() {
		return liste_amis;
	}
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance liste_amis
	 * @author C.Silva, R.Cuinat
	 * @param liste_amis liste des personnes de confiance
	 */
	public final synchronized void setListe_amis(ArrayList<TrustedContact> liste_amis) {
		ArrayList<TrustedContact> old = new ArrayList<TrustedContact>();
		old.addAll(this.liste_amis);
		this.liste_amis = liste_amis;
		this.updateCenter.updateUserListAmis(old, this);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant d'ajouter une personne de confiance à la liste des personnes de confiance d'un utilisateur
	 * @author C.Silva, R.Cuinat
	 * @param ami personne de confiance à ajouter
	 */
	public final synchronized void add_ami(TrustedContact ami) {
		this.liste_amis.add(ami);
		this.updateCenter.userAmiAdded(ami, this);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de supprimer une personne de confiance de la liste des personnes de confiance d'un utilisateur
	 * @author C.Silva, R.Cuinat
	 * @param ami personne de confiance à retirer
	 */
	public final synchronized void remove_ami(TrustedContact ami) {
		boolean success = this.liste_amis.remove(ami);
		if (success) this.updateCenter.userAmiRemoved(ami, this);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant d'ajouter un email à la liste des emails d'un utilisateur
	 * @author C.Silva, R.Cuinat
	 * @param email adresse email à supprimer
	 */
	public final synchronized void add_email(String email) {
		this.liste_emails.add(email);
		this.updateCenter.userEmailAdded(email,this);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de supprimer un email de la liste des emails d'un utilisateur
	 * @author C.Silva, R.Cuinat
	 * @param email adresse email à ajouter
	 */
	public final synchronized void remove_email(String email) {
		boolean success = this.liste_emails.remove(email);
		if (success) this.updateCenter.userEmailRemoved(email,this);
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance liste_emails
	 * @author C.Silva, R.Cuinat
	 * @return liste_emails liste des emails de l'utilisateur
	 */
	public final synchronized ArrayList<String> getListe_emails() {
		return liste_emails;
	}
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance liste_emails
	 * @author C.Silva, R.Cuinat
	 * @param liste_emails liste des emails de l'utilisateur
	 */
	public final synchronized void setListe_emails(ArrayList<String> liste_emails) {
		ArrayList<String> old = new ArrayList<String>(); 
		old.addAll(this.liste_emails);
		this.liste_emails = liste_emails;
		this.updateCenter.updateUserListEmail(old,this);
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance bateau
	 * @author C.Silva, R.Cuinat
	 * @return bateau objet bateau lié à l'utilisateur
	 */
	public final synchronized Ship getBateau() {
		return bateau;
	}
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance bateau
	 * @author C.Silva, R.Cuinat
	 * @param bateau objet bateau lié à l'utilisateur
	 */
	public final synchronized void setBateau(Ship bateau) {
		Ship old = this.bateau;
		this.bateau = bateau;
		this.updateCenter.updateUserShip(old, this);
	}
	/**
	 * <strong>Description : </strong> Getter de la variable de classe current_client s'incrémentant avec le nombre de clients enregistrés
	 * @author C.Silva, R.Cuinat
	 * @return current_Client nombre d'utilisateurs enregistrés
	 */
	public final static synchronized int getCurrent_Client() {
		return current_Client;
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance date_abo
	 * @author C.Silva, R.Cuinat
	 * @return date_abo date d'abonnement
	 */
	public final synchronized Date getDate_abo() {
		return date_abo;
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance id
	 * @author C.Silva, R.Cuinat
	 * @return id identifiant utilisateur
	 */
	public final synchronized String getId() {
		return id;
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance password
	 * @author C.Silva, R.Cuinat
	 * @return Hash du mot de passe du User
	 */
	public final synchronized int getPassword() {
		return password;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance password
	 * @author C.Silva, R.Cuinat
	 * @param password Mot de passe du User
	 */
	public final synchronized void setPassword(String password) {
		int old = this.password;
		this.password = password.hashCode();
		this.updateCenter.updateUserPassword(old, this);
	}
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance updateCenter
	 * @author C.Silva, R.Cuinat
	 * @return updateCenter du User
	 */
	public final synchronized IUpdateCenter getUpdateCenter() {
		return updateCenter;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance updateCenter
	 * @author C.Silva, R.Cuinat
	 * @param updateCenter du User
	 */
	public final synchronized void setUpdateCenter(IUpdateCenter updateCenter) {
		this.updateCenter = updateCenter;
	}
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance online
	 * @author C.Silva, R.Cuinat
	 * @return Vrai lorsque l'utilisateur est connecté
	 */
	public final synchronized boolean isOnline() {
		return online;
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance online
	 * @author C.Silva, R.Cuinat
	 * @param online nouvelle valeur pour la variable online
	 */
	public final synchronized void setOnline(boolean online) {
		boolean old  = this.online;
		this.online = online;
		this.updateCenter.updateUserOnline(old, this);
	}
	
	public String toString() {
		return "Client : "+nom;
	}
	
}
