/**
 * 
 */
package client;

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
 * <strong>Description : </strong> Discuter avec le serveur
 * 
 * 
 * @author chenqun
 *
 */
public class User extends Client {
  
  public User(String unNomServeur, int unNumero, String unLogin) {
	  	super(unNomServeur,unNumero,unLogin);
    	setTypeConnexion("@User");
  }

}
