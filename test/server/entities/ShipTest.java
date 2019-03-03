package server.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShipTest {

	/**
	 * <strong>Description : </strong> Méthode permettant de tester la bonne instanciation de l'objet Position
	 * @author C.Silva, R.Cuinat
	 */
	@Test
	public void Instance() {
		Ship ship = new Ship("Immat","Modele","Type","Station");
		assertEquals(ship.getImmatriculation(),"Immat");
		assertEquals(ship.getModele(),"Modele");
		assertEquals(ship.getType(),"Type");
		assertEquals(ship.getEndroit_stationnement(),"Station");
	}
	

}
