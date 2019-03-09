/**
 * 
 */
package client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

//==================
//TODO 9/3/19 10h47
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
public abstract class Capteur extends Observable implements CapteurComposant {

	// liste des observeurs (abonnés) pour notify
	private List<SystAlarme> systAlarmeList = new ArrayList<>();  // TODO
	
	String capteurLabel;
	
	
	public Capteur(String newCapteurLabel, SystAlarme newSystAlarme) {
		
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
	
	public abstract String getCapteurValueString();
	
	public void warning() {
		
		System.out.println("[+] Capteur warning");
		System.out.println(this.systAlarmeList.get(0));
		String messageAlerte = "alarme " + this.capteurLabel;
		setChanged(); 
		notifyObservers(messageAlerte);

	}
	
	public abstract void start();
}
