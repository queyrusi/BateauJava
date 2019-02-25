package server.entities;

import server.patterns.tcp.RequestHandler;

public abstract class Client {
	
	private RequestHandler requestHandler;
	/**
	 * <strong>Description : </strong> Classe abstraite définissant la structure d'un client côté serveur.
	 * @author C.Silva, R.Cuinat
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * <strong>Description : </strong> Méthode permettant d'accéder au gestionnaire de requête instancié à destination des échanges avec un client connecté au serveur.
	 * @author C.Silva, R.Cuinat
	 * @return Un gestionnaire de requête.
	 */
	public RequestHandler getRequestHandler() {
		return requestHandler;
	}
	/**
	 * <strong>Description : </strong> Méthode permettant d'accéder au gestionnaire de requête instancié à destination des échanges avec un client connecté au serveur.
	 * @author C.Silva, R.Cuinat
	 * @param Un gestionnaire de requête.
	 */
	public void setRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}
}
