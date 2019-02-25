package server.requestHandlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import server.CentralContext;
import server.entities.User;
import server.patterns.tcp.IContext;
import server.patterns.tcp.RequestHandler;

/**
 * <strong>Description : </strong>Thread g�rant les requ�tes d'un Utilisateur
 * @author C.Silva, R.Cuinat
 */
public class UserRequestHandler extends RequestHandler {

	private CentralContext centralContext;
	private BufferedReader socketInput;
	private BufferedWriter socketOutput;
	private User user;
	

	/**
	 * <strong>Description : </strong>Thread g�rant les requ�tes d'un Utilisateur
	 * @author C.Silva, R.Cuinat
	 * @param aContext contexte d'ex�cution du serveur
	 * @param socketIn socket pour la lecture
	 * @param socketOut socket pour l'�criture
	 */
	public UserRequestHandler(IContext aContext, BufferedReader socketIn, BufferedWriter socketOut) {
		// TODO Auto-generated constructor stub
		this.centralContext = (CentralContext) aContext;
		this.socketInput = socketIn;
		this.socketOutput = socketOut;
	}
	
	/**
	 * <strong>Description : </strong>M�thode ex�cut�e au lancement du thread. G�re les requ�tes d'un bateau en �tat Tracking.
	 * @author C.Silva, R.Cuinat
	 */
	@Override
	public void run(){
		try {
			centralContext.addRequestHandler(this);
			String id = socketInput.readLine();
			try {
				this.user = centralContext.getUser(id);
				System.out.println("L'utilisateur est authentifi� comme : " + id);
				this.user.setRequestHandler(this);
				String line;
				while ((line = socketInput.readLine()).compareTo("@quit") != 0) {
					
				}
				this.user.setRequestHandler(null);
				socketInput.close();
				socketOutput.close();
			}
			catch (IllegalAccessException e){
				System.out.println("Tentative de connexion d'un utilisateur inconnu (identifiant : "+id+")");
				socketInput.close(); //ferme le socket associ�
				socketOutput.close();
			}
			centralContext.removeRequestHandler(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read from client socket");
			e.printStackTrace();
		}
		
	}

}
