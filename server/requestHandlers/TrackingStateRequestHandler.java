package server.requestHandlers;

import java.io.IOException;
import java.util.Calendar;

import server.CentralContext;
import server.entities.Position;
import server.entities.Ship;
import server.patterns.tcp.IContext;
import server.patterns.tcp.ShipStateRequestHandler;
import server.shipStates.NotMonitoredState;
import server.shipStates.TrackingState;

/**
 * <strong>Description : </strong>Thread gérant les requêtes d'un bateau en état Not monitored
 * @author C.Silva, R.Cuinat
 */
public class TrackingStateRequestHandler extends ShipStateRequestHandler {
	private CentralContext centralContext;
	private Ship ship;

	/**
	 * <strong>Description : </strong>Constructeur du thread gérant les requêtes d'un bateau en état Not monitored
	 * @author C.Silva, R.Cuinat
	 * @param ship un objet bateau
	 * @param aContext contexte d'exécution du serveur
	 */
	public TrackingStateRequestHandler(Ship ship,IContext aContext) {
		// TODO Auto-generated constructor stub
		this.centralContext = (CentralContext) aContext;
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
				if (this.ship.getRequestHandler()!=null) {
				this.ship.getRequestHandler().join();}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ship.setRequestHandler(this);
			this.ship.setState(new TrackingState(ship));
			this.centralContext.addRequestHandler(this);
			String line;
			while ((line = this.ship.getSocketInput().readLine()).compareTo("@quit") != 0) {
				String[] message = line.split(" ");
				if (message.length > 0) {
					switch (message[0]) {
					case "@pos" :
							if (message.length == 3) {
								try {
									Position pos = new Position(Double.parseDouble(message[1]),Double.parseDouble(message[2]));
									this.ship.setPosition(pos);
									this.ship.setDate(Calendar.getInstance());
									(this.ship.getLogger()).writeLog(this.ship.getDate().getTime().toString()+" : "+pos.toString());
									this.ship.addPosLog(this.ship.getDate(),pos);
								}
								catch (NumberFormatException e) {
									System.out.println("Message position invalide reçu du bateau " + this.ship.getImmatriculation() + " (msg = " + line + ")");
								}
							}
							else {
								System.out.println("Message position invalide reçu du bateau " + this.ship.getImmatriculation() + " (msg = " + line + ")");
							}
							break;
					case "@req" :
						this.ship.getUser().getSocketOutput().write("line");
						this.ship.getUser().getSocketOutput().flush();
						break;
					}
				}
				else System.out.println("Le bateau "+ship.getImmatriculation()+" a envoyé un message vide.");
				}
			this.centralContext.removeRequestHandler(this);
			this.ship.getSocketInput().close();
			this.ship.getSocketOutput().close();
			this.ship.setRequestHandler(null);
			this.ship.setState(new NotMonitoredState(ship));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read from client socket");
			e.printStackTrace();
		}
	}
}
