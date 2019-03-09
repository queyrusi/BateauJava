/**
 * 
 */
package client;

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
	public void getStatus() {
		
		System.out.println("Je me fait traquer !");
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onEntry(){
		this.systemeDuBateau.handling = true;
	}

}
