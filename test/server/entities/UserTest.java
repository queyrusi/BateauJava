package server.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.junit.jupiter.api.Test;

class UserTest {
	/**
	 * <strong>Description : </strong> Méthode permettant de tester la bonne instanciation de l'objet User
	 * @author C.Silva, R.Cuinat
	 */
	@Test
	public void Instance() {
		ArrayList<String> email = new ArrayList<String>();
		ArrayList<TrustedContact> tc = new ArrayList<TrustedContact>();
		Ship ship = new Ship("","","","");
		User user = new User("Nom","Adresse",email, tc, ship);
		assertEquals(user.getAdresse(), "Adresse");
		assertEquals(user.getBateau(),ship);
		assertTrue(Math.abs(user.getDate_abo().getTime() - Calendar.getInstance().getTimeInMillis()) < 100);
		assertEquals(user.getListe_amis(),tc);
		assertEquals(user.getListe_emails(),email);
		assertEquals(user.getRequestHandler(),null);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de tester que 2 homonymes ont des id différents
	 * @author C.Silva, R.Cuinat
	 */
	public void HomonymeID() {
		User user1 = new User("Bob","",null,null,null);
		User user2 = new User("Bob","",null,null,null);
		assertNotEquals(user1.getId(), user2.getId());
	}
	
	

}
