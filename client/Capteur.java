/**
 * 
 */
package client;

import java.util.ArrayList;
import java.util.List;

//==================
//TODO 7/3/19 09h05
//==================

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

	// liste des observeurs (abonnés) pour notify
	private List<SystAlarme> systAlarmeList = new ArrayList<>();  // TODO
	
	String capteurLabel;
	
	int capteurValue;
	
	
	public Capteur(String newCapteurLabel, int newCapteurValue, SystAlarme newSystAlarme) {
		
		capteurValue = newCapteurValue;  // 125.912 par exemple
		capteurLabel = newCapteurLabel;  // gps1
		
		addObserverPerso(newSystAlarme);
	}
	
	private void addObserverPerso(SystAlarme newSystAlarme) {

		addObserver(newSystAlarme);
		this.systAlarmeList.add(newSystAlarme); // TODO
	}
	
	public String getCapteurLabel() {
		
		return capteurLabel;
		
	}
	
	public int getCapteurValue() {
		
		return capteurValue;
		
	}
	
	public void warning() {
		
		System.out.println("[+] Capteur warning");
		System.out.println(this.systAlarmeList.get(0));
		String messageAlerte = "alarme " + this.capteurLabel;
		setChanged(); 
		notifyObservers(messageAlerte);

	}
}
