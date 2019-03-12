/**
 * 
 */
package user_story;

import server.CentralContext;
import server.ConnectionProtocol;
import server.entities.Ship;
import server.patterns.tcp.ServeurTCP;
import client.User;
import client.SystemeEmbarque;

import java.util.concurrent.TimeUnit;

import client.*;

/**
 * <strong>Description : </strong>User story pour abonnement d'un bateau et d'un user puis connexion de ceux-ci au serveur.
 * Le bateau est en état Monitoring pendant 7 secondes, puis, au bout de 7 secondes, un capteur GPS alerte le système d'alarme
 * et le bateau passe en état Stolen. Après avoir que le serveur en ait pris connaissance, le bateau passe en Tracking et envoit
 * sa position toutes les secondes (ici 3.14 42.42).
 * @author C.Silva, R.Cuinat, S.Queyrut, P.Lledo
 */
public class MainUserStory3 {

	/**
	 * <strong>Description : </strong> Main la deuxième user story.
	 * @author C.Silva, R.Cuinat, S.Queyrut, P.Lledo
	 * @param args Param�tres du main
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		CentralContext context = new CentralContext();
		try {
			context.addUser(new server.entities.User("toto", "", null, null, null));
			context.addShip(new Ship("tata", "", "", ""));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		ConnectionProtocol protocol = new ConnectionProtocol();
		ServeurTCP serveur = new ServeurTCP(context, protocol, 6666);
		serveur.start();
	
		SystemeEmbarque newSys =  new SystemeEmbarque(null, 6666, "tata", "GPS"); // "LAPTOP-D7S9B5VD"
		((NoMonitoring) newSys.getNoMonitoringState()).start();

		((GPS) newSys.requestSensor("gps")).start();

		while (true) {}

	}

}
