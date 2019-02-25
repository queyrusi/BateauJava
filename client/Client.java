/**
 * 
 */
package client;

import java.io.*;
import java.net.*;
import java.util.Observable;


/**
 * Client générique duquel hérite les client User et SystemeEmbarque.
 * 
 * Intègre l'ensemble des methodes qui permettent la gestion de connexion et le transfert d'informations vers un serveur TCP générique.
 *
 *
 *
 */

@SuppressWarnings("deprecation")
public abstract class Client extends Observable {
  

	private int numeroPort;

	private String nomServeur;
  
  	private String login;
  
 	private String typeConnexion;

	private Socket socketServeur;

	private PrintStream socOut;

	private BufferedReader socIn;	
	
	/** Un client se connecte a un serveur identifie par un nom (unNomServeur), sur un port (unNumero), et s'identifie par un login unLogin */
	public  Client(String unNomServeur, int unNumero, String unLogin) { 
		
		// TODO : vraie abstraction
		numeroPort = unNumero;
		nomServeur = unNomServeur;
    	login = unLogin;
    	setTypeConnexion("@AbstractClient");
	} 
	/**
	* Methode de connexion au serveur
	*
	* @return Renvoie true si la connexion a été éffectuée
	*/
	public boolean connecterAuServeur() { 
		
		boolean ok = false;
		try {
			System.out.println("Tentative : " + nomServeur + " -- " + numeroPort);
			socketServeur = new Socket( nomServeur, numeroPort);
			System.out.println("[+]socket serveur ok");
			socOut = new PrintStream(socketServeur.getOutputStream());
			System.out.println("[+]socket out ok");
			socIn = new BufferedReader ( 
					new InputStreamReader (socketServeur.getInputStream()));
			System.out.println("[+]socket in ok");
			ok = true;
			
			// initialisation de la connexion :
			socOut.println(getTypeConnexion());
			socOut.println(login);
			
		} catch (UnknownHostException e) {
			System.err.println("Serveur inconnu : " + e);
			ok = false;

		} catch (ConnectException e) {
			System.err.println("Exception lors de la connexion:" + e);
			e.printStackTrace();
			ok = false;

		} catch (IOException e) {
			System.err.println("Exception lors de l'echange de donnees:" + e);
			ok = false;
			
		}
		
		if (ok) {
			System.out.println("[+]connexion faite");
		}
		return ok;
		
	}
	
	
	public int getNumeroPort() {
		return numeroPort;
	}

	public void setNumeroPort(int numeroPort) {
		this.numeroPort = numeroPort;
	}

	public String getNomServeur() {
		return nomServeur;
	}

	public void setNomServeur(String nomServeur) {
		this.nomServeur = nomServeur;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	
	/**
	* Methode de déconnexion au serveur
	*/
	public void deconnecterDuServeur() { 
		try {
			System.out.println("[ClientTCP] CLIENT : " + socketServeur);
			socOut.println("@quit");
			socOut.close();
			socIn.close();
			socketServeur.close();
		} catch (Exception e) {
			System.err.println("Exception lors de la deconnexion :  " + e);
		}
	} 	
	
	/**
	 * Methode transmettant une chaine de caractères au serveur
	 *
	 * @param uneChaine Chaine à transmettre au serveur
	 *
	 * @return Message de réponse du serveur à la requête du Client
	 */        
	public String transmettreChaine(String uneChaine) {
		String msgServeur = null;
		try {
			System.out.println( "Requete client : " + uneChaine );
			socOut.println( uneChaine );
			socOut.flush();
			msgServeur = socIn.readLine();
			System.out.println( "Reponse serveur : " + msgServeur );

		} catch (UnknownHostException e) {
			System.err.println("Serveur inconnu : " + e);
		} catch (IOException e) {
			System.err.println("Exception entree/sortie:  " + e);
			e.printStackTrace();
		}
		return msgServeur;
	} 

	public String getTypeConnexion() {
		return typeConnexion;
	}

	public void setTypeConnexion(String typeConnexion) {
		this.typeConnexion = typeConnexion;
	}

}
