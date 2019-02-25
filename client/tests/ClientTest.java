package client.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import client.Monitoring;
import client.NoMonitoring;
import client.Stolen;
import client.SystemeEmbarque;
import client.Tracking;
import client.User;

class ClientTest {
	
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
		
		SystemeEmbarque sysEmbTest = new SystemeEmbarque("nomTest", 6666, "tata");
		assertEquals(sysEmbTest.getMonitoringState().getClass(), Monitoring.class);
		assertEquals(sysEmbTest.getNoMonitoringState().getClass(), NoMonitoring.class);
		assertEquals(sysEmbTest.getTrackingState().getClass(), Tracking.class);
		assertEquals(sysEmbTest.getStolenState().getClass(), Stolen.class);
		
	}

}
