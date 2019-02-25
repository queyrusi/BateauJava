package server.entities;

public class Position {
	private double latitude;
	private double longitude;
	/**
	 * <strong>Description : </strong> Classe définissant la structure de position GPS d'un bateau.
	 * @author C.Silva, R.Cuinat
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
	public double[] getCoordsInDegree() {
		return new double[] {latitude,longitude};
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de récupérer les coordonnées d'un bateau en radians.
	 * @author C.Silva, R.Cuinat
	 * @return Latitude,longitude en radians.
	 */	
	public double[] getCoordsInRad() {
		return new double[] {latitude*Math.PI/180,longitude*Math.PI/180};
	}

}
