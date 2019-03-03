package server.shipStates;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import server.entities.Ship;

class TrackingStateTest {

	/**
	 * <strong>Description : </strong> M�thode permettant de s'assurer de la bonne instanciation de l'�tat.
	 * @author C.Silva, R.Cuinat
	 */
	@Test
	void Instance() {
		Ship ship = new Ship("","","","");
		TrackingState state = new TrackingState(ship);
		assertEquals(state.getShip(),ship);
	}
}
