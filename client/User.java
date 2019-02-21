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
    	numeroPort = unNumero;
		  nomServeur = unNomServeur;
      login = unLogin;
      typeConnexio="@User";
  }

}
