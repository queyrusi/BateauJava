/**
 * 
 */
package client;

import java.util.concurrent.TimeUnit;

//==================
//TODO 7/3/19 09h05
//==================

/* +--------------------+
 * | +----------------+ |              +------------------+
 * | |                | |              |                  |
 * | |  NoMonitoring  | +-------------->    Monitoring    |
 * | |                | <--------------+                  |
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
 *    |               |                 |     ~~~~~~      |
 *    +---------------+                 +-----------------+
 *    
 * # ascii art was generated on asciiflow.com/ 
 * 
 */

/**
 * <strong>Description : </strong> État {@code Stolen} pour le système embarqué.
 *   
 * @author P. Lledo, S. Queyrut
 */
public class Stolen implements Etat{

	String stateLabel;
	
	SystemeEmbarque systemeDuBateau;
	
	
	/**
	 * <strong>Description : </strong>Constructeur pour l'état {@code Stolen}.
	 * 
	 * @param newSysEmbarque - système embarqué qui implémente l'état.
	 * @author P. Lledo, S. Queyrut
	 */
	public Stolen(SystemeEmbarque newSysEmbarque){
		
		stateLabel = "Stolen";
		
		systemeDuBateau = newSysEmbarque;
		
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
	 * <strong>Description : </strong>Retourne le système embarqué qui implémente l'état.
	 * 
	 * @author P. Lledo, S. Queyrut
	 * @return le système embarqué qui implémente l'état.
	 */
	public SystemeEmbarque getSystemeDuBateau() {
		return systemeDuBateau;
	}
	
	/**
	 * <strong>Description : </strong>Exécution en entrée d'état. 
	 * Transmet une chaîne spécifique au serveur toute les secondes tant que le système embarqué qui implémente l'état
	 * et en {@code Stolen}.
	 * 
	 * @author P. Lledo, S. Queyrut
	 */
	public void onEntry() {
		
		while (systemeDuBateau.getCurrentState() == systemeDuBateau.getStolenState()) {
			systemeDuBateau.transmettreChaine("@state stolen");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
