/**
 * 
 */
package client;

import fr.ensta.fx.boatmonitoring.FXHomeUI;

/* * Collaborateurs :
 * 
 * +--------------+    +-------------+    
 * |  IHMClient   |    | ServeurTCP  |    
 * +--------------+    +-------------+    
 * |              |    |             |    
 * |              |    |             |    
 * +--------------+    +-------------+   
 * 
 * # ascii art was generated on asciiflow.com/
 */

/**
 * <strong>Description : </strong> ClientTCP associ�e au User � la maison
 * 
 * 
 * @author P. Lledo, S. Queyrut
 *
 */
public class User extends Client {
  
	/**
	 * <strong>Description : </strong> Constructeur pour le User
	 * 
	 * <strong>Exemple : </strong> User("monServeur", "newLogin",
	 * "monmdp")
	 * 
	 * @param unNomServeur - hostname du serveur que le système embarqué cherche à joindre.
	 * @param unNumero - numéro de port du serveur que le système embarqué cherche à joindre.
	 * @param unLogin - login d'accès au serveur.
	 * @param unPassword - mot de passe associ� au login d'acc�s.
	 * 
	 */
  public User(String unNomServeur, int unNumero, String unLogin, String unPassword) {
	  	super(unNomServeur,unNumero,unLogin,unPassword);
    	setTypeConnexion("@User");
  }
  
  /**
	 * <strong>Description : </strong> Constructeur pour le User avec gestion UI
	 * 
	 * <strong>Exemple : </strong> User("monServeur", "newLogin",
	 * "monmdp")
	 * 
	 * @param unNomServeur - hostname du serveur que le système embarqué cherche à joindre.
	 * @param unNumero - numéro de port du serveur que le système embarqué cherche à joindre.
	 * @param unLogin - login d'accès au serveur.
	 * @param unPassword - mot de passe associ� au login d'acc�s.
	 * @param unUI - UI associ�e � l'User
	 * 
	 */
  public User(String unNomServeur, int unNumero, String unLogin, String unPassword, FXHomeUI unUI) {
	  	super(unNomServeur,unNumero,unLogin,unPassword,unUI);
  	setTypeConnexion("@User");
}

}
