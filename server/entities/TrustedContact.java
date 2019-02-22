package server.entities;

public class TrustedContact {

	private String nom;
	private String email;
	private int num_tel;
	
	public TrustedContact(String nom, String email,int num) {
		this.nom = nom ;
		this.email = email;
		this.num_tel = num;
	}
	
	public synchronized String getNom() {
		return nom;
	}

	public synchronized void setNom(String nom) {
		this.nom = nom;
	}

	public synchronized String getEmail() {
		return email;
	}

	public synchronized void setEmail(String email) {
		this.email = email;
	}

	public synchronized int getNum_tel() {
		return num_tel;
	}

	public synchronized void setNum_tel(int num_tel) {
		this.num_tel = num_tel;
	}

	public String toString() {
		return "Nom : "+nom;
	}

}
