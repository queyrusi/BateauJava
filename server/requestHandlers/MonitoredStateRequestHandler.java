package server.requestHandlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import server.CentralContext;
import server.entities.Ship;
import server.patterns.tcp.IContext;
import server.patterns.tcp.ShipStateRequestHandler;

/**
 * <strong>Description : </strong>Thread gérant les requêtes d'un bateau en état monitored
 * @author C.Silva, R.Cuinat
 */
public class MonitoredStateRequestHandler extends ShipStateRequestHandler {
	
	private CentralContext centralContext;
	private BufferedReader socketInput;
	private BufferedWriter socketOutput;
	private Ship ship;

	/**
	 * <strong>Description : </strong>Thread gérant les requêtes d'un bateau en état monitored
	 * @author C.Silva, R.Cuinat
	 * @param aContext contexte d'exécution du serveur
	 * @param socketIn socket pour la lecture
	 * @param socketOut socket pour l'écriture
	 */
	public MonitoredStateRequestHandler(IContext aContext, BufferedReader socketIn, BufferedWriter socketOut) {
		this.centralContext = (CentralContext) aContext;
		this.socketInput = socketIn;
		this.socketOutput = socketOut;
	}
	@Override
public void run() {
		try {
			String immatriculation = socketInput.readLine();
			try {
				this.ship = centralContext.getShip(immatriculation);
				System.out.println("Le bateau est immatriculé : " + immatriculation);
				ship.setRequestHandler(this);
				centralContext.addRequestHandler(this);
				String line;
				while ((line = socketInput.readLine()).compareTo("@quit") != 0) {
				}
				ship.setRequestHandler(null);
			}
			catch (IllegalAccessException e){
				System.out.println("Tentative de connexion d'un bateau inconnu (immatriculation : "+immatriculation+")");
				socketInput.close(); //ferme le socket associé
				socketOutput.close();
			}
			centralContext.removeRequestHandler(this);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read from client socket");
			e.printStackTrace();
		}
		
	}
}
