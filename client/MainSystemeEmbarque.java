/**
 * 
 */
package client;

//===================
//TODO 6/3/19 18h23
//===================

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author chenqun
 *
 */
public class MainSystemeEmbarque {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SystemeEmbarque newSys = new SystemeEmbarque("LAPTOP-D7S9B5VD", 6666, "tata", "GPS"); // TODO : toto aussi ?
		System.out.println("Done ship");
//		newSys.connecterAuServeur();
		
		// l'état peut passer en Monitoring une fois la connection établie
		newSys.changerEtat(newSys.getMonitoringState());
		
		System.out.println(newSys.etatDuSystemeEmbarque);
		
		((Capteur) newSys.getCapteurList().getComposant(0)).warning();  // lance l'alarme 
		
		
//	    new Thread("mettre ici un runnable").start();

		while (true) {
		}
	}

}
