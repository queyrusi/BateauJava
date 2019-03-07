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
 * @author chenqun
 *
 */
@SuppressWarnings("deprecation")
public class SystAlarme extends Observable implements Observer {  // observable par le syst, observeur des capteurs
	
	
	private List<SystemeEmbarque> systemesEmbList = new ArrayList<>();
	
	String capteurEnAlerte;
	
	boolean isTriggered = false;  // alarme non déclenchée
	
	
	public SystAlarme(SystemeEmbarque newSystemeEmbarque) {
		
		this.addObserverPerso(newSystemeEmbarque);
		
	}

	private void addObserverPerso(SystemeEmbarque newSystemeEmbarque) {

		addObserver(newSystemeEmbarque);
		this.systemesEmbList.add(newSystemeEmbarque);
	}

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
