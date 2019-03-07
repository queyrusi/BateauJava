/**
 * 
 */
package client;

//==================
//TODO 7/3/19 09h05
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
 * <strong>Description : </strong> Etat {@code Monitoring} pour le bateau
 *     
 * @author chenqun
 *
 */
public class Monitoring implements Etat {

	String stateLabel;
	
	SystemeEmbarque systemeDuBateau;
	
	/**
	 * Constructeur pour l'état {@code Monitoring}
	 * 
	 * @param newSysEmbarque
	 */
	public Monitoring(SystemeEmbarque newSysEmbarque){
		
		stateLabel = "Monitoring";
		
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
