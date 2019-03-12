/**
 * 
 */
package client;

import java.util.concurrent.*;

/* +--------------------+
 * | +----------------+ |              +------------------+
 * | |                | |              |                  |
 * | |  NoMonitoring  | +-------------->    Monitoring    |
 * | |                | <--------------+                  |
 * | +----------------+ |              +---------+--------+
 * +--------------------+                        |
 *            ^                                  |
 *            |                                  |
 *            | finAlarme                        | alarmeVol
 *            |                                  |
 *            |                                  |
 *    +-------+-------+                 +--------v--------+
 *    |               |                 |                 |
 *    |    Tracking   <-----------------+     Stolen      |
 *    |    ~~~~~~~~   |                 |                 |
 *    +---------------+                 +-----------------+
 *    
 * # ascii art was generated on asciiflow.com/ 
 * 
 */

/**
 * <strong>Description : </strong> État {@code Tracking} pour le système embarqué.
 *   
 * @author P. Lledo, S. Queyrut
 */
public class Tracking implements Etat {

	String stateLabel;
	
	SystemeEmbarque systemeDuBateau;
	
	/**
	 * <strong>Description : </strong>Constructeur pour l'état {@code Tracking}.
	 * 
	 * @param newSysEmbarque - système embarqué qui implémente l'état.
	 * @author P. Lledo, S. Queyrut
	 */
	public Tracking(SystemeEmbarque newSysEmbarque){
		
		stateLabel = "Tracking";
		
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
	 * <strong>Description : </strong>Exécution en entrée d'état. 
	 * Arrête la gestion des requêtes et lance l'envoi périodique (méthode {@code envoiPeriodique}.
	 * 
	 * @author P. Lledo, S. Queyrut
	 */
	@Override
	public void onEntry() {
		
		System.out.println("[+] Je passe en Tracking");
		envoiPeriodique(); // on commence l'envoi periodique
		this.systemeDuBateau.handling = false;
	}
	
	/**
	 * <strong>Description : </strong> Lance l'envoi périodique de la position du bateau au serveur. 
	 * L'envoi continue jusqu'au passage à l'état final.
	 * 
	 * @author P. Lledo, S. Queyrut
	 */
	public void envoiPeriodique () {
		
		while (systemeDuBateau.currentState == systemeDuBateau.getTrackingState()) {
			String chaine = systemeDuBateau.requestSensor("gps").getCapteurValueString();
			systemeDuBateau.transmettreChaine("@pos " + chaine);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
