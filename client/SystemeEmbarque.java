/**
 * 
 */
package client;

import java.io.BufferedReader;
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
	Etat etatDuSystemeEmbarque;

	// capteurs
	CapteurComposant capteurList; // contient chaque Capteur, CapteurGroupe et tout Capteur sauvegardé dans les
									// CapteurGroupes

	// variable d'activation du requestHandler
	boolean handling;

	// le système d'alarme
	SystAlarme systAlarme;
	
	// Runnable en paramètre du thread pour gérer les requêtes
	RequestHandler requestHandler;
	
	// Thread à executer une fois en Monitoring
	Thread requestHandlerThread;
	
	// SocIn qui prendra une valeur une fois la connexion établie
	private BufferedReader socIn;

	/**
	 * <strong>Description : </strong> Constructeur pour le système embarqué
	 * 
	 * <strong>Exemple : </strong> SystemeEmbarque("monServeur", "newLogin",
	 * "gps;thermometre")
	 * 
	 */
	public SystemeEmbarque(String unNomServeur, int unNumero, String unLogin, String EnsembleDesCapteurs) {

		super(unNomServeur, unNumero, unLogin);
		setTypeConnexion(" @Ship");

		estSurveille = new Monitoring(this);
		nonSurveille = new NoMonitoring(this);
		estVole = new Stolen(this);
		estTraque = new Tracking(this);

		etatDuSystemeEmbarque = nonSurveille; // état initial du système embarqué

		systAlarme = new SystAlarme(this);

		capteurList = new CapteurGroupe("Ensemble capteurs");

		// TODO il y a plusieurs capteurs a ajouter
		String[] Ensemble = EnsembleDesCapteurs.split(";");
		int k = 0;
		String typeDeCapteur = Ensemble[k];
		while (!typeDeCapteur.equals(null)) { // ajouter un à un les capteurs décrits par le string EnsembleDesCapteurs

			switch (typeDeCapteur) {

			case "GPS":
				
				GPS newGPS = new GPS("gps", this.systAlarme);
				getCapteurList().add(newGPS);
				break;
			}
			k++;
			typeDeCapteur = Ensemble[k];
		}
		
		requestHandler = new RequestHandler(this);
		requestHandlerThread = new Thread(requestHandlerThread);

	}

	/**
	 * <strong>Description : </strong> Change l'état du système embarqué
	 * 
	 * @param newSystemeEmbarqueState
	 */
	void changerEtat(Etat newSystemeEmbarqueState) {

		etatDuSystemeEmbarque.onExit();
		etatDuSystemeEmbarque = newSystemeEmbarqueState;
		etatDuSystemeEmbarque.onEntry();

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

	public CapteurComposant requestSensor(String sensor){
		
		for (CapteurComposant capteur : ((CapteurGroupe)capteurList).getcapteurComposants()) {
			
			if(capteur.getCapteurLabel().equals(sensor)) {
				
				return capteur;
			}
			
		}
		return null;
	}

	@Override
	public void update(Observable o, Object arg) { // est appellée quand le systeme d'alarme fait notifyObserver

		System.out.println("[+] Systeme Embarque se rend compte que l'alarme sonne.");
		System.out.println("[+] Le capteur qui s'est mis en branle est : " + (String) arg);
		this.changerEtat(this.estVole);
		System.out.println("[+] Je suis passe en Stolen");

	}

	public BufferedReader getSocIn() {
		return socIn;
	}

	public void setSocIn(BufferedReader socIn) {
		this.socIn = socIn;
	}

}
