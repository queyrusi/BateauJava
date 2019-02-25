package server.shipStates;

import server.entities.Ship;
import server.patterns.ShipState;

public class TrackingState extends ShipState {

	public TrackingState(Ship ship) {
		// TODO Auto-generated constructor stub
		super(ship);
	}
	
	public String toString() {
		return "état Tracking";
	}
}
