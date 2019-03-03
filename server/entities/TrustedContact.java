package server.entities;

public class TrustedContact {

	private String nom;
	private String email;
	private int num_tel;
	/**
	 * <strong>Description : </strong> Classe définissant la structure de contact de confiance (demandé aux utilisateurs lors de la création d'un compte).
	 * @author C.Silva, R.Cuinat
	 * @param nom nom de la personne de confiance
	 * @param email email de la personne de confiance
	 * @param num nuémro de téléphone de la personne de confiance
	 */
	public TrustedContact(String nom, String email,int num) {
		this.nom = nom ;
		this.email = email;
		this.num_tel = num;
	}
	//getters and setters ...	
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
