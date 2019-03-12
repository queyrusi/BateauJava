/**
 * 
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

// ==================
// TODO 9/3/19 10h33
// ==================

/**<strong>Description : </strong>Classe g�rant les requ�tes et r�ponses du serveur
 * 
 * @author S. Queyrut P. Lledo
 *
 */
public class RequestHandler implements Runnable {
	
	SystemeEmbarque listeningSystemeEmbarque;
	
	BufferedReader socIn;
	

	/**<strong>Description : </strong>Constructeur de la classe RequestHandler
	 * Le request handler doit �tre instanci� par un syst�me embarqu� pour fonctionner
	 * <strong>Exemple : </strong>RequestHandler(this)
	 * 
	 * @author S. Queyrut P. Lledo
	 */
	public RequestHandler(SystemeEmbarque newSystemeEmbarque) {
		
		listeningSystemeEmbarque = newSystemeEmbarque;
		socIn = newSystemeEmbarque.getSocIn();
		
	}
	/**<strong>Description : </strong>Surcharge de la methode run de runnable permettant de l'�xecuter
	 * dans un Thread. Sa fonction est de recevoir et traiter les messages provenant du serveur tant que
	 * cela est n�cessaire
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
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			receivedLine = maLigne.split(" ");
			
			// switch sur le message du serveur :
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
					
					
					// on parcourt la liste de tous les composants du syst�me embarqu�
					for (CapteurComposant capteur : ((CapteurGroupe)listeningSystemeEmbarque.capteurList).getcapteurComposants()) {
						
						value += capteur.getCapteurLabel() + " ";
						value += capteur.getCapteurValueString() + " ";
						
						}
				}
							
				// cas non-"all" ; requete capteur par capteur :
				else {
					for (int i = 1; i <= sensorNumbers; i++) { 
						
						// valeur de chacun des capteurs demand� :
					  	value += this.listeningSystemeEmbarque.requestSensor(receivedLine[i]).getCapteurValueString();  
						value += " ";
					}
				}
				this.listeningSystemeEmbarque.transmettreChaine(value);
						}
				
				break;	
				}  
			    				  
			    
			}
	}