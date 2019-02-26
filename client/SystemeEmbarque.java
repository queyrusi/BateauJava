/**
 * 
 */
package client;

/**
 * <strong>Description : </strong> Discute avec le serveur (et peut lui envoyer des messages d'alarme)
 * 
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
	 * <strong>Description : </strong> Constructeur pour le système embarqué
	 * 
	 */
	public SystemeEmbarque(String unNomServeur, int unNumero, String unLogin) {
		
		super(unNomServeur,unNumero,unLogin);
		setTypeConnexion("@Ship");
		
		estSurveille = new Monitoring(this);
		nonSurveille = new NoMonitoring(this);
		estVole = new Stolen(this);
		estTraque = new Tracking(this);
		
		etatDuSystemeEmbarque = nonSurveille;  // état initial du système embarqué
		
	}
	
	/**
	 * <strong>Description : </strong> Change l'état du système embarqué
	 * 
	 * @param newSystemeEmbarqueState
	 */
	void changerEtat(Etat newSystemeEmbarqueState){
		
		etatDuSystemeEmbarque = newSystemeEmbarqueState;
		
	}
	
	// Les méthodes ci-dessous permettent aux autres classes de changer les états du
	// système embarqué => publiques
	
	/**
	 * <strong>Description : </strong> Retourne l'état {@code estSurveille} du système embarqué ({@code estSurveille} est un état 
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
	 * <strong>Description : </strong> Retourne l'état {@code nonSurveille} du système embarqué
	 * @return
	 */
	public Etat getNoMonitoringState() {
		
		return nonSurveille;
	}
	
	/**
	 * <strong>Description : </strong> Retourne l'état {@code estVole} du système embarqué
	 * @return
	 */
	public Etat getStolenState() {
		
		return estVole;
	}
	
	/**
	 * <strong>Description : </strong> Retourne l'état {@code estTraque} du système embarqué
	 * @return
	 */
	public Etat getTrackingState() {
		
		return estTraque;
	}
	
}
