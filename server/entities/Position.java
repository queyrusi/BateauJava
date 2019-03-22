package server.entities;
/**
 * <strong>Description : </strong> Classe d�finissant la structure de position GPS d'un bateau.
 * @author C.Silva, R.Cuinat
 */
public class Position {
	private double latitude;
	private double longitude;
	/**
	 * <strong>Description : </strong> Constructeur de la classe d�finissant la structure de position GPS d'un bateau.
	 * @author C.Silva, R.Cuinat
	 * @param latitude la latitude en degr�s
	 * @param longitude la longitude en degr�s
	 */
	public Position(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
	}
	/**
	 * <strong>Description : </strong> M�thode permettant de r�cup�rer les coordonn�es d'un bateau en d�gr�s.
	 * @author C.Silva, R.Cuinat
	 * @return Latitude,longitude en degr�s.
	 */
	public final synchronized double[] getCoordsInDegree() {
		return new double[] {latitude,longitude};
	}
	/**
	 * <strong>Description : </strong> M�thode permettant de r�cup�rer les coordonn�es d'un bateau en radians.
	 * @author C.Silva, R.Cuinat
	 * @return Latitude,longitude en radians.
	 */	
	public final synchronized double[] getCoordsInRad() {
		return new double[] {latitude*Math.PI/180,longitude*Math.PI/180};
	}
	
	public String toString() {
		return this.latitude+" ; "+this.longitude;
	}

}
