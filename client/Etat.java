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
 * <strong>Description : </strong> Définit une méthode d'échange .
 * 
 * @author P. Lledo, S. Queyrut
 *
 */
public interface Etat {
	

	String stateLabel = null;
	
	default void onEntry(){};
	
	default void onExit(){}

	String getStateLabel();
	
}
