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
 * <strong>Description : </strong> Classe permettant de créer des capteurs pour détecter une cause d'alarme.
 *
	 * @author P. Lledo, S. Queyrut
 *
 */
@SuppressWarnings("deprecation")
public abstract class Capteur extends Observable implements CapteurComposant {

	// liste des observeurs (abonnés) pour notify
	private List<SystAlarme> systAlarmeList = new ArrayList<>();  // TODO
	
	String capteurLabel;
	
	/**
	 * <strong>Description : </strong>Constructeur pour la classe {@code Capteur}.
	 * 
	 * @param newCapteurLabel - label du nouveau capteur.
	 * @param newSystAlarme - alarme que le capteur doit notifier en cas de problème.
	 * @author P. Lledo, S. Queyrut
	 */
	public Capteur(String newCapteurLabel, SystAlarme newSystAlarme) {
		
		capteurLabel = newCapteurLabel;  // gps1
		
		addObserverPerso(newSystAlarme);
		
	}
	
	/**
	 * <strong>Description : </strong>Ajoute un abonné {@code SystAlarme}.
	 * 
	 * @param newSystAlarme - alarme que le capteur doit notifier en cas de problème.
	 * @author P. Lledo, S. Queyrut
	 */
	private void addObserverPerso(SystAlarme newSystAlarme) {

		addObserver(newSystAlarme);
		this.systAlarmeList.add(newSystAlarme); // TODO
	}
	
	/**
	 * <strong>Description : </strong>Getter qui retourne le label du capteur.
	 * 
	 * @return capteurLabel - le label du capteur.
	 * @author P. Lledo, S. Queyrut
	 */
	public String getCapteurLabel() {
		
		return capteurLabel;
		
	}
	
	
	public abstract String getCapteurValueString();
	
	/**
	 * <strong>Description : </strong>Met l'alarme en branle.
	 * 
	 * @author P. Lledo, S. Queyrut
	 */
	public void warning() {
		
		System.out.println("[+] Capteur warning");
		System.out.println(this.systAlarmeList.get(0));
		String messageAlerte = "alarme " + this.capteurLabel;
		setChanged(); 
		notifyObservers(messageAlerte);

	}
	
	public abstract void start();
}
