package server.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PositionTest {

	/**
	 * <strong>Description : </strong> Méthode permettant de tester la bonne instanciation de l'objet Position
	 * @author C.Silva, R.Cuinat
	 */
	@Test
	public void Instance() {
		Position pos = new Position(10,12);
		assertTrue(Math.abs(pos.getCoordsInDegree()[0] - 10) < 1e-5);
		assertTrue(Math.abs(pos.getCoordsInDegree()[1] - 12) < 1e-5);
	}

}
