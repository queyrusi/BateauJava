package server.shipStates;

import server.entities.Ship;
import server.patterns.ShipState;

public class MonitoredState extends ShipState {
	/**
	 * <strong>Description : </strong> Classe h�ritant de ShipState et d�finissant l'�tat 'Surveill�' pour un bateau.
	 * @author C.Silva, R.Cuinat
	 */
	public MonitoredState(Ship ship) {
		super(ship);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "�tat Monitored";
	}

}
