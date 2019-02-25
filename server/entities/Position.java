package server.entities;

public class Position {
	private double latitude;
	private double longitude;
	
	public Position(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
	}
	
	public double[] getCoordsInDegree() {
		return new double[] {latitude,longitude};
	}
	
	public double[] getCoordsInRad() {
		return new double[] {latitude*Math.PI/180,longitude*Math.PI/180};
	}

}
