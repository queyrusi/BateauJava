package server.shipStates;

import server.entities.Ship;
import server.patterns.ShipState;

/**
 * <strong>Description : </strong> Classe héritant de ShipState et définissant l'état 'Non surveillé' pour un bateau.
 * @author C.Silva, R.Cuinat
 */
public class NotMonitoredState extends ShipState{
	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de ShipState et définissant l'état 'Non surveillé' pour un bateau.
	 * @author C.Silva, R.Cuinat
	 * @param ship Bateau dont on référence l'état
	 */
	public NotMonitoredState(Ship ship) {
		// TODO Auto-generated constructor stub
		super(ship);
	}
	
	public String toString() {
		return "état NotMonitored";
	}

}
