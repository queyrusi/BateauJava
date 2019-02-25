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

/**
 * @author chenqun
 *
 */
public class MainUserStory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CentralContext context = new CentralContext();
		try {
			context.addUser("toto", new server.entities.User("toto", "", "", null, null));
			context.addShip("tata", new Ship("tata", "", "", ""));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		ConnectionProtocol protocol = new ConnectionProtocol();
		ServeurTCP serveur = new ServeurTCP(context, protocol, 6666);
		serveur.start();
		
		client.User newUser = new client.User(null, 6666, "toto");
		SystemeEmbarque newSys =  new SystemeEmbarque(null, 6666, "tata");
		
		newUser.connecterAuServeur();
		newSys.connecterAuServeur();
		while (true) {}

	}

}
