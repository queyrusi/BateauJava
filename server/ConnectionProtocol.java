package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import server.patterns.tcp.IContext;
import server.patterns.tcp.IProtocole;
import server.requestHandlers.MonitoredStateRequestHandler;
import server.requestHandlers.UserRequestHandler;

/**
 * <strong>Description : </strong>Classe implémentant IProtocole. Le thread se charge d'écouter le socket pour valider la connexion d'un utilisateur ou d'un bateau. 
 * Il lance le gestionnaire associé.
 * @author C.Silva, R.Cuinat
 * 
 */
public class ConnectionProtocol implements IProtocole {
	/**
	 * <strong>Description : </strong>Classe implémentant IProtocole. Le thread se charge d'écouter le socket pour valider la connexion d'un utilisateur ou d'un bateau. 
	 * Il lance le gestionnaire associé.
	 * @author C.Silva, R.Cuinat
	 * 
	 */
	public ConnectionProtocol() {
	}
	
	/**
	 * <strong>Description : </strong>Lis la première ligne du InputStream et vérifie la validité du code ('@User' ou '@Ship'). Lance le gestionnaire associé
	 * @author C.Silva, R.Cuinat
	 * @param aContext Contexte du serveur. Implémente l'interface IContext
	 * @param anInputStream Stream d'entrée de la connexion
	 * @param anOutputStream Stream de sortie de la connexion
	 */
	@Override
	public final void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		BufferedReader socketIn = new BufferedReader(new InputStreamReader(anInputStream));
		BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(anOutputStream));
		try {
			String connectionCode = socketIn.readLine();
			switch (connectionCode) {
			case "@Ship" :
				MonitoredStateRequestHandler MSt = new MonitoredStateRequestHandler(aContext, socketIn, socketOut);
				System.out.println("Connexion d'un Bateau");
				MSt.start();
				break;
			case "@User" :
				UserRequestHandler URt = new UserRequestHandler(aContext, socketIn, socketOut);
				System.out.println("Connexion d'un Utilisateur");
				URt.start();
				break;
			default :
				throw new IllegalArgumentException();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read from client socket");
			e.printStackTrace();
		}

	}

}
