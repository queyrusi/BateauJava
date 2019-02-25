package server.shipStates;

import server.entities.Ship;
import server.patterns.ShipState;

public class NotMonitoredState extends ShipState{

	public NotMonitoredState(Ship ship) {
		// TODO Auto-generated constructor stub
		super(ship);
	}
	
	public String toString() {
		return "état NotMonitored";
	}

}
