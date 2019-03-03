package server.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * <strong>Description : </strong> Classe héritant de Client et définissant la structure d'un utilisateur.
 * @author C.Silva, R.Cuinat
 */
public class User extends Client {

	private static int current_Client = 0;
	private Date date_abo;
	private String id;
	private String nom;
	private String adresse;
	private ArrayList<TrustedContact> liste_amis = new ArrayList<TrustedContact>();
	private ArrayList<String> liste_emails = new ArrayList<String>();
	private Ship bateau;
	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de Client et définissant la structure d'un utilisateur.
	 * @author C.Silva, R.Cuinat
	 * @param nom identité de l'utilisateur
	 * @param adresse adresse de l'utilisateur
	 * @param Lemail liste d'adresses emails de l'utilisateur
	 * @param LPDC liste de personnes de confiance
	 * @param bateau bateau appartenant à l'utilisateur
	 */
	public User(String nom,String adresse,ArrayList<String> Lemail,ArrayList<TrustedContact> LPDC,Ship bateau) {
		current_Client ++;
		this.id=nom+Integer.toString(current_Client);
		this.nom = nom ;
		this.adresse = adresse;
		this.liste_emails=Lemail;
		this.date_abo = Calendar.getInstance().getTime();
		this.liste_amis=LPDC;
		this.bateau = bateau;
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance nom
	 * @author C.Silva, R.Cuinat
	 * @return nom - identité de l'utilisateur
	 */
	public synchronized String getNom() {
		return nom;
	}
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance nom
	 * @author C.Silva, R.Cuinat
	 * @param nom - identité de l'utilisateur
	 */
	public synchronized void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance adresse
	 * @author C.Silva, R.Cuinat
	 * @return adresse adresse de l'utilisateur
	 */
	public synchronized String getAdresse() {
		return adresse;
	}
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance adresse
	 * @author C.Silva, R.Cuinat
	 * @param adresse adresse de l'utilisateur
	 */
	public synchronized void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance liste_amis
	 * @author C.Silva, R.Cuinat
	 * @return liste_amis liste des personnes de confiance
	 */
	public synchronized ArrayList<TrustedContact> getListe_amis() {
		return liste_amis;
	}
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance liste_amis
	 * @author C.Silva, R.Cuinat
	 * @param liste_amis liste des personnes de confiance
	 */
	public synchronized void setListe_amis(ArrayList<TrustedContact> liste_amis) {
		this.liste_amis = liste_amis;
	}
	/**
	 * <strong>Description : </strong> Méthode permettant d'ajouter une personne de confiance à la liste des personnes de confiance d'un utilisateur
	 * @author C.Silva, R.Cuinat
	 * @param ami personne de confiance à ajouter
	 */
	public synchronized void add_ami(TrustedContact ami) {
		this.liste_amis.add(ami);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de supprimer une personne de confiance de la liste des personnes de confiance d'un utilisateur
	 * @author C.Silva, R.Cuinat
	 * @param ami personne de confiance à retirer
	 */
	public synchronized void remove_ami(TrustedContact ami) {
		this.liste_amis.remove(ami);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant d'ajouter un email à la liste des emails d'un utilisateur
	 * @author C.Silva, R.Cuinat
	 * @param email adresse email à supprimer
	 */
	public synchronized void add_email(String email) {
		this.liste_emails.add(email);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de supprimer un email de la liste des emails d'un utilisateur
	 * @author C.Silva, R.Cuinat
	 * @param email adresse email à ajouter
	 */
	public synchronized void remove_email(String email) {
		this.liste_emails.remove(email);
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance liste_emails
	 * @author C.Silva, R.Cuinat
	 * @return liste_emails liste des emails de l'utilisateur
	 */
	public synchronized ArrayList<String> getListe_emails() {
		return liste_emails;
	}
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance liste_emails
	 * @author C.Silva, R.Cuinat
	 * @param liste_emails liste des emails de l'utilisateur
	 */
	public synchronized void setListe_emails(ArrayList<String> liste_emails) {
		this.liste_emails = liste_emails;
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance bateau
	 * @author C.Silva, R.Cuinat
	 * @return bateau objet bateau lié à l'utilisateur
	 */
	public synchronized Ship getBateau() {
		return bateau;
	}
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance bateau
	 * @author C.Silva, R.Cuinat
	 * @param bateau objet bateau lié à l'utilisateur
	 */
	public synchronized void setBateau(Ship bateau) {
		this.bateau = bateau;
	}
	/**
	 * <strong>Description : </strong> Getter de la variable de classe current_client s'incrémentant avec le nombre de clients enregistrés
	 * @author C.Silva, R.Cuinat
	 * @return current_Client nombre d'utilisateurs enregistrés
	 */
	public static synchronized int getCurrent_Client() {
		return current_Client;
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance date_abo
	 * @author C.Silva, R.Cuinat
	 * @return date_abo date d'abonnement
	 */
	public synchronized Date getDate_abo() {
		return date_abo;
	}
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance id
	 * @author C.Silva, R.Cuinat
	 * @return id identifiant utilisateur
	 */
	public synchronized String getId() {
		return id;
	}

	public String toString() {
		return "Client : "+nom;
	}


}
