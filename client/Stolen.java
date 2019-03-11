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
 * <strong>Description : </strong> Etat Stolen pour le bateau
 *   
 * @author chenqun
 *
 */
public class Stolen implements Etat{

	String stateLabel;
	
	SystemeEmbarque systemeDuBateau;
	
	
	/**
	 * Constructeur pour l'état Stolen
	 * 
	 * @param newSysEmbarque
	 */
	public Stolen(SystemeEmbarque newSysEmbarque){
		
		stateLabel = "Stolen";
		
		systemeDuBateau = newSysEmbarque;
		
	}
	
	public String getStateLabel() {
		return stateLabel;
	}
	
	public SystemeEmbarque getSystemeDuBateau() {
		return systemeDuBateau;
	}
	
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
