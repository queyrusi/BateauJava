package server.requestHandlers;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import server.CentralContext;
import server.ConnectionProtocol;
import server.entities.TrustedContact;
import server.entities.User;

class UserRequestHandlerTest {
	/**
	 * <strong>Description : </strong> Méthode permettant de s'assurer de l'arrêt du gestionnaire de requêtes associé à un utilisateur lorsque l'utilisateur se déconnecte.
	 * @author C.Silva, R.Cuinat
	 */
	public void EndofThread() {
		CentralContext context = new CentralContext();
		User user = new User("Ali", "here", null, null, null);
		try {
			context.addUser(user);
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@User\n"+user.getId()+"\n@quit\n";
				int index = 0;
				@Override
				public int read() throws IOException {
					// TODO Auto-generated method stub
					int envoi = message.charAt(index);
					index = (index +1)%(message.length());
					return envoi;				
				}
			};
			OutputStream oStream = new OutputStream() {
				@Override
				public void write(int arg0) throws IOException {
					// TODO Auto-generated method stub
					
				}
			};
			protocol.execute(context, iStream, oStream);
			try {
				TimeUnit.SECONDS.sleep(1);
				assertEquals(context.countRunningRequestHandler(),0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de s'assurer qu'aucun gestionnaire de requêtes n'est instancié lorsque l'utilisateur qui cherche à se connecter n'est pas référencé par le serveur.
	 * @author C.Silva, R.Cuinat
	 */
	@Test
	public void UnknownUser() {
			CentralContext context = new CentralContext();
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@User\naliID\n";
				int index = 0;
				@Override
				public int read() throws IOException {
					int envoi = message.charAt(index);
					index = (index +1)%12;
					return envoi;				
				}
			};
			OutputStream oStream = new OutputStream() {
				@Override
				public void write(int arg0) throws IOException {
					
				}
			};
			protocol.execute(context, iStream, oStream);
			try {
				TimeUnit.SECONDS.sleep(1);
				assertEquals(context.countRunningRequestHandler(), 0);
				
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de s'assurer de la bonne instanciation d'un gestionnaire de requêtes.
	 * @author C.Silva, R.Cuinat
	 */	
	@Test
	public void StartOfThread() {
		CentralContext context = new CentralContext();
		User user = new User("Ali", "here", null, null, null);
		try {
			context.addUser(user);
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@User\n"+user.getId()+"\n";
				int index = 0;
				@Override
				public int read() throws IOException {
					// TODO Auto-generated method stub
					int envoi = message.charAt(index);
					index = (index +1)%(message.length());
					return envoi;				
				}
			};
			OutputStream oStream = new OutputStream() {
				@Override
				public void write(int arg0) throws IOException {
					// TODO Auto-generated method stub
					
				}
			};
			protocol.execute(context, iStream, oStream);
			try {
				TimeUnit.SECONDS.sleep(1);
				assertEquals(context.countRunningRequestHandler(),1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
