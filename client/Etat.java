/**
 * 
 */
package client;

//==================
//TODO 9/3/19 10h27
//==================

/*
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
 */

/**
 * <strong>Description : </strong> Définit une méthode d'échange 
 * 
 * @author chenqun
 *
 */
public interface Etat {
	

	String stateLabel = null;
	
	default String getStateLabel() {
		
		return this.stateLabel;
	}
	
	default void onEntry(){};
	
	default void onExit(){};
	
}
