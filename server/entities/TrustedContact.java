package server.entities;
/**
 * <strong>Description : </strong> Classe définissant la structure de contact de confiance (demandé aux utilisateurs lors de la création d'un compte).
 * @author C.Silva, R.Cuinat
 */
public class TrustedContact {

	private String nom;
	private String email;
	private int num_tel;
	/**
	 * <strong>Description : </strong> Constructeur de la classe définissant la structure de contact de confiance (demandé aux utilisateurs lors de la création d'un compte).
	 * @author C.Silva, R.Cuinat
	 * @param nom Nom de la personne de confiance
	 * @param email Email de la personne de confiance
	 * @param num Numéro de téléphone de la personne de confiance
	 */
	public TrustedContact(String nom, String email,int num) {
		this.nom = nom ;
		this.email = email;
		this.num_tel = num;
	}
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance nom
	 * @author C.Silva, R.Cuinat
	 * @return Nom de la personne de confiance
	 */
	public synchronized String getNom() {
		return nom;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance nom
	 * @author C.Silva, R.Cuinat
	 * @param nom Nom de la personne de confiance
	 */
	public synchronized void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance email
	 * @author C.Silva, R.Cuinat
	 * @return Email de la personne de confiance
	 */
	public synchronized String getEmail() {
		return email;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance email
	 * @author C.Silva, R.Cuinat
	 * @param email Email de la personne de confiance
	 */
	public synchronized void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance num_tel
	 * @author C.Silva, R.Cuinat
	 * @return Numéro de téléphone de la personne de confiance
	 */
	public synchronized int getNum_tel() {
		return num_tel;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance num_tel
	 * @author C.Silva, R.Cuinat
	 * @param num_tel Numéro de téléphone de la personne de confiance
	 */
	public synchronized void setNum_tel(int num_tel) {
		this.num_tel = num_tel;
	}

	public String toString() {
		return "Nom : "+nom;
	}

}
