/**
 * 
 */
package client;

import java.util.Observable;
import java.util.Observer;


/**
 * Change l’état du bateau (notifie le système embarqué d’une alarme type voie d’eau, effraction etc.)
 * 
 * Collaborateurs :
 * 
 * +--------------+    +----------+
 * |   Capteur    |    |   Etat   |
 * +--------------+    +----------+
 * |              |    |          |
 * |              |    |          |
 * +--------------+    +----------+
 *
 * # ascii art was generated on asciiflow.com/
 * 
 * <dl>
 * <dt><span class="strong">Remarque</span></dt><dd>{@code SystAlarme} est observer et observable
 * car elle doit recevoir une notification des capteurs, mais aussi notifier le système embarqué en cas
 * d'alarme.</dd>
 * </dl>
 * 
 * @author chenqun
 *
 */
@SuppressWarnings("deprecation")
public class SystAlarme extends Observable implements Observer {
	
	boolean isTriggered = false;  // alarme non déclenchée

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
