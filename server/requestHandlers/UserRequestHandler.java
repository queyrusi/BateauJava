package server.requestHandlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import server.CentralContext;
import server.entities.Ship;
import server.entities.User;
import server.patterns.tcp.IContext;
import server.patterns.tcp.RequestHandler;
import server.shipStates.NotMonitoredState;

/**
 * <strong>Description : </strong>Thread gérant les requêtes d'un Utilisateur
 * @author C.Silva, R.Cuinat
 */
public class UserRequestHandler extends RequestHandler{

	private CentralContext centralContext;
	private BufferedReader socketInput;
	private BufferedWriter socketOutput;
	private User user;
	
	

	/**
	 * <strong>Description : </strong>Thread gérant les requêtes d'un Utilisateur
	 * @author C.Silva, R.Cuinat
	 * @param aContext contexte d'exécution du serveur
	 * @param socketIn socket pour la lecture
	 * @param socketOut socket pour l'écriture
	 */
	public UserRequestHandler(IContext aContext, BufferedReader socketIn, BufferedWriter socketOut) {
		// TODO Auto-generated constructor stub
		this.centralContext = (CentralContext) aContext;
		this.socketInput = socketIn;
		this.socketOutput = socketOut;
	}
	
	/**
	 * <strong>Description : </strong>Méthode exécutée au lancement du thread. Gère les requêtes d'un bateau en état Tracking.
	 * @author C.Silva, R.Cuinat
	 */
	@Override
	public void run(){
		try {
			centralContext.addRequestHandler(this);
			String id = socketInput.readLine();
			String password = socketInput.readLine();
			try {
				this.user = centralContext.getUser(id);
				if (this.user.getPassword() != password.hashCode()) {
					throw new IllegalArgumentException();
				}
				System.out.println("L'utilisateur est authentifié comme : " + id);
				this.user.setRequestHandler(this);
				this.user.setSocketInput(this.socketInput);
				this.user.setSocketOutput(this.socketOutput);
				this.user.setOnline(true);
				this.socketInput = null;
				this.socketOutput = null;
				this.user.getSocketOutput().write("success");
				this.user.getSocketOutput().newLine();
				this.user.getSocketOutput().flush();
				String line;
				while ((line = this.user.getSocketInput().readLine()).compareTo("@quit") != 0) {
					//System.out.println(this.user.getId() + " : " + line);
					String[] fields = line.split(" ");
					if (fields.length > 0) {
						switch (fields[0]) {
						case "@req" :
							Ship ship = this.user.getBateau();
							if (ship == null || ship.getState().getClass() == NotMonitoredState.class) {
								this.user.getSocketOutput().write("@req none");
							}
							else {
								this.user.getBateau().getSocketOutput().write(line);
							}
							break;
						case "@get" :
							String GottenLine = this.user.getAllInfo();
							this.user.getSocketOutput().write(GottenLine);
							this.user.getSocketOutput().newLine();
							this.user.getSocketOutput().flush();
							break;
						case "@set" :
							for (int i = 1; i+1 < fields.length;i = i + 2) {
								switch (fields[i]) {
									case "pass" :
										this.user.setPassword(fields[i+1]);
									case "nom" :
										this.user.setNom(fields[i+1].replaceAll("_", " "));
										break;
									case "adresse" :
										this.user.setAdresse(fields[i+1].replaceAll("_", " "));
										break;
									case "email" :
										if (fields[i+1].contains("/") == true) {
											String[] emails = fields[i+1].split("/");
											this.user.remove_email(emails[0]);
											if (emails.length == 2) {
												this.user.add_email(emails[1]);
											}
										}
										else { //Ajout
											this.user.add_email(fields[i+1]);
										}
										break;
									case "bateau_nom" :
										if (this.user.getBateau() != null) {
											this.user.getBateau().setNom(fields[i+1].replaceAll("_", " "));
										}
										break;
									case "bateau_lieu" : //lieu de stationnement
										if (this.user.getBateau() != null) {
											this.user.getBateau().setEndroit_stationnement(fields[i+1].replaceAll("_", " "));
										}
										break;
								}
							}
							this.user.getSocketOutput().write(this.user.getAllInfo());
							this.user.getSocketOutput().newLine();
							this.user.getSocketOutput().flush();
							break;
						case "@log" :
							this.user.getSocketOutput().write(this.user.getBateau().flushPosLog());
							this.user.getSocketOutput().newLine();
							this.user.getSocketOutput().flush();
							break;
						}
					}					
				}
				this.endOfThread();
			}
			catch (IllegalAccessException e){
				System.out.println("Tentative de connexion d'un utilisateur inconnu (identifiant : "+id+")");
				socketInput.close(); //ferme le socket associé
				socketOutput.close();
				socketInput = null;
				socketOutput = null;
			}
			catch (IllegalArgumentException e) {
				System.out.println("Tentative de connexion au utilisateur (login : "+ id + ") avec un mot passe eronné ( mdp : " + password + " )");
				socketOutput.write("pass");
				socketOutput.newLine();
				socketOutput.flush();
				socketInput.close(); //ferme le socket associé
				socketOutput.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read from client socket");
			e.printStackTrace();
		}
		centralContext.removeRequestHandler(this);
		
	}
	
	/**
	 * <strong>Description : </strong>Méthode exécutée au lors de la déconnexion du User.
	 * @author C.Silva, R.Cuinat
	 * @throws IOException Lorsqu'il y a une erreur lors de la fermeture des sockets
	 */
	public void endOfThread() throws IOException {
		this.user.setRequestHandler(null);
		this.user.setOnline(false);
		this.user.getSocketInput().close();
		this.user.getSocketOutput().close();
		this.user.setSocketInput(null);
		this.user.setSocketOutput(null);
		centralContext.removeRequestHandler(this);
		System.out.println("Déconnexion du user " + this.user.getId());
	}
}
