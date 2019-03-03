	package server.requestHandlers;

	import static org.junit.Assert.assertEquals;
	import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;

	import org.junit.jupiter.api.Test;

	import server.CentralContext;
	import server.ConnectionProtocol;
	import server.entities.Ship;

	public class TrackingStateRequestHandlerTest {
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
				InputStream iStream = new InputStream() {
					String message = "@Ship\nAZER1\n@quit\n";
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
				TrackingStateRequestHandler monThread = new TrackingStateRequestHandler(ship, context, new BufferedReader(new InputStreamReader(iStream)), new BufferedWriter(new OutputStreamWriter(oStream)));
				ship.setRequestHandler(monThread);
				monThread.start();
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
		 * <strong>Description : </strong> Méthode permettant de s'assurer de la bonne instanciation d'un gestionnaire de requêtes et du bon changement d'état du bateau associé.
		 * @author C.Silva, R.Cuinat
		 */
		@Test
		public void StateChange() {
				CentralContext context = new CentralContext();
				Ship ship = new Ship("AZER", "", "", "");
				InputStream iStream = new InputStream() {
					String message = "\n";
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
				TrackingStateRequestHandler monThread = new TrackingStateRequestHandler(ship, context, new BufferedReader(new InputStreamReader(iStream)), new BufferedWriter(new OutputStreamWriter(oStream)));
				monThread.start();
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				assertEquals(context.countRunningRequestHandler(), 1);
				assertEquals(ship.getState().toString(),"état Tracking");
		}
	}
