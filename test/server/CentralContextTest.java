package server;

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
/**
 * <strong>Description : </strong> Classe de test de la classe CentralContext.
 * @author C.Silva, R.Cuinat
 */
public class CentralContextTest {
	/**
	 * <strong>Description : </strong> Méthode permettant de s'assurer que la suppression d'un utilisateur du contexte fonctionne.
	 * @author C.Silva, R.Cuinat
	 * @throws Exception lorsqu'un utilisateur est inconnu 
	 */
	@Test(expected = IllegalAccessException.class)
	public void testRemoveClient() throws Exception {
		Ship ship1=new Ship("RM17XY", "Roca Ruby", "Catamaran", "Ponton de Jard Sur Mer");
		//TrustedContact tC1 =new TrustedContact("Moreau Thomas", "amidetoto@yopmail.com",0652456765);
		User user1=new User("Bedlin Simon", "1 rue du Moulin", null, null, ship1);
		CentralContext contexte = new CentralContext();
		contexte.addShip(ship1);
		contexte.addUser(user1);
		String ID = user1.getId();
		assertEquals(contexte.getUser(ID),user1);
		contexte.removeUser(ID);
		contexte.getUser(user1.getId());
		
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de s'assurer qu'une tentative d'accès à un client non référencé renvoie une erreur.
	 * @author C.Silva, R.Cuinat
	 * @throws Exception lorsqu'un client est inconnu
	 */
	@Test(expected = IllegalAccessException.class)
	public void testGetClient() throws Exception {
		CentralContext contexte = new CentralContext();
		contexte.getUser("Remi Bontaz0");
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de s'assurer que l'ajout d'un client s'effectue correctement.
	 * @author C.Silva, R.Cuinat
	 * @throws Exception lorsqu'un client est inconnu
	 */
	@Test
	public void testAddClient() throws Exception {
		Ship ship1 = new Ship("RM17XY", "Roca Ruby", "Catamaran", "Ponton de Jard Sur Mer");
		//TrustedContact tC1 = new TrustedContact("Moreau Thomas", "amidetoto@yopmail.com",0652456765);
		User user1=new User("Bedlin Simon", "1 rue du Moulin", null, null, ship1);
		CentralContext contexte = new CentralContext();
		contexte.addShip(ship1);
		contexte.addUser(user1);
		assertEquals(contexte.getUser(user1.getId()).getNom(),"Bedlin Simon");
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de s'assurer qu'une tentative d'accès à un bateau non référencé renvoie une erreur.
	 * @author C.Silva, R.Cuinat
	 * @throws Exception lorsqu'un bateau est inconnu
	 */
	@Test(expected = IllegalAccessException.class)
	public void testGetBoat() throws Exception {
		CentralContext contexte = new CentralContext();
		contexte.getShip("RM17XY");
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de s'assurer que l'ajout d'un bateau fonctionne.
	 * @author C.Silva, R.Cuinat
	 * @throws Exception lorsqu'un bateau est inconnu
	 */
	@Test
	public void testAddBoat() throws Exception {
		CentralContext contexte = new CentralContext();
		Ship bateau1=new Ship("RM17XY", "Roca Ruby", "Catamaran", "Ponton de Jard Sur Mer");
		contexte.addShip(bateau1);
		assertEquals(contexte.getShip(bateau1.getImmatriculation()),bateau1);
	}

}
