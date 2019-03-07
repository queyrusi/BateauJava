/**
 * 
 */
package client;

//==================
//TODO 7/3/19 09h05
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
 * 
 */

/**
 * <strong>Description : </strong> Etat Stolen pour le bateau
 *   
 * @author chenqun
 *
 */
public class Stolen implements Etat{

	String stateLabel;
	
	SystemeEmbarque systemeDuBateau;
	
	
	/**
	 * Constructeur pour l'état Stolen
	 * 
	 * @param newSysEmbarque
	 */
	public Stolen(SystemeEmbarque newSysEmbarque){
		
		stateLabel = "Stolen";
		
		systemeDuBateau = newSysEmbarque;
		
	}
	
	public SystemeEmbarque getSystemeDuBateau() {
		return systemeDuBateau;
	}

	@Override
	public void getStatus() {
		
		System.out.println("Je viens de me faire voler !");
		// TODO Auto-generated method stub
		
	}


}
