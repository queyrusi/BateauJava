package server.shipStates;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import server.entities.Ship;

class NotMonitoredStateTest {
	/**
	 * <strong>Description : </strong> Méthode permettant de s'assurer de la bonne instanciation de l'état.
	 * @author C.Silva, R.Cuinat
	 */
	@Test
	void Instance() {
		Ship ship = new Ship("","","","");
		NotMonitoredState state = new NotMonitoredState(ship);
		assertEquals(state.getShip(),ship);
	}

}
