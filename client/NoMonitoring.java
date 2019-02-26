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
 * <strong>Description : </strong> Etat {@code NoMonitoring} pour le bateau
 * 
 * 
 * @author chenqun
 *
 */
public class NoMonitoring implements Etat {

	
	SystemeEmbarque systemeDuBateau;
	
	/**
	 * Constructeur pour l'état {@code NoMonitoring}
	 * 
	 * @param newSysEmbarque
	 */
	public NoMonitoring(SystemeEmbarque newSysEmbarque){
		
		systemeDuBateau = newSysEmbarque;
		
	}

	public SystemeEmbarque getSystemeDuBateau() {
		return systemeDuBateau;
	}
	
	@Override
	public void getStatus() {
		
		System.out.println("Je ne suis pas sous surveillance !");
		// TODO Auto-generated method stub
		
	}

}
