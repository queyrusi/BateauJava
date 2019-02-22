package server.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import server.CentralContext;
import server.ConnectionProtocol;
import server.entities.Ship;
import server.entities.TrustedContact;
import server.entities.User;

public class CentralContextTest {

	@Test(expected = IllegalAccessException.class)
	public void testRemoveClient() throws Exception {
		Ship ship1=new Ship("RM17XY", "Roca Ruby", "Catamaran", "Ponton de Jard Sur Mer");
		TrustedContact tC1 =new TrustedContact("Moreau Thomas", "amidetoto@yopmail.com",0652456765);
		User user1=new User("Bedlin Simon", "1 rue du Moulin", "toto@yopmail.com", tC1, ship1);
		CentralContext contexte = new CentralContext();
		contexte.addShip("RM17XY", ship1);
		contexte.addUser(user1.getId(), user1);
		String ID = user1.getId();
		assertEquals(contexte.getUser(ID),user1);
		contexte.removeUser(ID);
		contexte.getUser(user1.getId());
	}

	@Test(expected = IllegalAccessException.class)
	public void testGetClient() throws Exception {
		CentralContext contexte = new CentralContext();
		contexte.getUser("Remi Bontaz0");
	}

	@Test
	public void testAddClient() throws Exception {
		Ship ship1 = new Ship("RM17XY", "Roca Ruby", "Catamaran", "Ponton de Jard Sur Mer");
		TrustedContact tC1 = new TrustedContact("Moreau Thomas", "amidetoto@yopmail.com",0652456765);
		User user1=new User("Bedlin Simon", "1 rue du Moulin", "toto@yopmail.com", tC1, ship1);
		CentralContext contexte = new CentralContext();
		contexte.addShip("RM17XY", ship1);
		contexte.addUser(user1.getId(), user1);
		assertEquals(contexte.getUser(user1.getId()).getNom(),"Bedlin Simon");
	}

	@Test(expected = IllegalAccessException.class)
	public void testGetBoat() throws Exception {
		CentralContext contexte = new CentralContext();
		contexte.getShip("RM17XY");
	}

	@Test
	public void testAddBoat() throws Exception {
		CentralContext contexte = new CentralContext();
		Ship bateau1=new Ship("RM17XY", "Roca Ruby", "Catamaran", "Ponton de Jard Sur Mer");
		contexte.addShip("RM17XY", bateau1);
		assertEquals(contexte.getShip(bateau1.getImmatriculation()),bateau1);
	}

}
