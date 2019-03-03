package server.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrustedContactTest {

	/**
	 * <strong>Description : </strong> Méthode permettant de tester la bonne instanciation de l'objet TrustedContact
	 * @author C.Silva, R.Cuinat
	 */
	public void Instance() {
		TrustedContact TC = new TrustedContact("nom", "email", 1);
		assertEquals(TC.getNom(),"nom");
		assertEquals(TC.getEmail(),"email");
		assertEquals(TC.getNum_tel(),1);
	}

}
