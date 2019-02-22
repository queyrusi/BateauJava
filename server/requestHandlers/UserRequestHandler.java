package server.requestHandlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import server.CentralContext;
import server.entities.User;
import server.patterns.tcp.IContext;
import server.patterns.tcp.RequestHandler;

public class UserRequestHandler extends RequestHandler {

	private CentralContext centralContext;
	private BufferedReader socketInput;
	private BufferedWriter socketOutput;
	private User user;

	public UserRequestHandler(IContext aContext, BufferedReader socketIn, BufferedWriter socketOut) {
		// TODO Auto-generated constructor stub
		this.centralContext = (CentralContext) aContext;
		this.socketInput = socketIn;
		this.socketOutput = socketOut;
	}
	
	@Override
	public void run(){
		try {
			centralContext.addRequestHandler(this);
			String id = socketInput.readLine();
			try {
				this.user = centralContext.getUser(id);
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
				socketInput.close(); //ferme le socket associé
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
