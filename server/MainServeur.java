package server;

import server.entities.Ship;
import server.entities.User;
import server.patterns.tcp.ServeurTCP;

public class MainServeur {

	public static void main(String[] args) {
		CentralContext context = new CentralContext();
		try {
			context.addUser(new User("toto", "", null, null, null));
			context.addShip(new Ship("tata", "", "", ""));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionProtocol protocol = new ConnectionProtocol();
		ServeurTCP serveur = new ServeurTCP(context, protocol, 6666);
		serveur.start();
	}
}
