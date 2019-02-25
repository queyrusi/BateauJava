package server.shipStates;

import server.entities.Ship;
import server.patterns.ShipState;

public class MonitoredState extends ShipState {
	/**
	 * <strong>Description : </strong> Classe héritant de ShipState et définissant l'état 'Surveillé' pour un bateau.
	 * @author C.Silva, R.Cuinat
	 */
	public MonitoredState(Ship ship) {
		super(ship);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "état Monitored";
	}

}
