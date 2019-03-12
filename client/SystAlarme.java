/**
 * 
 */
package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

//==================
//TODO 7/3/19 09h05
//==================

/**
 * <strong>Description : </strong> Change l’état du bateau (notifie le système embarqué d’une alarme type voie d’eau, effraction etc.)
 * 
 * 
 * <dl>
 * <dt><span class="strong">Remarque</span></dt><dd>{@code SystAlarme} est observeur et observable
 * car elle doit recevoir une notification des capteurs, mais aussi notifier le système embarqué en cas
 * d'alarme.</dd>
 * </dl>
 * 
 * @author P. Lledo, S. Queyrut
 *
 */
@SuppressWarnings("deprecation")
public class SystAlarme extends Observable implements Observer {  // observable par le syst, observeur des capteurs
	
	
	private List<SystemeEmbarque> systemesEmbList = new ArrayList<>();
	
	String capteurEnAlerte;
	
	boolean isTriggered = false;  // alarme non déclenchée
	
	/**
	 * <strong>Description : </strong> Constructeur pour le système d'alarme.
	 * 
	 * @param newSystemeEmbarque - système embarqué auquel appartient le système d'alarme.
	 * @author P. Lledo, S. Queyrut
	 */
	public SystAlarme(SystemeEmbarque newSystemeEmbarque) {
		
		this.addObserverPerso(newSystemeEmbarque);
		
	}

	/**
	 * <strong>Description : </strong> Ajoute un abonné au système d'alarme.
	 * 
	 * @param newSystemeEmbarque - système embarqué auquel appartient le système d'alarme.
	 * @author P. Lledo, S. Queyrut
	 */
	private void addObserverPerso(SystemeEmbarque newSystemeEmbarque) {

		addObserver(newSystemeEmbarque);
		this.systemesEmbList.add(newSystemeEmbarque);
	}

	/**
	 * <strong>Description : </strong> Surcharge de la méthode {@code update}, met le système d'alarme en branle s'il reçoit
	 * un message d'alarme, notifie le système embarqué de cette alarme.
	 * 
	 * @param o - observable.
	 * @param arg - String contenant un message d'alarme.
	 * @author P. Lledo, S. Queyrut
	 */
	@Override
	public void update(Observable o, Object arg) {
	
		// on reçoit "alarme gps"
		if (((String) arg).substring(0, ((String) arg).indexOf(" ")).equals("alarme")) {
			
			this.capteurEnAlerte = ((String) arg).substring(((String) arg).indexOf(" ") + 1, ((String) arg).indexOf(" ") + 4);
					
			if (capteurEnAlerte.equals("gps")) {
				
				// TODO gestion des autres capteurs ?
				this.isTriggered = true;
				
			}
		}
		if (this.isTriggered == true) {
			
			System.out.println("[+] Alarme trigger");
			System.out.println("[+] Notifying embedded system...");
			this.setChanged();
			this.notifyObservers(this.capteurEnAlerte);
			
		}
		
	}	

}
