package server.entities;

import server.patterns.tcp.RequestHandler;

public abstract class Client {
	
	private RequestHandler requestHandler;
	/**
	 * <strong>Description : </strong> Classe abstraite d�finissant la structure d'un client c�t� serveur.
	 * @author C.Silva, R.Cuinat
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * <strong>Description : </strong> M�thode permettant d'acc�der au gestionnaire de requ�te instanci� � destination des �changes avec un client connect� au serveur.
	 * @author C.Silva, R.Cuinat
	 * @return Un gestionnaire de requ�te.
	 */
	public RequestHandler getRequestHandler() {
		return requestHandler;
	}
	/**
	 * <strong>Description : </strong> M�thode permettant d'acc�der au gestionnaire de requ�te instanci� � destination des �changes avec un client connect� au serveur.
	 * @author C.Silva, R.Cuinat
	 * @param Un gestionnaire de requ�te.
	 */
	public void setRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}
}
