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
 * <strong>Description : </strong>User story pour abonnement d'un bateau et d'un user puis connexion de ceux-ci au serveur.
 * @author C.Silva, R.Cuinat, S.Queyrut, P.Lledo
 */
public class MainUserStory1 {

	/**
	 * <strong>Description : </strong> Main pour abonnement d'un bateau et d'un user puis connexion de ceux-ci au serveur.
	 * @author C.Silva, R.Cuinat, S.Queyrut, P.Lledo
	 * @param args Paramètres du main
	 */
	public static void main(String[] args) {
		
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
		
		client.User newUser = new client.User(null, 6666, "toto1");
		SystemeEmbarque newSys =  new SystemeEmbarque(null, 6666, "tata");
		
		newUser.connecterAuServeur();
		newSys.connecterAuServeur();
		while (true) {}

	}

}
