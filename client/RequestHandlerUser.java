package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//==================
//TODO 20/3/19 21h15
//==================

public class RequestHandlerUser implements Runnable {
		
		User listeningUser;
		
		BufferedReader socIn;
		
		// variable d'activation du requestHandler
		boolean handling;
		
		SimpleDateFormat parser=new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");

		/**
		 * <strong>Description : </strong>Constructeur de la classe RequestHandler
		 * Le request handler doit être instancié par un système embarqué pour fonctionner
		 * <strong>Exemple : </strong>RequestHandler(this)
		 * 
		 * @param newSystemeEmbarque Systeme embarqué sur lequel le requestHandler doit écouter
		 *
		 * @author S. Queyrut P. Lledo
		 */	
		public RequestHandlerUser(User newUser) {
			
			listeningUser = newUser;
			socIn = newUser.getSocIn();
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

			while (this.listeningUser.handling) {
				
				System.out.println("Inside runnable");
				
				try {
					maLigne = this.listeningUser.getSocIn().readLine();
					System.out.println("Ce qui suit est la ligne");
					System.out.println(maLigne);
					
					if (maLigne != null) {
						receivedLine = maLigne.split(" ");
						int compteur_mail = 0;  // pour remplir la liste des emails dans l'ordre

					switch(receivedLine[0]) { 
					
					  case "@log":
						  break;
						  
					  case "@get":  // TODO listes observables ? locales stockées où ?

						  for (int i=1; i<receivedLine.length - 2;i=i+2) {
							  
							  switch(receivedLine[i]) {
							  // TODO nombreux elements qui ne peuvent pas etre modifies, comme immatriculation par exemple.
							  	case "id":
							  		this.listeningUser.setId( receivedLine[i+1] );
							  		break;
							  		
							  	case "nom":
							  		this.listeningUser.setNom( receivedLine[i+1] );
							  		break;
							  		
							  	case "adresse":
							  		this.listeningUser.setAdresse( receivedLine[i+1] );
							  		break;
							  		
							  	case "email":  
							  		// on overwrite les mails dans la liste, puisque Caine renvoit
							  		// tout a chaque fois...
							  		(this.listeningUser.getEmailArray())[compteur_mail++] = receivedLine[i+1];
							  		break;
							  		
							  	case "bateau_immatriculation":
							  		this.listeningUser.bateauIm = receivedLine[i+1];  // TODO Obsolete, immatriculation ne peut pas changer sur demande du User
							  		break;
							  		
							  	case "bateau_nom":
							  		this.listeningUser.setBateauNom( receivedLine[i+1] );
							  		break;
							  		
							  	case "bateau_state":
							  		this.listeningUser.setBateauState( receivedLine[i+1] );
							  		break;
							  		
							  	case "date_abo":
							  		String dateString = receivedLine[i+1].replaceAll("_", " ");
								@SuppressWarnings("deprecation")
								Date dateAbo = null;
								try {
									
									dateAbo = parser.parse(dateString);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							  		this.listeningUser.setDateAbo(dateAbo);
							  		break;
							  		
							  	case "bateau_lieu":
							  		this.listeningUser.setBateauLieu(receivedLine[i+1]);
							  		break;
							  		
							  	case "bateau_pos":
							  		break;

							  }
						  }
						
						break;	
						} 
					}
				} catch (SocketException e) {
					System.out.println("Fermeture du socket");
					this.listeningUser.handling = false;
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}   				  
				    
			}
			System.out.println("[+] Fin du thread RequestHandler User.");
		}
	}
