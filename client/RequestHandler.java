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
	public RequestHandler(BufferedReader newsocIn, SystemeEmbarque newSystemeEmbarque) {
		
		listeningSystemeEmbarque = newSystemeEmbarque;
		socIn = newsocIn;
		
	}

	@Override
	public void run() {
		String[] receivedLine;
		String value;
		int sensorNumbers;
		// TODO Auto-generated method stub
		while (this.listeningSystemeEmbarque.handling) {
			
			// switch sur l'état actuel du système embarqué :
			switch(this.listeningSystemeEmbarque.etatDuSystemeEmbarque.getStateLabel()) { 

			  case "Stolen":
				  
				  try {
					if (this.socIn.readLine().equals("@ack")) {
						  
						// on a reçu un @ack du serveur, on est prêts à  passer en Tracking :
						listeningSystemeEmbarque.changerEtat(listeningSystemeEmbarque.getTrackingState());
						
					  }
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			    
			    break;
			    
			  case "Monitoring":
				
				  try {
					receivedLine=this.socIn.readLine().split(" ");
					sensorNumbers=receivedLine.length()-1;
					if (receivedLine[0].equals("@req")) {
						for(int i=1;i<=sensorNumber;i++){
						  	value+=this.listeningSystemeEmbarque.requestSensor(receivedLine[i]).getCapteurValueString();
							value+=" ";
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
