package server;

import server.entities.Position;
import server.entities.Ship;
import server.entities.User;
import server.patterns.tcp.ServeurTCP;

public class MainServeur {

	public static void main(String[] args) {
		CentralContext context = new CentralContext();
		try {
			Ship bateau = new Ship("immat", "password", "modele", "type", "station", new Position(-117.6, 0));
			context.addUser(new User("toto","0000", "mon adresse", null, null, bateau));
			context.addShip(bateau);
			context.addShip(new Ship("tata","password","a","b","c"));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionProtocol protocol = new ConnectionProtocol();
		ServeurTCP serveur = new ServeurTCP(context, protocol, 6666);
		serveur.start();
	}
}
