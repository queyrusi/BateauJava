/**
 * 
 */
package client;

/**
 * Définit une méthode d'échange 
 * 
 * Collaborateurs :
 * 
 * +--------------+    +---------------------+
 * |  SystAlarme  |    |   SystemeEmbarque   |
 * +--------------+    +---------------------+
 * |              |    |                     |
 * |              |    |                     |
 * +--------------+    +---------------------+
 *
 * # ascii art was generated on asciiflow.com/
 * 
 * @author chenqun
 *
 */
public interface Etat {

	/**
	 * Remplaçable par un ToString()...
	 * 
	 */
	void getStatus();
	
}
