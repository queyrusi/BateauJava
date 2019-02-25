package server.shipStates;

import server.entities.Ship;
import server.patterns.ShipState;

public class NotMonitoredState extends ShipState{
	/**
	 * <strong>Description : </strong> Classe h�ritant de ShipState et d�finissant l'�tat 'Non surveill�' pour un bateau.
	 * @author C.Silva, R.Cuinat
	 */
	public NotMonitoredState(Ship ship) {
		// TODO Auto-generated constructor stub
		super(ship);
	}
	
	public String toString() {
		return "�tat NotMonitored";
	}

}
