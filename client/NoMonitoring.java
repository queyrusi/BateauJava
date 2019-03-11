/**
 * 
 */
package client;

//==================
//TODO 7/3/19 09h05
//==================

/*
 * +--------------------+
 * | +----------------+ |              +------------------+
 * | |                | |              |                  |
 * | |  NoMonitoring  | +-------------->    Monitoring    |
 * | |  ~~~~~~~~~~~~  | <--------------+                  |
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
 * <strong>Description : </strong> Etat {@code NoMonitoring} pour le bateau
 * 
 * 
 * @author chenqun
 *
 */
public class NoMonitoring implements Etat {

	String stateLabel;
	
	SystemeEmbarque systemeDuBateau;
	
	/**
	 * Constructeur pour l'état {@code NoMonitoring}
	 * 
	 * @param newSysEmbarque
	 */
	public NoMonitoring(SystemeEmbarque newSysEmbarque){
		
		stateLabel = "NoMonitoring";
		
		systemeDuBateau = newSysEmbarque;
		
	}

	public SystemeEmbarque getSystemeDuBateau() {
		return systemeDuBateau;
	}

	
	public void start() {
		
		while (!systemeDuBateau.connecterAuServeur()) {
			
			System.out.println("[+] en attente de connexion...");
			
		}
		
		// connexion établie, on passe en état Monitoring :
		systemeDuBateau.changerEtat(systemeDuBateau.getMonitoringState());
	}
}
