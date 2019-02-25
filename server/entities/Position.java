package server.entities;

public class Position {
	private double latitude;
	private double longitude;
	/**
	 * <strong>Description : </strong> Classe d�finissant la structure de position GPS d'un bateau.
	 * @author C.Silva, R.Cuinat
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
	public double[] getCoordsInDegree() {
		return new double[] {latitude,longitude};
	}
	/**
	 * <strong>Description : </strong> M�thode permettant de r�cup�rer les coordonn�es d'un bateau en radians.
	 * @author C.Silva, R.Cuinat
	 * @return Latitude,longitude en radians.
	 */	
	public double[] getCoordsInRad() {
		return new double[] {latitude*Math.PI/180,longitude*Math.PI/180};
	}

}
