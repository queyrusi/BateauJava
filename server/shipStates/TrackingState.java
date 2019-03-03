package server.shipStates;

import server.entities.Ship;
import server.patterns.ShipState;
/**
 * <strong>Description : </strong> Classe h�ritant de ShipState et d�finissant l'�tat 'Suivi' pour un bateau.
 * @author C.Silva, R.Cuinat
 */
public class TrackingState extends ShipState {
	/**
	 * <strong>Description : </strong> Constructeur de la classe h�ritant de ShipState et d�finissant l'�tat 'Suivi' pour un bateau.
	 * @author C.Silva, R.Cuinat
	 * @param ship un objet bateau
	 */
	public TrackingState(Ship ship) {
		// TODO Auto-generated constructor stub
		super(ship);
	}
	
	public String toString() {
		return "�tat Tracking";
	}
}
