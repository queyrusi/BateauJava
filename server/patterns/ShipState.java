package server.patterns;

import server.entities.Ship;
import server.patterns.tcp.RequestHandler;

/**
 * <strong>Description : </strong>Etat abstrait d'un bateau
 * @author C.Silva, R.Cuinat
 */
public abstract class ShipState {
	private Ship ship;
	/**
	 * <strong>Description : </strong>Constructeur de l'�tat
	 * @author C.Silva, R.Cuinat
	 * @param ship Bateau associ� � l'�tat
	 */
	public ShipState(Ship ship) {
		// TODO Auto-generated constructor stub
		this.ship = ship;
	}
	/**
	 * <strong>Description : </strong>Getter du bateau associ� � l'�tat
	 * @author C.Silva, R.Cuinat
	 */
	public Ship getShip() {
		return ship;
	}
}
