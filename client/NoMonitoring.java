/**
 * 
 */
package client;

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
 * <strong>Description : </strong> État {@code NoMonitoring} pour le système embarqué.
 *   
 * @author P. Lledo, S. Queyrut
 */
public class NoMonitoring implements Etat {

	String stateLabel;
	
	SystemeEmbarque systemeDuBateau;
	
	/**
	 * <strong>Description : </strong>Constructeur pour l'état {@code NoMonitoring}.
	 * 
	 * @param newSysEmbarque - système embarqué qui implémente l'état.
	 * @author P. Lledo, S. Queyrut
	 */
	public NoMonitoring(SystemeEmbarque newSysEmbarque){
		
		stateLabel = "NoMonitoring";
		
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
	 * <strong>Description : </strong>Initie une tentative de connexion du système embarqué avec un serveur distant.
	 * Une fois la connexion établie, passe le système embarqué en état {@code Monitoring}.
	 * 
	 * @author P. Lledo, S. Queyrut
	 * @return le label de l'état.
	 */
	public void start() {
		
		while (!systemeDuBateau.connecterAuServeur()) {
			System.out.println("[+] en attente de connexion...");
		}
		// connexion établie, on passe en état Monitoring :
		systemeDuBateau.changerEtat(systemeDuBateau.getMonitoringState());
	}
}
