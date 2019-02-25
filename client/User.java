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
 * @author chenqun
 *
 */
public class User extends Client {
  
  public User(String unNomServeur, int unNumero, String unLogin){
	  	super(unNomServeur,unNumero,unLogin);
    	typeConnexion="@User";
  }

}
