package server.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import server.patterns.tcp.RequestHandler;

public abstract class Client {
	
	private BufferedReader socketInput;
	private BufferedWriter socketOutput;
	private RequestHandler requestHandler;
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance socketInput
	 * @author C.Silva, R.Cuinat
	 * @return socketInput
	 */
	public final synchronized BufferedReader getSocketInput() {
		return socketInput;
	}
	
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance socketInput
	 * @author C.Silva, R.Cuinat
	 * @param socketInput socketInput
	 */
	public final synchronized void setSocketInput(BufferedReader socketInput) {
		this.socketInput = socketInput;
	}
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance socketOutput
	 * @author C.Silva, R.Cuinat
	 * @return socketOutput
	 */
	public final synchronized BufferedWriter getSocketOutput() {
		return socketOutput;
	}
	
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance socketOutput
	 * @author C.Silva, R.Cuinat
	 * @param socketOutput socketOutput
	 */
	public final synchronized void setSocketOutput(BufferedWriter socketOutput) {
		this.socketOutput = socketOutput;
	}
	
	/**
	 * <strong>Description : </strong> Constructeur de la classe abstraite d�finissant la structure d'un client c�t� serveur.
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
	public final synchronized RequestHandler getRequestHandler() {
		return requestHandler;
	}
	/**
	 * <strong>Description : </strong> M�thode permettant d'acc�der au gestionnaire de requ�te instanci� � destination des �changes avec un client connect� au serveur.
	 * @author C.Silva, R.Cuinat
	 * @param requestHandler Un gestionnaire de requ�te.
	 */
	public final synchronized void setRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}
	
	
}
