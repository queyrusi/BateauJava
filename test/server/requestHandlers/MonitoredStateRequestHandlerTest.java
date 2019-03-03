package server.requestHandlers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import server.CentralContext;
import server.ConnectionProtocol;
import server.entities.Position;
import server.entities.Ship;
import server.shipStates.MonitoredState;
import server.shipStates.TrackingState;

class MonitoredStateRequestHandlerTest {
	/**
	 * <strong>Description : </strong> Méthode permettant de tester le changement d'état vers tracking
	 * @author C.Silva, R.Cuinat
	 */
	@Test
	public void StolenTest() {
		CentralContext context = new CentralContext();
		Ship ship = new Ship("AZER","","","");
		try {
			context.addShip(ship);
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@Ship\nAZER\n@state stolen\n";
				int index = 0;
				@Override
				public int read() throws IOException {
					int envoi = message.charAt(index);
					index = (index +1)%(message.length());
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
				assertEquals(ship.getState().getClass(),TrackingState.class);
				assertEquals(ship.getRequestHandler().getClass(),TrackingStateRequestHandler.class);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <strong>Description : </strong> Méthode permettant de tester la reception de mauvais messages positions
	 * @author C.Silva, R.Cuinat
	 */
	@Test
	public void WrongPositionReceiving() {
		CentralContext context = new CentralContext();
		Ship ship = new Ship("AZER","","","");
		try {
			context.addShip(ship);
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@Ship\nAZER\n@pos 107\n@pos coucou couco\n@quit\n";
				int index = 0;
				@Override
				public int read() throws IOException {
					int envoi = message.charAt(index);
					index = (index +1)%(message.length());
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
				assertEquals(ship.getPosition(),null);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de tester la réception et sauvegarde d'une position
	 * @author C.Silva, R.Cuinat
	 */
	@Test
	public void PositionReceiving() {
		CentralContext context = new CentralContext();
		Ship ship = new Ship("AZER","","","");
		try {
			context.addShip(ship);
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@Ship\nAZER\n@pos 107.2 -4.1\n@quit\n";
				int index = 0;
				@Override
				public int read() throws IOException {
					int envoi = message.charAt(index);
					index = (index +1)%(message.length());
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
				assertTrue(Math.abs((ship.getPosition().getCoordsInDegree()[0]-107.2)) < 1e-5);
				assertTrue(Math.abs((ship.getPosition().getCoordsInDegree()[1]+4.1)) < 1e-5);
				assertNotEquals(ship.getDate(), null);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * <strong>Description : </strong> Méthode permettant de s'assurer de l'arrêt du gestionnaire de requêtes associé à un bateau lorsque le bateau se déconnecte.
	 * @author C.Silva, R.Cuinat
	 */
	@Test
	public void EndofThread() {
		CentralContext context = new CentralContext();
		Ship ship = new Ship("AZER", "", "", "");
		try {
			context.addShip(ship);
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@Ship\nAZER\n@quit\n";
				int index = 0;
				@Override
				public int read() throws IOException {
					int envoi = message.charAt(index);
					index = (index +1)%(message.length());
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
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de s'assurer qu'aucun gestionnaire de requêtes n'est instancié lorsque le bateau qui cherche à se connecter n'est pas référencé par le serveur.
	 * @author C.Silva, R.Cuinat
	 */
	@Test
	public void UnknownShip() {
			CentralContext context = new CentralContext();
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@Ship\nAZER\n";
				int index = 0;
				@Override
				public int read() throws IOException {
					int envoi = message.charAt(index);
					index = (index +1)%(message.length());
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
		Ship ship = new Ship("AZER", "", "", "");
		try {
			context.addShip(ship);
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@Ship\nAZER\n";
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
				assertEquals(ship.getState().getClass(),MonitoredState.class);
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
