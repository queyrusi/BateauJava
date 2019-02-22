package server.shipStates;

import server.entities.Ship;
import server.patterns.ShipState;

public class MonitoredState extends ShipState {

	public MonitoredState(Ship ship) {
		super(ship);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "état Monitored";
	}

}
