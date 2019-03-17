/**
 * 
 */
package client;

import java.io.*;
import java.net.*;
import java.util.Observable;
import fr.ensta.fx.boatmonitoring.user.FXUserUI;


/**
 * <strong>Description : </strong> : Client générique duquel hérite les client {@code User} et {@code SystemeEmbarque}.
 * Intègre l'ensemble des methodes qui permettent la gestion de connexion et le transfert d'informations vers un serveur TCP générique.
 *
 * @author P. Lledo
 */

@SuppressWarnings("deprecation")
public abstract class Client extends Observable {
  

	private int numeroPort;

	private String nomServeur;
  
  	private String login;
  	
  	private String password;
  
 	private String typeConnexion;

	private Socket socketServeur;

	private PrintStream socOut;

	private BufferedReader socIn;	
	
	private FXUserUI ClientUI;
	
	/**
	 * <strong>Description : </strong> Classe {@code Client} de laquelle {@code User} et {@code SystemeEmbarque} devront heriter.
	 *  Un client se connecte a un serveur identifie par un nom ({@code unNomServeur}), sur un port ({@code unNumero}), 
	 *  et s'identifie par un login ({@code unLogin}). Cette version g�re les erreurs pour l'UI
	 *  
	 *  @param unNomServeur - hostname du serveur que le système embarqué cherche à joindre.
	 *  @param unNumero - numéro de port du serveur que le système embarqué cherche à joindre.
	 *  @param unLogin  - login d'accès au serveur.
	 *  @param unPassword - mot de passe associ� au login d'acc�s.
	 *  @param unUI - UI associ�e au client.
	 */
	public  Client(String unNomServeur, int unNumero, String unLogin, String unPassword, FXUserUI unUI) { 
		
		// TODO : vraie abstraction
		numeroPort = unNumero;
		nomServeur = unNomServeur;
    	login = unLogin;
    	password = unPassword;
    	ClientUI = unUI;
    	setTypeConnexion("@AbstractClient");
	} 
	
	/**
	 * <strong>Description : </strong> Classe {@code Client} de laquelle {@code User} et {@code SystemeEmbarque} devront heriter.
	 *  Un client se connecte a un serveur identifie par un nom ({@code unNomServeur}), sur un port ({@code unNumero}), 
	 *  et s'identifie par un login ({@code unLogin}). 
	 *  
	 *  @param unNomServeur - hostname du serveur que le système embarqué cherche à joindre.
	 *  @param unNumero - numéro de port du serveur que le système embarqué cherche à joindre.
	 *  @param unLogin  - login d'accès au serveur.
	 *  @param unPassword - mot de passe associ� au login d'acc�s.
	 */
	public  Client(String unNomServeur, int unNumero, String unLogin, String unPassword) { 
		
		// TODO : vraie abstraction
		numeroPort = unNumero;
		nomServeur = unNomServeur;
    	login = unLogin;
    	password = unPassword;
    	setTypeConnexion("@AbstractClient");
	} 
	
   /**
	* <strong>Description : </strong>Methode de connexion au serveur avec UI
	*
	* @return Renvoie true si la connexion a été éffectuée
	*/
	public boolean connecterAuServeurUI() { 
		
		boolean ok = false;
		String answer = null;
		String[] line;
		try {
			System.out.println("[+]Tentative : " + nomServeur + " -- " + numeroPort);
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
			socOut.println(password);
			answer = socIn.readLine();
			
		} catch (UnknownHostException e) {
			System.err.println("[+]Serveur inconnu : " + e);
			ok = false;

		} catch (ConnectException e) {
			System.err.println("[+]Exception lors de la connexion:" + e);
			e.printStackTrace();
			ok = false;

		} catch (IOException e) {
			System.err.println("[+]Exception lors de l'echange de donnees:" + e);
			ok = false;
			
		}
		if(!answer.isEmpty()) {
			line=answer.split(" ");
			switch(line[0]) {
			case "login" :
				ok=false;
				System.err.println("");
				ClientUI.displayWarning("Login", "Login incorrect");
				break;
			case "pass":
				ok=false;
				ClientUI.displayWarning("Login", "Password incorrect");
				break;
			case "success" :
				break;
			default :
				ok=false;
			}
			
		} else {
			ok=false;
		}
		
		if (ok) {
			ClientUI.getLogList().add("[+]connexion faite");
		}
		return ok;
		
	}
	
	   /**
		* <strong>Description : </strong>Methode de connexion au serveur sans UI pour les User stories pr�c�dentes
		*
		* @return Renvoie true si la connexion a été éffectuée
		*/
		public boolean connecterAuServeur() { 
			
			boolean ok = false;
			String answer = null;
			String[] line;
			try {
				System.out.println("[+]Tentative : " + nomServeur + " -- " + numeroPort);
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
				System.err.println("[+]Serveur inconnu : " + e);
				ok = false;

			} catch (ConnectException e) {
				System.err.println("[+]Exception lors de la connexion:" + e);
				e.printStackTrace();
				ok = false;

			} catch (IOException e) {
				System.err.println("[+]Exception lors de l'echange de donnees:" + e);
				ok = false;
				
			}
			if(!answer.isEmpty()) {
				line=answer.split(" ");
				switch(line[0]) {
				case "login" :
					ok=false;
					System.err.println("[+]Login : Login incorrect");
					break;
				case "pass":
					ok=false;
					System.err.println("[+]Login : Password incorrect");
					break;
				case "success" :
					break;
				default :
					ok=false;
				}
				
			} else {
				ok=false;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public FXUserUI getUI() {
		return ClientUI;
	}
	
	
   /**
	* <strong>Description : </strong>Methode de déconnexion au serveur
	*/
	public void deconnecterDuServeur() { 
		try {
			System.out.println("Deconnexion");
			socOut.println("@quit");
			socOut.flush();
			socOut.close();
			socIn.close();
			socketServeur.close();
		} catch (Exception e) {
			System.err.println("Exception lors de la deconnexion :  " + e);
		}
	} 	
	
	   /**
		* <strong>Description : </strong>Methode de déconnexion au serveur
		*/
		public void deconnecterDuServeurUI() { 
			try {
				ClientUI.getLogList().add("Deconnexion");
				socOut.println("@quit");
				socOut.flush();
				socOut.close();
				socIn.close();
				socketServeur.close();
			} catch (Exception e) {
				ClientUI.displayWarning("Exception lors de la deconnexion :  " , e.toString());
			}
		} 
	
	/**
	 * <strong>Description : </strong> Methode transmettant une chaine de caractères au serveur
	 *
	 * @param uneChaine Chaine à transmettre au serveur
	 *
	 *
	 */        
	public void transmettreChaine(String uneChaine) {
		String msgServeur = null;
		System.out.println( "Requete client : " + uneChaine );
		socOut.println( uneChaine );
		socOut.flush();
	} 

	/**
	 * @return the socOut
	 */
	public PrintStream getSocOut() {
		return socOut;
	}
	/**
	 * @param socOut the socOut to set
	 */
	public void setSocOut(PrintStream socOut) {
		this.socOut = socOut;
	}
	/**
	 * @return the socIn
	 */
	public BufferedReader getSocIn() {
		return socIn;
	}
	/**
	 * @param socIn the socIn to set
	 */
	public void setSocIn(BufferedReader socIn) {
		this.socIn = socIn;
	}
	public String getTypeConnexion() {
		return typeConnexion;
	}

	public void setTypeConnexion(String typeConnexion) {
		this.typeConnexion = typeConnexion;
	}

}
