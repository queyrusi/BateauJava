package server.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class User extends Client {

	private static int current_Client = 0;
	private Date date_abo;
	private String id;
	private String nom;
	private String adresse;
	private ArrayList<TrustedContact> liste_amis = new ArrayList<TrustedContact>();
	private ArrayList<String> liste_emails = new ArrayList<String>();
	private Ship bateau;
	
	public User(String nom,String adresse,String email,TrustedContact PDC,Ship bateau) {
		current_Client ++;
		this.id=nom+Integer.toString(current_Client);
		this.nom = nom ;
		this.adresse = adresse;
		this.liste_emails.add(email);
		this.date_abo = Calendar.getInstance().getTime();
		this.liste_amis.add(PDC);
		this.bateau = bateau;
	}

	public synchronized String getNom() {
		return nom;
	}

	public synchronized void setNom(String nom) {
		this.nom = nom;
	}

	public synchronized String getAdresse() {
		return adresse;
	}

	public synchronized void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public synchronized ArrayList<TrustedContact> getListe_amis() {
		return liste_amis;
	}

	public synchronized void setListe_amis(ArrayList<TrustedContact> liste_amis) {
		this.liste_amis = liste_amis;
	}

	public synchronized ArrayList<String> getListe_emails() {
		return liste_emails;
	}

	public synchronized void setListe_emails(ArrayList<String> liste_emails) {
		this.liste_emails = liste_emails;
	}

	public synchronized Ship getBateau() {
		return bateau;
	}

	public synchronized void setBateau(Ship bateau) {
		this.bateau = bateau;
	}

	public static synchronized int getCurrent_Client() {
		return current_Client;
	}

	public synchronized Date getDate_abo() {
		return date_abo;
	}

	public synchronized String getId() {
		return id;
	}

	public String toString() {
		return "Client : "+nom;
	}


}
