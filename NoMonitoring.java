/**
 * 
 */
package client;

/** 
 * 
 * 
 * 
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
 * 
 * # ascii art was generated on asciiflow.com/
 * @author chenqun
 *
 */
public class NoMonitoring implements Etat {

	
	SystemeEmbarque systemeDuBateau;
	
	/**
	 * Constructeur pour l'état Monitoring
	 * 
	 * @param newSysEmbarque
	 */
	public NoMonitoring(SystemeEmbarque newSysEmbarque){
		
		systemeDuBateau = newSysEmbarque;
		
	}
	
	@Override
	public void getStatus() {
		
		System.out.println("Je ne suis pas sous surveillance !");
		// TODO Auto-generated method stub
		
	}

}
