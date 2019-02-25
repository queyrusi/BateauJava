/**
 * 
 */
package client;

/**
 * Discute avec le serveur (et peut lui envoyer des messages d'alarme)
 * 
 * Collaborateurs : 
 * 
 * +--------------+    +-------------+    +--------------+
 * |  ServeurTCP  |    |   Capteur   |    |     Etat     |
 * +--------------+    +-------------+    +--------------+
 * |              |    |             |    |              |
 * |              |    |             |    |              |
 * +--------------+    +-------------+    +--------------+
 *
 * # ascii art was generated on asciiflow.com/
 * 
 * @author chenqun
 *
 */
public class SystemeEmbarque extends Client {
	
	// liste des états possibles du système embarqué :
	Etat estSurveille;     
	Etat nonSurveille;
	Etat estVole;
	Etat estTraque;
	
	// état actuel du système embarqué
	Etat etatDuSystemeEmbarque;
	
	/**
	 * Constructeur pour le système embarqué
	 * 
	 */
	public SystemeEmbarque(String unNomServeur, int unNumero, String unLogin) {
		
		super(unNomServeur,unNumero,unLogin);
		typeConnexion = "@Ship";
		
		estSurveille = new Monitoring(this);
		nonSurveille = new NoMonitoring(this);
		estVole = new Stolen(this);
		estTraque = new Tracking(this);
		
		etatDuSystemeEmbarque = nonSurveille;  // état initial du système embarqué
		
	}
	
	/**
	 * Change l'état du système embarqué
	 * 
	 * @param newSystemeEmbarqueState
	 */
	void changerEtat(Etat newSystemeEmbarqueState){
		
		etatDuSystemeEmbarque = newSystemeEmbarqueState;
		
	}
	
	// Les méthodes ci-dessous permettent aux autres classes de changer les états du
	// système embarqué => publiques
	
	/**
	 * Retourne l'état {@code estSurveille} du système embarqué ({@code estSurveille} est un état 
	 * {@code Monitoring}).
	 * 
	 * Un exemple d'utilisation dans une classe distante est :
	 *
	 * <pre>
	 * {@code
	 *  systemeDuBateau.changerEtat(systemeDuBateau.getMonitoringState());
	 * }
	 * </pre>
	 * 
	 * L'état du système embarqué est maintenant {@code estSurveille}.
	 * 
	 * @return
	 */
	public Etat getMonitoringState() {
		
		return estSurveille;
	}
	
	/**
	 * Retourne l'état {@code nonSurveille} du système embarqué
	 * @return
	 */
	public Etat getNoMonitoringState() {
		
		return nonSurveille;
	}
	
	/**
	 * Retourne l'état {@code estVole} du système embarqué
	 * @return
	 */
	public Etat getStolenState() {
		
		return estVole;
	}
	
	/**
	 * Retourne l'état {@code estTraque} du système embarqué
	 * @return
	 */
	public Etat getTrackingState() {
		
		return estTraque;
	}
	
}
