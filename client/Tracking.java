/**
 * 
 */
package client;

import java.util.concurrent.*;
//==================
//TODO 9/3/19 10h27
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
 * <strong>Description : </strong> Etat tracking pour le bateau
 *   
 * @author chenqun
 *
 */
public class Tracking implements Etat {

	String stateLabel;
	
	SystemeEmbarque systemeDuBateau;
	
	/**
	 * Constructeur pour l'état Tracking
	 * 
	 * @param newSysEmbarque
	 */
	public Tracking(SystemeEmbarque newSysEmbarque){
		
		stateLabel = "Tracking";
		
		systemeDuBateau = newSysEmbarque;
		
	}
	
	public SystemeEmbarque getSystemeDuBateau() {
		
		return systemeDuBateau;
	}

	@Override
	public void onEntry() {
		
		envoiPeriodique(); // on commence l'envoit periodique
		this.systemeDuBateau.handling = false;
	}
	
	/**
	 * <strong>Description : </strong> Lance l'envoi périodique de la position du bateau au serveur
	 * 
	 */
	public void envoiPeriodique () {
		
		while (systemeDuBateau.currentState == systemeDuBateau.getTrackingState()) {
			
			String chaine = systemeDuBateau.requestSensor("gps").getCapteurValueString();
			systemeDuBateau.transmettreChaine(chaine);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
