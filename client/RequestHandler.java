/**
 * 
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

// ==================
// TODO 9/3/19 10h33
// ==================

/**<strong>Description : </strong>Classe gérant les requêtes et réponses du serveur
 * 
 * @author S. Queyrut P. Lledo
 *
 */
public class RequestHandler implements Runnable {
	
	SystemeEmbarque listeningSystemeEmbarque;
	
	BufferedReader socIn;
	

	/**<strong>Description : </strong>Constructeur de la classe RequestHandler
	 * Le request handler doit être instancié par un système embarqué pour fonctionner
	 * <strong>Exemple : </strong>RequestHandler(this)
	 * 
	 * @param newSystemeEmbarque Systeme embarqué sur lequel le requestHandler doit écouter
	 *
	 * @author S. Queyrut P. Lledo
	 */
	public RequestHandler(SystemeEmbarque newSystemeEmbarque) {
		
		listeningSystemeEmbarque = newSystemeEmbarque;
		socIn = newSystemeEmbarque.getSocIn();
		
	}
	/**<strong>Description : </strong>Surcharge de la methode run de runnable permettant de l'éxecuter
	 * dans un Thread. Sa fonction est de recevoir et traiter les messages provenant du serveur tant que
	 * cela est nécessaire
	 * 
	 * @author S. Queyrut P. Lledo
	 */
	@Override
	public void run() {
		
		String[] receivedLine;
		
		String value = null;
		
		String maLigne = null;
		
		int sensorNumbers;

		while (this.listeningSystemeEmbarque.handling) {
			
			try {
				maLigne = this.listeningSystemeEmbarque.getSocIn().readLine();
				if (maLigne != null) {
					receivedLine = maLigne.split(" ");
				
				// switch sur l'Ã©tat actuel du systÃ¨me embarquÃ© :
				switch(receivedLine[0]) { 

				  case "@ack":
					  
					if (receivedLine[1].equals("stolen")) {
						  listeningSystemeEmbarque.changerEtat(listeningSystemeEmbarque.getTrackingState());
					  }
					
					break;
				    
				  case "@req":
					
					sensorNumbers = receivedLine.length-1;
					
					// cas "all" : on demande tous les capteurs :
					if (receivedLine[1].equals("all")) {
						
						
						// on parcourt la liste de tous les composants du systÃ¨me embarquÃ©
						for (CapteurComposant capteur : ((CapteurGroupe)listeningSystemeEmbarque.capteurList).getcapteurComposants()) {
							
							value += capteur.getCapteurLabel() + " ";
							value += capteur.getCapteurValueString() + " ";
							
							}
					}
								
					// cas non-"all" ; requete capteur par capteur :
					else {
						for (int i = 1; i <= sensorNumbers; i++) { 
							
							// valeur de chacun des capteurs demandÃ© :
						  	value += this.listeningSystemeEmbarque.requestSensor(receivedLine[i]).getCapteurValueString();  
							value += " ";
						}
					}
					this.listeningSystemeEmbarque.transmettreChaine(value);
					
					break;	
					} 
				}
			} catch (SocketException e) {
				System.out.println("Fermeture du socket");
				this.listeningSystemeEmbarque.handling = false;
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   				  
			    
		}
		System.out.println("Fin du thread RequestHandler Systeme embarqué.");
	}
	}
