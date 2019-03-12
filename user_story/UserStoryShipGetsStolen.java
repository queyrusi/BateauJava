/**
 * 
 */

import server.CentralContext;
import server.ConnectionProtocol;
import server.entities.Ship;
import server.patterns.tcp.ServeurTCP;
import client.User;
import client.SystemeEmbarque;

import static org.junit.Assert.*;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import client.*;

/**
 * <strong>Description : </strong>User story pour abonnement d'un bateau et d'un user puis connexion de ceux-ci au serveur.
 * Le bateau est en état Monitoring pendant 7 secondes, puis, au bout de 7 secondes, un capteur GPS alerte le système d'alarme
 * et le bateau passe en état Stolen. Après avoir que le serveur en ait pris connaissance, le bateau passe en Tracking et envoit
 * sa position toutes les secondes (ici 3.14 42.42).
 * @author C.Silva, R.Cuinat, S.Queyrut, P.Lledo
 */
public class UserStoryShipGetsStolen {

	/**
	 * <strong>Description : </strong> Main la deuxième user story.
	 * @author C.Silva, R.Cuinat, S.Queyrut, P.Lledo
	 * @param args Param�tres du main
	 * @throws InterruptedException 
	 */
	@Test
	public void main() {
		
		CentralContext context = new CentralContext();
		Ship ship = new Ship("tati", "", "", "");
		try {
			context.addShip(ship);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		ConnectionProtocol protocol = new ConnectionProtocol();
		ServeurTCP serveur = new ServeurTCP(context, protocol, 6667);
		serveur.start();
	
		SystemeEmbarque newSys =  new SystemeEmbarque("127.0.0.18", 6667, "tati", "GPS"); // "LAPTOP-D7S9B5VD"
		((NoMonitoring) newSys.getNoMonitoringState()).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(client.Monitoring.class,newSys.getCurrentState().getClass());
		assertEquals(ship.getState().getClass(), server.shipStates.MonitoredState.class);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((GPS) newSys.requestSensor("gps")).start();
		assertEquals(client.Tracking.class,newSys.getCurrentState().getClass());
		assertEquals(ship.getState().getClass(), server.shipStates.TrackingState.class);
		assertTrue(Math.abs(ship.getPosition().getCoordsInDegree()[0]-2) < 1e-5);
		assertTrue(Math.abs(ship.getPosition().getCoordsInDegree()[1]-5) < 1e-5);
		newSys.deconnecterDuServeur();
		serveur.disconnect();
			

	}

}
