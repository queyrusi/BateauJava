/**
 * 
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;

// ==================
// TODO 7/3/19 09h05
// ==================

/**
 * @author chenqun
 *
 */
public class ServerListener implements Runnable {
	
	SystemeEmbarque listeningSystemeEmbarque;
	
	BufferedReader socIn;

	/**
	 * 
	 */
	public ServerListener(BufferedReader newsocIn, SystemeEmbarque newSystemeEmbarque) {
		
		listeningSystemeEmbarque = newSystemeEmbarque;
		socIn = newsocIn;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			
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
					if (this.socIn.readLine().equals("@req")) {
						  
						  // TODO gestion de l'envoi des données capteur
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
