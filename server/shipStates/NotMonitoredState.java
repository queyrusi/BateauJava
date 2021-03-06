package server.shipStates;

import server.entities.Ship;
import server.patterns.ShipState;

/**
 * <strong>Description : </strong> Classe h�ritant de ShipState et d�finissant l'�tat 'Non surveill�' pour un bateau.
 * @author C.Silva, R.Cuinat
 */
public class NotMonitoredState extends ShipState{
	/**
	 * <strong>Description : </strong> Constructeur de la classe h�ritant de ShipState et d�finissant l'�tat 'Non surveill�' pour un bateau.
	 * @author C.Silva, R.Cuinat
	 * @param ship Bateau dont on r�f�rence l'�tat
	 */
	public NotMonitoredState(Ship ship) {
		// TODO Auto-generated constructor stub
		super(ship);
		System.out.println("Le bateau immatricul� " + ship.getImmatriculation() + " est en �tat NOT MONITORED.");
	}
	
	public String toString() {
		return "NotMonitored";
	}

}
