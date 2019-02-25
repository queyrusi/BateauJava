package server.patterns.tcp;
import java.io.IOException;
import java.net.Socket;

/**
	 * <strong>Description : </strong>Thread lanc� lors de la connexion d'un client.
	 * @author C.Silva, R.Cuinat
	 */
class ConnectionThread extends Thread {

	private Socket clientSocket;
	private ServeurTCP monServeurTCP;

	/**
	 * <strong>Description : </strong>Instancie le thread de connexion.
	 * @author C.Silva, R.Cuinat
	 * @param uneSocket socket de connexion au serveur
	 * @param unServeur Serveur � l'origine de l'execution du thread
	 */
	public  ConnectionThread(Socket uneSocket, ServeurTCP unServeur) {        
		super("ServeurThread");
		clientSocket = uneSocket;
		System.out.println("Connection sur : " + clientSocket);
		monServeurTCP = unServeur;
	} 
	
	/**
	 * <strong>Description : </strong>m�thode ex�cut�e lors du lancement du thread. Il ex�cute le protocole de connexion
	 * @author C.Silva, R.Cuinat
	 */
	public void run() {        
		try {
			monServeurTCP.getProtocole().execute( monServeurTCP.getContexte() , clientSocket.getInputStream() , clientSocket.getOutputStream() );
			System.out.println("Connexion effectu�e");
		} catch (IllegalArgumentException e) {
			System.out.println("Tentative de connexion d'un client non d�termin�");
		}
		catch (IOException e) {
			System.err.println("[Connexion] Exception : " + e );
			e.printStackTrace();
		}
	} 
}
