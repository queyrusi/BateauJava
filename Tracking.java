/**
 * 
 */
package client;

/**
 * 
 * 
 * +--------------------+
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
 * @author chenqun
 *
 */
public class Tracking implements Etat {

	SystemeEmbarque systemeDuBateau;
	
	/**
	 * Constructeur pour l'état Tracking
	 * 
	 * @param newSysEmbarque
	 */
	public Tracking(SystemeEmbarque newSysEmbarque){
		
		systemeDuBateau = newSysEmbarque;
		
	}
	
	@Override
	public void getStatus() {
		
		System.out.println("Je me fait traquer !");
		// TODO Auto-generated method stub
		
	}

}
