package server.patterns.tcp;
import java.io.IOException;
import java.net.Socket;

/**
 * Processus de Transaction (anciennement ServeurSpecifique)
 */
class ConnectionThread extends Thread {

	private Socket clientSocket;
	private ServeurTCP monServeurTCP;

	public  ConnectionThread(Socket uneSocket, ServeurTCP unServeur) {        
		super("ServeurThread");
		clientSocket = uneSocket;
		System.out.println("Connection sur : " + clientSocket);
		monServeurTCP = unServeur;
	} 

	public void run() {        
		try {
			monServeurTCP.getProtocole().execute( monServeurTCP.getContexte() , clientSocket.getInputStream() , clientSocket.getOutputStream() );
			System.out.println("Connexion effectuée");
		} catch (IllegalArgumentException e) {
			System.out.println("Tentative de connexion d'un client non déterminé");
		}
		catch (IOException e) {
			System.err.println("[Connexion] Exception : " + e );
			e.printStackTrace();
		}
	} 
}
