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
 *    
 * # ascii art was generated on asciiflow.com/  
 * @author chenqun
 *
 */
public class Monitoring implements Etat {

	
	SystemeEmbarque systemeDuBateau;
	
	/**
	 * Constructeur pour l'état Monitoring
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
