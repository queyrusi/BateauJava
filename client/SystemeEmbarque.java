/**
 * 
 */
package client;

import java.util.Observable;
import java.util.Observer;
import fr.ensta.fx.boatmonitoring.FXBoatUI;


/**
 * <strong>Description : </strong> Discute avec le serveur (et peut lui envoyer
 * des messages d'alarme)
 * 
 * 
 * @author P. Lledo, S. Queyrut
 */
@SuppressWarnings("deprecation")
public class SystemeEmbarque extends Client implements Observer {

	// liste des états possibles du système embarqué :
	Etat estSurveille;
	Etat nonSurveille;
	Etat estVole;
	Etat estTraque;

	// état actuel du système embarqué
	Etat currentState;

	// capteurs
	CapteurComposant capteurList; // contient chaque Capteur, CapteurGroupe et tout Capteur sauvegardé dans les
								  // CapteurGroupes

	// variable d'activation du requestHandler
	boolean handling;
	
	//variable discriminant la pr�sence d'un affichage
	private boolean hasUI;

	// le système d'alarme
	SystAlarme systAlarme;
	
	// Runnable en paramètre du thread pour gérer les requêtes
	RequestHandler requestHandler;
	
	// Thread à executer une fois en Monitoring
	Thread requestHandlerThread;
	

	/**
	 * <strong>Description : </strong> Constructeur pour le système embarqué
	 * 
	 * <strong>Exemple : </strong> SystemeEmbarque("monServeur", "newLogin",
	 * "gps;thermometre")
	 * 
	 * @param unNomServeur - hostname du serveur que le système embarqué cherche à joindre.
	 * @param unNumero - numéro de port du serveur que le système embarqué cherche à joindre.
	 * @param unLogin - login d'accès au serveur.
	 * @param unPassword - mot de passe associ� au login d'acc�s.
	 * @param EnsembleDesCapteurs - string contenant les capteurs dont sera doté le système embarqué.
	 * 
	 */
	public SystemeEmbarque(String unNomServeur, int unNumero, String unLogin, String unPassword, String EnsembleDesCapteurs) {

		super(unNomServeur, unNumero, unLogin, unPassword);
		setTypeConnexion("@Ship");
		
		hasUI=false;

		estSurveille = new Monitoring(this);
		nonSurveille = new NoMonitoring(this);
		estVole = new Stolen(this);
		estTraque = new Tracking(this);

		currentState = nonSurveille; // état initial du système embarqué

		systAlarme = new SystAlarme(this);

		capteurList = new CapteurGroupe("Ensemble capteurs");

		// TODO il y a plusieurs capteurs a ajouter
		String[] Ensemble = EnsembleDesCapteurs.split(";");
		int k = 0;
		String typeDeCapteur = Ensemble[k];
		
		// tnat qu'il y a des capteurs a ajouter
		while (!typeDeCapteur.equals(null) & k < Ensemble.length)  { //ajouter un à un les capteurs décrits par le string EnsembleDesCapteurs
				
			typeDeCapteur = Ensemble[k];
			
			switch (typeDeCapteur) {

			case "GPS":
				
				GPS newGPS = new GPS("gps", this.systAlarme);
				getCapteurList().add(newGPS);
				break;
			}	
			k++;  // on passe au capteur suivant

		}
		
		requestHandler = new RequestHandler(this);
		requestHandlerThread = new Thread(requestHandler);

	}

	/**
	 * <strong>Description : </strong> Constructeur pour le système embarqué
	 * 
	 * Cette version impl�mente la pr�sence d'une UI
	 * 
	 * <strong>Exemple : </strong> SystemeEmbarque("monServeur", "newLogin",
	 * "gps;thermometre")
	 * 
	 * @param unNomServeur - hostname du serveur que le système embarqué cherche à joindre.
	 * @param unNumero - numéro de port du serveur que le système embarqué cherche à joindre.
	 * @param unLogin - login d'accès au serveur.
	 * @param unPassword - mot de passe associ� au login d'acc�s.
	 * @param EnsembleDesCapteurs - string contenant les capteurs dont sera doté le système embarqué.
	 * @param unUI - UI associ�e au client.
	 * 
	 */
	public SystemeEmbarque(String unNomServeur, int unNumero, String unLogin, String unPassword, FXBoatUI unUI, String EnsembleDesCapteurs) {

		super(unNomServeur, unNumero, unLogin, unPassword, unUI);
		setTypeConnexion("@Ship");
		
		hasUI=true;

		estSurveille = new Monitoring(this);
		nonSurveille = new NoMonitoring(this);
		estVole = new Stolen(this);
		estTraque = new Tracking(this);

		currentState = nonSurveille; // état initial du système embarqué

		systAlarme = new SystAlarme(this);

		capteurList = new CapteurGroupe("Ensemble capteurs");

		// TODO il y a plusieurs capteurs a ajouter
		String[] Ensemble = EnsembleDesCapteurs.split(";");
		int k = 0;
		String typeDeCapteur = Ensemble[k];
		
		// tnat qu'il y a ds capteurs a ajouter
		while (!typeDeCapteur.equals(null) & k < Ensemble.length)  { //ajouter un à un les capteurs décrits par le string EnsembleDesCapteurs
				
			typeDeCapteur = Ensemble[k];
			
			switch (typeDeCapteur) {

			case "GPS":
				
				GPS newGPS = new GPS("gps", this.systAlarme);
				getCapteurList().add(newGPS);
				break;
			}	
			k++;  // on passe au capteur suivant

		}
		
		requestHandler = new RequestHandler(this);
		requestHandlerThread = new Thread(requestHandler);

	}
	
	/**
	 * <strong>Description : </strong>Retourne l'état courant du système embarqué.
	 * 
	 * @author P. Lledo, S. Queyrut
	 * @return l'état courant du système embarqué.
	 */
	public Etat getCurrentState() {
		return currentState;
	}
	
	/**
	 * <strong>Description : </strong>Retourne s'il y a pr�sence d'une UI.
	 * 
	 * @author P. Lledo, S. Queyrut
	 * @return l'état courant du système embarqué.
	 */
	public boolean gethasUI() {
		return hasUI;
	}

	/**
	 * <strong>Description : </strong> Change l'état du système embarqué.
	 * 
	 * @param newSystemeEmbarqueState - état dans lequel on veut mettre le système embarqué.
	 * @author P. Lledo, S. Queyrut
	 */
	public void changerEtat(Etat newSystemeEmbarqueState) {

		currentState.onExit();
		currentState = newSystemeEmbarqueState;
		currentState.onEntry();

	}

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
	 * @return l'état {@code Monitoring} du système embarqué.
	 * @author P. Lledo, S. Queyrut
	 */
	public Etat getMonitoringState() {

		return estSurveille;
	}

	/**
	 * <strong>Description : </strong> Retourne l'état {@code nonSurveille} du
	 * système embarqué
	 * 
	 * @return l'état {@code NoMonitoring} du système embarqué.
	 * @author P. Lledo, S. Queyrut
	 */
	public Etat getNoMonitoringState() {

		return nonSurveille;
	}

	/**
	 * <strong>Description : </strong> Retourne l'état {@code estVole} du système
	 * embarqué
	 * 
	 * @return l'état {@code Stolen} du système embarqué.
	 * @author P. Lledo, S. Queyrut
	 */
	public Etat getStolenState() {

		return estVole;
	}

	/**
	 * <strong>Description : </strong> Retourne l'état {@code estTraque} du système
	 * embarqué.
	 * 
	 * @return l'état {@code Tracking} du système embarqué.
	 * @author P. Lledo, S. Queyrut
	 */
	public Etat getTrackingState() {

		return estTraque;
	}

	/**
	 * <strong>Description : </strong> Retourne l'array de capteurs du système
	 * embarqué.
	 * 
	 * @return la liste (type {@code CapteurComposant}) des capteurs du système embarqué.
	 * @author P. Lledo, S. Queyrut
	 * 
	 */
	public CapteurComposant getCapteurList() {

		return capteurList;
	}

	/**
	 * <strong>Description : </strong> Setter de l'array de capteurs du système
	 * embarqué.
	 *
	 * @param capteurList - array de capteurs du système embarqué.
	 * @author P. Lledo, S. Queyrut
	 */
	public void setCapteurList(CapteurComposant capteurList) {

		this.capteurList = capteurList;
	}

	/**
	 * <strong>Description : </strong> Retourne le capteur appellé dans le string en paramètre.
	 * 
	 * Un exemple d'utilisation dans une classe distante est :
	 *
	 * <pre>
	 * {@code
	 *  systemeDuBateau.requestSensor("gps");
	 * }
	 * </pre>
	 *
	 * @param sensor - string (label) du capteur auquel on veut accéder.
	 * @return le capteur auquel on veut accéder.
	 * @author P. Lledo, S. Queyrut
	 */
	public CapteurComposant requestSensor(String sensor){
		
		for (CapteurComposant capteur : ((CapteurGroupe)capteurList).getcapteurComposants()) {
			
			if(capteur.getCapteurLabel().equals(sensor)) {
				
				return capteur;
			}
			
		}
		return null;
	}

	/**
	 * <strong>Description : </strong> Change l'état du système embarqué à {@code estVole} (type {@code Stolen})
	 * après mise en branle d'un capteur (utilisation de {@code notify}).
	 * 
	 * Un exemple d'utilisation dans une classe distante est :
	 *
	 * <pre>
	 * {@code
	 *  systemeDuBateau.requestSensor("gps");
	 * }
	 * </pre>
	 *
	 * @param o - observable qui peut déclencher la méthode {@code update}.
	 * @param arg - label du capteur qui se met en branle.
	 * @author P. Lledo, S. Queyrut
	 */
	@Override
	public void update(Observable o, Object arg) { // est appellée quand le systeme d'alarme fait notifyObserver
		if(!gethasUI()) {
			System.out.println("[+] Systeme Embarque se rend compte que l'alarme sonne.");
			System.out.println("[+] Le capteur qui s'est mis en branle est : " + (String) arg);
			this.changerEtat(this.estVole);
			System.out.println("[+] Alerte vol !");
		}else {
			// TODO remplacer les println par des entr�es dans le log
			this.getUI().getLogList().add("[+] Systeme Embarque se rend compte que l'alarme sonne.");
			this.getUI().getLogList().add("[+] Le capteur qui s'est mis en branle est : " + (String) arg);
			this.changerEtat(this.estVole);
			this.getUI().getLogList().add("[+] Alerte vol !");
		}

	}

}
