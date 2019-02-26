/**
 * 
 */
package client;

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
 * <strong>Description : </strong> Etat {@code Monitoring} pour le bateau
 *     
 * @author chenqun
 *
 */
public class Monitoring implements Etat {

	
	SystemeEmbarque systemeDuBateau;
	
	/**
	 * Constructeur pour l'état {@code Monitoring}
	 * 
	 * @param newSysEmbarque
	 */
	public Monitoring(SystemeEmbarque newSysEmbarque){
		
		systemeDuBateau = newSysEmbarque;
		
	}
	
	public SystemeEmbarque getSystemeDuBateau() {
		return systemeDuBateau;
	}
	
	@Override
	public void getStatus() {
		
		System.out.println("Je suis sous surveillance !");
		// TODO Auto-generated method stub
		
	}
	
}
