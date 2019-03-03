package server.requestHandlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Calendar;

import server.CentralContext;
import server.entities.Position;
import server.entities.Ship;
import server.patterns.tcp.IContext;
import server.patterns.tcp.ShipStateRequestHandler;
import server.shipStates.MonitoredState;
import server.shipStates.NotMonitoredState;
import server.shipStates.TrackingState;

/**
 * <strong>Description : </strong>Thread gérant les requêtes d'un bateau en état Not monitored
 * @author C.Silva, R.Cuinat
 */
public class TrackingStateRequestHandler extends ShipStateRequestHandler {
	private CentralContext centralContext;
	private BufferedReader socketInput;
	private BufferedWriter socketOutput;
	private Ship ship;

	/**
	 * <strong>Description : </strong>Constructeur du thread gérant les requêtes d'un bateau en état Not monitored
	 * @author C.Silva, R.Cuinat
	 * @param ship un objet bateau
	 * @param aContext contexte d'exécution du serveur
	 * @param socketIn socket pour la lecture
	 * @param socketOut socket pour l'écriture
	 */
	public TrackingStateRequestHandler(Ship ship,IContext aContext, BufferedReader socketIn, BufferedWriter socketOut) {
		// TODO Auto-generated constructor stub
		this.centralContext = (CentralContext) aContext;
		this.socketInput = socketIn;
		this.socketOutput = socketOut;
		this.ship = ship;
	}

	/**
	 * <strong>Description : </strong>Méthode exécutée au lancement du thread. Gère les requêtes d'un bateau en état Tracking.
	 * @author C.Silva, R.Cuinat
	 */
	@Override
	public void run() {
		try {
			try {
				if (ship.getRequestHandler()!=null) {
				ship.getRequestHandler().join();}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ship.setRequestHandler(this);
			ship.setState(new TrackingState(ship));
			centralContext.addRequestHandler(this);
			String line;
			while ((line = socketInput.readLine()).compareTo("@quit") != 0) {
				String[] message = line.split(" ");
				if (message.length > 0) {
				if (message[0].compareTo("@pos") == 0) {
							if (message.length == 3) {
								try {
									this.ship.setPosition(new Position(Double.parseDouble(message[1]),Double.parseDouble(message[2])));
									this.ship.setDate(Calendar.getInstance());
								}
								catch (NumberFormatException e) {
									System.out.println("Message position invalide reçu du bateau " + this.ship.getImmatriculation() + " (msg = " + line + ")");
								}
							}
							else {
								System.out.println("Message position invalide reçu du bateau " + this.ship.getImmatriculation() + " (msg = " + line + ")");
							}			
				}
				}
				else System.out.println("Le bateau "+ship.getImmatriculation()+" a envoyé un message vide.");
				}
			centralContext.removeRequestHandler(this);
			socketInput.close();
			socketOutput.close();
			ship.setRequestHandler(null);
			ship.setState(new NotMonitoredState(ship));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read from client socket");
			e.printStackTrace();
		}
	}
}
