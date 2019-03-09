/**
 * 
 */
package client;

import java.util.Random;

//===================
//TODO 6/3/19 15h45
//===================

/**
 * @author chenqun
 *
 */
public class GPS extends Capteur {
	
	private float r=6371.009;
	
	// Distance d'alerte du capteur. Attention en km
	private float dist;
	
	Random randomizer;
	
	float latitude;  // TODO a initialiser dans le constr
	
	
	float longitude; // TODO a initialiser dans le constr

	/**
	 * @param newCapteurName
	 * @param newCapteurLabel
	 */
	public GPS(String newCapteurLabel, SystAlarme newSystAlarme) {
		super(newCapteurLabel, newSystAlarme);
		latitude=generateCoordinateNorth();
		longitude=generatecoordinateEast();
		// TODO pas le bon argument
		
	}
	
	public float generateCoordinateNorth() {
		
		return (180 * randomizer.nextFloat() - 90);
	}

	
	public float generateCoordinateEast() {
		
		return (360 * randomizer.nextFloat() - 180);
	}
	
	@Override
	public void start() {
		// Longitude et latitude temporaires
		float tempLat;
		float tempLong;
		float x;
		float y;
		float z;
		float d;
		while(true){
			tempLat=generateCoordinateNorth();
			tempLong=generatecoordinateEast();
			x=(cos(latitude)*cos(longitude)-cos(tempLat)*cos(tempLong))**2
			y=(cos(latitude)*sin(longitude)-cos(tempLat)*sin(tempLong))**2
			z=(sin(latitude)-sin(tempLat))**2;
			d=r*sqrt((x+y+z));
			if (d>=dist){
				warning();
			}
			latitude=tempLat;
			longitude=tempLong;
		}
			
		
		
	}

	@Override
	public String getCapteurValueString() {
		// TODO retouner un string des valeurs Nord et Est 
		// type primitif Integer.toString(item.getId());   !!!
		
		return null;
	} 
	

}
