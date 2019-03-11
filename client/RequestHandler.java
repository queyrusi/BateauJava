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
			
			// switch sur l'état actuel du système embarqué :
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
				
				break;	
				}  
			    				  
			    
			}
	}
