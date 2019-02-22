package server.patterns;

import server.entities.Ship;
import server.patterns.tcp.RequestHandler;

public abstract class ShipState {
	private Ship ship;
	private RequestHandler thread;
	
	public ShipState(Ship ship) {
		// TODO Auto-generated constructor stub
		this.ship = ship;
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public RequestHandler getThread() {
		return thread;
	}
}
