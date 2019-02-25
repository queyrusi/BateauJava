/**
 * 
 */
package client;

import java.io.*;
import java.net.*;
import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class Client extends Observable {
  

	private int numeroPort;

	private String nomServeur;
  
  	private String login;
  
 	public String typeConnexion;

	private Socket socketServeur;

	private PrintStream socOut;

	private BufferedReader socIn;	
	
	/** Un client se connecte a un serveur identifie par un nom (unNomServeur), sur un port (unNumero), et s'identifie par un login unLogin */
	public  Client(String unNomServeur, int unNumero, String unLogin) { 
		
		numeroPort = unNumero;
		nomServeur = unNomServeur;
    	login = unLogin;
    	typeConnexion = "@AbstractClient";
	} 

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
			socOut.println(typeConnexion);
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
	
	public void deconnecterDuServeur() {        
		try {
			System.out.println("[ClientTCP] CLIENT : " + socketServeur);
			socOut.close();
			socIn.close();
			socketServeur.close();
		} catch (Exception e) {
			System.err.println("Exception lors de la deconnexion :  " + e);
		}
	} 	
	
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

	/* A utiliser pour ne pas deleguer la connexion aux interfaces GUI */
	public String transmettreChaineConnexionPonctuelle(String uneChaine) {
		String msgServeur = null;
		String chaineRetour = "";
		System.out.println("\nClient connexionTransmettreChaine " + uneChaine);
		if (connecterAuServeur() == true) {
			try {
				socOut.println(uneChaine);
				socOut.flush();
				msgServeur = socIn.readLine();
				while( msgServeur != null && msgServeur.length() >0) {
					chaineRetour += msgServeur + "\n";
					msgServeur = socIn.readLine();
				}
				System.out.println("Client msgServeur " + chaineRetour);
				deconnecterDuServeur();
			} catch (Exception e) {
				System.err.println("Exception lors de la connexion client:  " + e);
			}
		}
		else
		{	
			System.err.println("Connexion echouee");
		}
		return chaineRetour;
	}

}
