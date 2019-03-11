/**
 * 
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;

// ==================
// TODO 9/3/19 10h33
// ==================

/**
 * @author chenqun
 *
 */
public class RequestHandler implements Runnable {
	
	SystemeEmbarque listeningSystemeEmbarque;
	
	BufferedReader socIn;
	

	/**
	 * 
	 */
	public RequestHandler(SystemeEmbarque newSystemeEmbarque) {
		
		listeningSystemeEmbarque = newSystemeEmbarque;
		socIn = newSystemeEmbarque.getSocIn();
		
	}

	@Override
	public void run() {
		
		String[] receivedLine;
		
		String value = null;
		
		int sensorNumbers;
		
		while (this.listeningSystemeEmbarque.handling) {
			
			// switch sur l'état actuel du système embarqué :
			switch(this.listeningSystemeEmbarque.currentState.getStateLabel()) { 

			  case "Stolen":
				  
				  try {
					  System.out.println(socIn.readLine());
					if (this.socIn.readLine().equals("@ack stolen")) {
						  
						// on a reçu un @ack du serveur, on est prêts à  passer en Tracking :
						listeningSystemeEmbarque.changerEtat(listeningSystemeEmbarque.getTrackingState());
						
					  }
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			    
			    break;
			    
			  case "Monitoring":
				
				  try {
					receivedLine = this.socIn.readLine().split(" ");  // split la ligne pour savoir si c'est une requete...
					
					sensorNumbers=receivedLine.length-1;
					
					if (receivedLine[0].equals("@req")) {  // si c'est une requete :
						
						// cas "all" : on demande tous les capteurs :
						if (receivedLine[1].equals("all")) {
							
							// on parcourt la liste de tous les composants du système embarqué
							for (CapteurComposant capteur : ((CapteurGroupe)listeningSystemeEmbarque.capteurList).getcapteurComposants()) {
								
								value += capteur.getCapteurLabel() + " ";
								value += capteur.getCapteurValueString() + " ";
								
								}
						}
						
						// cas non-"all" ; requete capteur par capteur :
						else {
							for (int i = 1; i <= sensorNumbers; i++) { 
								
								// valeur de chacun des capteurs demandé :
							  	value += this.listeningSystemeEmbarque.requestSensor(receivedLine[i]).getCapteurValueString();  
								value += " ";
							}
						}
						this.listeningSystemeEmbarque.transmettreChaine(value);
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			    
			    break;
			    
			  default:
				  
				  break;
				  
			    
			}
		}
	}

}
