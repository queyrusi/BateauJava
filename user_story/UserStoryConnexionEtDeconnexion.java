

import server.CentralContext;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import server.ConnectionProtocol;
import server.entities.Ship;
import server.patterns.tcp.ServeurTCP;
import client.User;
import client.NoMonitoring;
import client.SystemeEmbarque;

/**
 * <strong>Description : </strong>User story pour abonnement d'un bateau et d'un user puis connexion de ceux-ci au serveur.
 * @author C.Silva, R.Cuinat, S.Queyrut, P.Lledo
 */
public class UserStoryConnexionEtDeconnexion {

	/**
	 * <strong>Description : </strong> Main pour abonnement d'un bateau et d'un user puis connexion de ceux-ci au serveur.
	 * @author C.Silva, R.Cuinat, S.Queyrut, P.Lledo
	 * @param args Param�tres du main
	 */
	@Test
	public void UserStory() {
		
		CentralContext context = new CentralContext();
		Ship ship = new Ship("tata", "", "", "");
		server.entities.User user = new server.entities.User("toto", "", null, null, null);
		try {
			context.addUser(user);
			context.addShip(ship);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		ConnectionProtocol protocol = new ConnectionProtocol();
		ServeurTCP serveur = new ServeurTCP(context, protocol, 6666);
		serveur.start();
		
		client.User newUser = new client.User(null, 6666, "toto1");
		SystemeEmbarque newSys =  new SystemeEmbarque(null, 6666, "tata", "GPS");
		
		newUser.connecterAuServeur();
		((NoMonitoring) newSys.getNoMonitoringState()).start();		
		try {
			TimeUnit.SECONDS.sleep(3);
			assertEquals(client.Monitoring.class,newSys.getCurrentState().getClass());
			assertEquals(ship.getState().getClass(), server.shipStates.MonitoredState.class);
			TimeUnit.SECONDS.sleep(1);
			newSys.deconnecterDuServeur();
			newUser.deconnecterDuServeur();
			serveur.disconnect();
			TimeUnit.SECONDS.sleep(1);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
