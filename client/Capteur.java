/**
 * 
 */
package client;

//===================
//TODO 4/2/19 23h03
//===================

import java.util.Observable;

/* * Collaborateurs :
 * 
 * +--------------+    +---------------------+
 * |  SystAlarme  |    |   SystemeEmbarque   |
 * +--------------+    +---------------------+
 * |              |    |                     |
 * |              |    |                     |
 * +--------------+    +---------------------+
 * 
 */

/**
 * <strong>Description : </strong> Classe permettant de creer des objets pour détecter une cause d'alarme.
 *
 * @author chenqun
 *
 */
@SuppressWarnings("deprecation")
public class Capteur extends Observable implements CapteurComposant {

	String capteurName;
	String capteurLabel;
	
	public Capteur(String newCapteurName, String newCapteurLabel) {
		
		capteurName = newCapteurName;
		capteurLabel = newCapteurLabel;
		
	}
	
	public String getCapteurLabel() {
		
		return capteurLabel;
		
	}
	
	public String getCapteurName() {
		
		return capteurName;
		
	}
}
