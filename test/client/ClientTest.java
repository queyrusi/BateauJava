package client;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import client.CapteurGroupe;
import client.GPS;
import client.Monitoring;
import client.NoMonitoring;
import client.Stolen;
import client.SystemeEmbarque;
import client.Tracking;
import client.User;

class ClientTest {

	/**
	 * <strong>Description : </strong> Méthode permettant de tester les méthodes
	 * User
	 * 
	 * @author P. Lledo, S. Queyrut
	 */
	@Test
	public void clientMethods() {

		User userTest = new User("nomTest", 6666, "toto");
		assertEquals(userTest.getLogin(), "toto");
		assertEquals(userTest.getNomServeur(), "nomTest");
		assertEquals(userTest.getNumeroPort(), 6666);
		assertEquals(userTest.getTypeConnexion(), "@User");

	}

	@Test
	public void sysEmbarqueState() {

		SystemeEmbarque sysEmbTest = new SystemeEmbarque("nomTest", 6666, "tata", "GPS");
		assertEquals(sysEmbTest.getMonitoringState().getClass(), Monitoring.class);
		assertEquals(sysEmbTest.getNoMonitoringState().getClass(), NoMonitoring.class);
		assertEquals(sysEmbTest.getTrackingState().getClass(), Tracking.class);
		assertEquals(sysEmbTest.getStolenState().getClass(), Stolen.class);

	}

	@Test
	public void capteurComposantDeclaration() {

		SystemeEmbarque sysEmbTest = new SystemeEmbarque("nomTest", 6666, "tata", "GPS");
		assertEquals(sysEmbTest.getCapteurList().getClass(), CapteurGroupe.class);
		assertEquals(sysEmbTest.getCapteurList().getComposant(0).getClass(), GPS.class);
	}

	@Test
	public void etatInitial() {
		SystemeEmbarque sysEmbTest = new SystemeEmbarque("nomTest", 6666, "tata", "GPS");
		assertEquals(sysEmbTest.getCurrentState().getClass(), NoMonitoring.class);
	}

	@Test
	public void changeStateNoMon2Mon() {

		SystemeEmbarque sysEmbTest = new SystemeEmbarque("nomTest", 6666, "tata", "GPS");
		sysEmbTest.changerEtat(sysEmbTest.getMonitoringState());
		assertEquals(sysEmbTest.getMonitoringState().getClass(), Monitoring.class);
	}

	@Test
	public void changeStateMon2Stolen() {
		assertThrows(NullPointerException.class,()->
		{
			SystemeEmbarque sysEmbTest = new SystemeEmbarque("nomTest", 6666, "tata", "GPS");
			sysEmbTest.changerEtat(sysEmbTest.getStolenState());
			assertEquals(sysEmbTest.getStolenState().getClass(), Stolen.class);
		});
	}
	
	@Test
	public void changeStateStolen2Track() {
		assertThrows(NullPointerException.class,()->
		{
			SystemeEmbarque sysEmbTest = new SystemeEmbarque("nomTest", 6666, "tata", "GPS");
			sysEmbTest.changerEtat(sysEmbTest.getTrackingState());
			assertEquals(sysEmbTest.getTrackingState().getClass(), Tracking.class);
		});
	}
	
	@Test
	public void requestSensor() {
			SystemeEmbarque sysEmbTest = new SystemeEmbarque("nomTest", 6666, "tata", "GPS");
			assertEquals(sysEmbTest.requestSensor("gps").getCapteurLabel(), "gps");
			assertNull(sysEmbTest.requestSensor("thermometre"));
	}
	
	
}
