package server.shipStates;

import server.entities.Ship;
import server.patterns.ShipState;
/**
 * <strong>Description : </strong> Classe héritant de ShipState et définissant l'état 'Surveillé' pour un bateau.
 * @author C.Silva, R.Cuinat
 */
public class MonitoredState extends ShipState {
	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de ShipState et définissant l'état 'Surveillé' pour un bateau.
	 * @author C.Silva, R.Cuinat
	 * @param ship Bateau dont on référence l'état
	 */
	public MonitoredState(Ship ship) {
		super(ship);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "état Monitored";
	}

}
