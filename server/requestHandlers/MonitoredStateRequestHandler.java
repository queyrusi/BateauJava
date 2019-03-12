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
	
	/**
	 * <strong>Description : </strong>Méthode exécutée au lancement du thread. Gère les requêtes d'un bateau en état monitored.
	 * @author C.Silva, R.Cuinat
	 */
	@Override
public void run() {
		try {
			String immatriculation = socketInput.readLine();
			try {
				//Changement d'état
				this.ship = centralContext.getShip(immatriculation);
				System.out.println("Le bateau est immatriculé : " + immatriculation);
				ship.setState(new MonitoredState(ship));
				ship.setRequestHandler(this);
				centralContext.addRequestHandler(this);
				//Ecoute
				String line;
				boolean running = true;
				while (running) {
					line = socketInput.readLine();
					if (line.compareTo("@quit") == 0) {
						running = false;
						this.socketInput.close();
						this.socketOutput.close();
						this.ship.setState(new NotMonitoredState(this.ship));
						this.ship.setRequestHandler(null);
					}
					else {
						String[] fields = line.split(" ");
						if (fields.length > 0) {
							switch (fields[0]) {
							case "@pos" :
								if (fields.length == 3) {
									try {
										this.ship.setPosition(new Position(Double.parseDouble(fields[1]),Double.parseDouble(fields[2])));
										this.ship.setDate(Calendar.getInstance());
									}
									catch (NumberFormatException e) {
										System.out.println("Message position invalide reçu du bateau " + this.ship.getImmatriculation() + " (msg = " + line + ")");
									}
								}
								else {
									System.out.println("Message position invalide reçu du bateau " + this.ship.getImmatriculation() + " (msg = " + line + ")");
								}
								break;
							case "@state" :
								if (fields.length == 2 && fields[1].compareTo("stolen") == 0) {
									socketOutput.write("@ack stolen");
									socketOutput.newLine();
									socketOutput.flush();
									this.ship.setState(new TrackingState(this.ship));
									running = false;
									TrackingStateRequestHandler TSRH = new TrackingStateRequestHandler(ship, this.centralContext, this.socketInput, socketOutput);
									centralContext.addRequestHandler(TSRH);
									TSRH.start();
								}
								else {
									System.out.println("Message de changement d'état invalide reçu du bateau " + this.ship.getImmatriculation() + " (msg = " + line + ")");
								}
								break;
							}
						}
						else { //message vide
							System.out.println("Message vide reçu du bateau " + this.ship.getImmatriculation());
						}
					}
				}
			}
			
			catch (IllegalAccessException e){ //Immatriculation inconnue
				System.out.println("Tentative de connexion d'un bateau inconnu (immatriculation : "+immatriculation+")");
				socketInput.close(); //ferme le socket associé
				socketOutput.close();
			}
		}
		catch (IOException e) { //Pb de connection
			// TODO Auto-generated catch block
			System.out.println("Unable to read from client socket");
			e.printStackTrace();
		}
		this.centralContext.removeRequestHandler(this);
		
	}
}
