package server.requestHandlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import server.CentralContext;
import server.entities.Ship;
import server.patterns.tcp.IContext;
import server.patterns.tcp.ShipStateRequestHandler;

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
	 * <strong>Description : </strong>Thread gérant les requêtes d'un bateau en état Not monitored
	 * @author C.Silva, R.Cuinat
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
			centralContext.addRequestHandler(this);
			String line;
			while ((line = socketInput.readLine()).compareTo("@quit") != 0) {
			}
			centralContext.removeRequestHandler(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read from client socket");
			e.printStackTrace();
		}
	}
}
