/**
 * 
 */
package client;

import java.util.Observable;
import java.util.Observer;

//==================
//TODO 9/3/19 10h22
//==================

/**
 * <strong>Description : </strong> Discute avec le serveur (et peut lui envoyer
 * des messages d'alarme)
 * 
 * 
 * @author chenqun
 *
 */
@SuppressWarnings("deprecation")
public class SystemeEmbarque extends Client implements Observer {

	// liste des états possibles du système embarqué :
	Etat estSurveille;
	Etat nonSurveille;
	Etat estVole;
	Etat estTraque;

	// état actuel du système embarqué
	protected Etat etatDuSystemeEmbarque;

	// capteurs
	CapteurComposant capteurList; // contient chaque Capteur, CapteurGroupe et tout Capteur sauvegardé dans les
									// CapteurGroupes
	
	// variable d'activation du requestHandler
	boolean handling;
	
	// le système d'alarme
	SystAlarme systAlarme;

	/**
	 * <strong>Description : </strong> Constructeur pour le système embarqué
	 * 
	 */
	public SystemeEmbarque(String unNomServeur, int unNumero, String unLogin, String EnsembleDesCapteur) {

		super(unNomServeur, unNumero, unLogin);
		setTypeConnexion("@Ship");

		estSurveille = new Monitoring(this);
		nonSurveille = new NoMonitoring(this);
		estVole = new Stolen(this);
		estTraque = new Tracking(this);

		etatDuSystemeEmbarque = nonSurveille; // état initial du système embarqué

		systAlarme = new SystAlarme(this);
		// /!\ /!\ Je crois qu'il y a quelques soucis ici
		capteurList = new CapteurGroupe("Ensemble capteurs");
		// TODO il y a plusieurs capteurs a ajouter
		String[] Ensemble = EnsembleDesCapteurs.split(";");
		int k=0;
		String typeDeCapteur = Ensemble[k];
		while(!typeDeCapteur.equals(null)){
			switch(typeDeCapteur) {
			case "GPS":
				GPS newGPS = new GPS("gps", 0, this.systAlarme);
				getCapteurList().add(newGPS);
				break;
			}
			k++;
			String typeDeCapteur = Ensemble[k];
		}

	}

	/**
	 * <strong>Description : </strong> Change l'état du système embarqué
	 * 
	 * @param newSystemeEmbarqueState
	 */
	void changerEtat(Etat newSystemeEmbarqueState) {

		etatDuSystemeEmbarque = newSystemeEmbarqueState;

	}

	// Les méthodes ci-dessous permettent aux autres classes de changer les états du
	// système embarqué => publiques

	/**
	 * <strong>Description : </strong> Retourne l'état {@code estSurveille} du
	 * système embarqué ({@code estSurveille} est un état {@code Monitoring}).
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
	 * <strong>Description : </strong> Retourne l'état {@code nonSurveille} du
	 * système embarqué
	 * 
	 * @return
	 */
	public Etat getNoMonitoringState() {

		return nonSurveille;
	}

	/**
	 * <strong>Description : </strong> Retourne l'état {@code estVole} du système
	 * embarqué
	 * 
	 * @return
	 */
	public Etat getStolenState() {

		return estVole;
	}

	/**
	 * <strong>Description : </strong> Retourne l'état {@code estTraque} du système
	 * embarqué
	 * 
	 * @return
	 */
	public Etat getTrackingState() {

		return estTraque;
	}

	public CapteurComposant getCapteurList() {

		return capteurList;
	}

	public void setCapteurList(CapteurComposant capteurList) {

		this.capteurList = capteurList;
	}
	
	public Capteur requestSensor(String sensor){
		Capteur capteur = Capteur.stream().filter(Capteur -> sensor.equals(Capteur.getCapteurLabel())).findAny().orElse(null);
		return capteur
	}

	@Override
	public void update(Observable o, Object arg) { // est appellée quand le systeme d'alarme fait notifyObserver

		System.out.println("[+] Systeme Embarque se rend compte que l'alarme sonne.");
		System.out.println("[+] Le capteur qui s'est mis en branle est : " + (String) arg);
		this.changerEtat(this.estVole);
		System.out.println("[+] Je suis passe en Stolen");

	}

}
