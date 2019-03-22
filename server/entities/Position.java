package server.entities;
/**
 * <strong>Description : </strong> Classe définissant la structure de position GPS d'un bateau.
 * @author C.Silva, R.Cuinat
 */
public class Position {
	private double latitude;
	private double longitude;
	/**
	 * <strong>Description : </strong> Constructeur de la classe définissant la structure de position GPS d'un bateau.
	 * @author C.Silva, R.Cuinat
	 * @param latitude la latitude en degrés
	 * @param longitude la longitude en degrés
	 */
	public Position(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de récupérer les coordonnées d'un bateau en dégrés.
	 * @author C.Silva, R.Cuinat
	 * @return Latitude,longitude en degrés.
	 */
	public final synchronized double[] getCoordsInDegree() {
		return new double[] {latitude,longitude};
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de récupérer les coordonnées d'un bateau en radians.
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
