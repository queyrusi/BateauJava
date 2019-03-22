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
 * <strong>Description : </strong>Thread g�rant les requ�tes d'un bateau en �tat monitored
 * @author C.Silva, R.Cuinat
 */
public class MonitoredStateRequestHandler extends ShipStateRequestHandler {
	
	private CentralContext centralContext;
	private BufferedReader socketInput;
	private BufferedWriter socketOutput;
	private Ship ship;
	private boolean running;

	/**
	 * <strong>Description : </strong>Thread g�rant les requ�tes d'un bateau en �tat monitored
	 * @author C.Silva, R.Cuinat
	 * @param aContext contexte d'ex�cution du serveur
	 * @param socketIn socket pour la lecture
	 * @param socketOut socket pour l'�criture
	 */
	public MonitoredStateRequestHandler(IContext aContext, BufferedReader socketIn, BufferedWriter socketOut) {
		this.centralContext = (CentralContext) aContext;
		this.socketInput = socketIn;
		this.socketOutput = socketOut;
		this.running = true;
	}
	
	/**
	 * <strong>Description : </strong>M�thode ex�cut�e au lancement du thread. G�re les requ�tes d'un bateau en �tat monitored.
	 * @author C.Silva, R.Cuinat
	 */
	@Override
public void run() {
		try {
			String immatriculation = socketInput.readLine();
			String password = socketInput.readLine();
			try {
				//Changement d'�tat
				this.ship = centralContext.getShip(immatriculation);
				if (this.ship.getPassword() != password.hashCode()) {
					throw new IllegalArgumentException();
				}
				System.out.println("Le bateau est immatricul� : " + immatriculation);
				ship.setState(new MonitoredState(ship));
				ship.setRequestHandler(this);
				ship.setSocketInput(this.socketInput);
				ship.setSocketOutput(this.socketOutput);
				this.socketInput = null;
				this.socketOutput = null;
				centralContext.addRequestHandler(this);
				this.ship.getSocketOutput().write("success");
				this.ship.getSocketOutput().newLine();
				this.ship.getSocketOutput().flush();
				//Ecoute
				String line;
				while (running) {
					line = this.ship.getSocketInput().readLine();
					if (line.compareTo("@quit") == 0) {
						this.quitMessage();
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
										System.out.println("Message position invalide re�u du bateau " + this.ship.getImmatriculation() + " (msg = " + line + ")");
									}
								}
								else {
									System.out.println("Message position invalide re�u du bateau " + this.ship.getImmatriculation() + " (msg = " + line + ")");
								}
								break;
							case "@state" :
								if (fields.length == 2 && fields[1].compareTo("stolen") == 0) {
									this.ship.getSocketOutput().write("@ack stolen");
									this.ship.getSocketOutput().newLine();
									this.ship.getSocketOutput().flush();
									this.ship.setState(new TrackingState(this.ship));
									running = false;
									TrackingStateRequestHandler TSRH = new TrackingStateRequestHandler(ship, this.centralContext);
									centralContext.addRequestHandler(TSRH);
									TSRH.start();
								}
								else {
									System.out.println("Message de changement d'�tat invalide re�u du bateau " + this.ship.getImmatriculation() + " (msg = " + line + ")");
								}
								break;
							case "@req" :
								this.ship.getUser().getSocketOutput().write("line");
								this.ship.getUser().getSocketOutput().flush();
								break;
							}
						}
						else { //message vide
							System.out.println("Message vide re�u du bateau " + this.ship.getImmatriculation());
						}
					}
				}
			}
			
			catch (IllegalAccessException e){ //Immatriculation inconnue
				System.out.println("Tentative de connexion d'un bateau inconnu (immatriculation : "+immatriculation+")");
				socketOutput.write("login");
				socketOutput.newLine();
				socketOutput.flush();
				socketInput.close(); //ferme le socket associ�
				socketOutput.close();
			}
			
			catch (IllegalArgumentException e) { //Mot de passe �ronn�
				System.out.println("Tentative de connexion au bateau immatricul� "+ immatriculation + " avec un mot passe eronn� ( mdp : " + password + " )");
				socketOutput.write("pass");
				socketOutput.newLine();
				socketOutput.flush();
				socketInput.close(); //ferme le socket associ�
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
	
	public void quitMessage() throws IOException {
		this.running = false;
		this.ship.getSocketInput().close();
		this.ship.getSocketOutput().close();
		this.ship.setState(new NotMonitoredState(this.ship));
		this.ship.setRequestHandler(null);
	}
}
