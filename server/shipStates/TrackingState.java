package server.shipStates;

import server.entities.Ship;
import server.patterns.ShipState;
/**
 * <strong>Description : </strong> Classe héritant de ShipState et définissant l'état 'Suivi' pour un bateau.
 * @author C.Silva, R.Cuinat
 */
public class TrackingState extends ShipState {
	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de ShipState et définissant l'état 'Suivi' pour un bateau.
	 * @author C.Silva, R.Cuinat
	 * @param ship un objet bateau
	 */
	public TrackingState(Ship ship) {
		// TODO Auto-generated constructor stub
		super(ship);
		System.out.println("Le bateau immatriculé " + ship.getImmatriculation() + " est en état TRACKING.");
	}
	
	public String toString() {
		return "Tracking";
	}
}
