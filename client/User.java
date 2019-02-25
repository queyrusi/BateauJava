/**
 * 
 */
package client;

/**
 * Discuter avec le serveur
 * 
 * Collaborateurs :
 * 
 * +--------------+    +-------------+    
 * |  IHMClient   |    | ServeurTCP  |    
 * +--------------+    +-------------+    
 * |              |    |             |    
 * |              |    |             |    
 * +--------------+    +-------------+   
 * 
 * # ascii art was generated on asciiflow.com/
 * 
 * 
 *
 * 
 *
 *
 * @author chenqun
 *
 */
public class User extends Client {
  
  public User(String unNomServeur, int unNumero, String unLogin){
	  /**
	  * @param unNomServeur Ce paramètre défini le serveur sur lequel le client de type User va se connecter.
	  * @param unNumero Numéro du port du serveur sur lequel on souhaite se connecter
	  * @param unLogin Identifiant du client décidé au préalable par l'administrateur réseau du serveur 
	  */
	  	super(unNomServeur,unNumero,unLogin);
    	setTypeConnexion("@User");
  }

}
