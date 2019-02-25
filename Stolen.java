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
 * @author chenqun
 *
 */
public class Stolen implements Etat{

	
	SystemeEmbarque systemeDuBateau;
	
	/**
	 * Constructeur pour l'état Stolen
	 * 
	 * @param newSysEmbarque
	 */
	public Stolen(SystemeEmbarque newSysEmbarque){
		
		systemeDuBateau = newSysEmbarque;
		
	}
	
	@Override
	public void getStatus() {
		
		System.out.println("Je viens de me faire voler !");
		// TODO Auto-generated method stub
		
	}

}
