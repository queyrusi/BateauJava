package server;

import server.patterns.tcp.ServeurTCP;

public class MainServeur {

	public static void main(String[] args) {
		CentralContext context = new CentralContext();
		ConnectionProtocol protocol = new ConnectionProtocol();
		ServeurTCP serveur = new ServeurTCP(6666);
		serveur.start();
	}
}
