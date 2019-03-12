/**
 * 
 */
package client;

//==================
//TODO 9/3/19 10h27
//==================

/*
 * TODO
 * +--------------------+
 * | +----------------+ |              +------------------+
 * | |                | |              |                  |
 * | |  NoMonitoring  | +-------------->    Monitoring    |
 * | |                | <--------------+    ~~~~~~~~~~    |
 * | +----------------+ |              +---------+--------+
 * +--------------------+                        |
 *            ^                                  |
 *            |                                  |
 *            |                                  |
 *            |                                  |
 *            |                                  |
 *    +-------+-------+                 +--------v--------+
 *    |               |                 |                 |
 *    |    Tracking   <-----------------+     Stolen      |
 *    |               |                 |                 |
 *    +---------------+                 +-----------------+
 */

/**
 * <strong>Description : </strong> État {@code Monitoring} pour le système embarqué.
 *   
 * @author P. Lledo, S. Queyrut
 */
public class Monitoring implements Etat {

	String stateLabel;
	
	SystemeEmbarque systemeDuBateau;
	
	/**
	 * <strong>Description : </strong>Constructeur pour l'état {@code Monitoring}.
	 * 
	 * @param newSysEmbarque - système embarqué qui implémente l'état.
	 * @author P. Lledo, S. Queyrut
	 */
	public Monitoring(SystemeEmbarque newSysEmbarque){
		
		stateLabel = "Monitoring";
		
		systemeDuBateau = newSysEmbarque;
		
	}
	
	/**
	 * <strong>Description : </strong>Retourne le système embarqué qui implémente l'état.
	 * 
	 * @author P. Lledo, S. Queyrut
	 * @return le système embarqué qui implémente l'état.
	 */
	public SystemeEmbarque getSystemeDuBateau() {
		return systemeDuBateau;
	}
	
	/**
	 * <strong>Description : </strong>Retourne le label de l'état.
	 * 
	 * @author P. Lledo, S. Queyrut
	 * @return le label de l'état.
	 */
	public String getStateLabel() {
		return stateLabel;
	}
	
	/**
	 * <strong> Description </strong>: méthode exécutée en entrée de Monitoring.
	 * Lance le thread de gestion des requêtes du serveur.
	 * 
	 * @author P. Lledo, S. Queyrut
	 */
	@Override
	public void onEntry(){
		
		this.systemeDuBateau.handling = true;
		systemeDuBateau.requestHandlerThread.start();
	}
}
