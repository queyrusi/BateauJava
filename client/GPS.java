/**
 * 
 */
package client;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.lang.Math.*;

//===================
//TODO 6/3/19 15h45
//===================

/**<strong>Description : </strong>Classe GPS h�ritant de capteur
 * 
 * @author S. Queyrut P. Lledo
 *
 */
public class GPS extends Capteur {
	
	private float r = (float) 6371.009;
	
	// Distance d'alerte du capteur. Attention en km
	private float dist;
	
	Random randomizer;
	
	float latitude;  // TODO a initialiser dans le constr
	
	
	float longitude; // TODO a initialiser dans le constr

	/**<strong>Description : </strong>Constructeur de la classe GPS
	 * 
	 * <strong>Exemple : </strong>GPS("gps",monAlarme)
	 * 
	 * @param newCapteurName
	 * @param newCapteurLabel
	 * 
	 * @author S. Queyrut P. Lledo
	 */
	public GPS(String newCapteurLabel, SystAlarme newSystAlarme) {
		super(newCapteurLabel, newSystAlarme);
		latitude= 1; // TODO
		longitude=1; // TODO
		dist = 7;
		// TODO pas le bon argument
		
	}
	
	/**
	 * <strong>Description : </strong>G�n�rateur pseudo-al�atoire de latitude orient�e Nord
	 * 
	 * @author S. Queyrut P. Lledo
	 */
	public float generateCoordinateNorth() {
		
		return (180 * randomizer.nextFloat() - 90);
	}

	/**
	 * <strong>Description : </strong>G�n�rateur pseudo-al�atoire de longitude orient�e Est
	 * 
	 * @author S. Queyrut P. Lledo
	 */
	public float generateCoordinateEast() {
		
		return (360 * randomizer.nextFloat() - 180);
	}
	
		
	@Override
	/**
	 * <strong>Description : </strong>Methode d�marrant la simulation de donn�es GPS
	 * 
	 * @author S. Queyrut P. Lledo
	 */
	public void start() {
		// Longitude et latitude temporaires
		float tempLat;
		float tempLong;
		float x;
		float y;
		float z;
		float d = 0;
//		while(true){
//			tempLat = generateCoordinateNorth();
//			tempLong = generateCoordinateEast();
//			x = (float) Math.pow( Math.cos(latitude)*Math.cos(longitude) - Math.cos(tempLat) * Math.cos(tempLong), 2.0);
//			y = (float) Math.pow( Math.cos(latitude)*Math.sin(longitude) - Math.cos(tempLat) * Math.sin(tempLong), 2.0);
//			z = (float) Math.pow((Math.sin(latitude) - Math.sin(tempLat)), 2);
//			d = (float) (r * Math.sqrt((x + y + z)));
//		if (d>=dist){}
		while (d <= dist) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			d += 1;
		}
		warning();
	}

	@Override
	/**
	 * <strong>Description : </strong>Getter pour obtenir la valeur du capteur
	 * 
	 * @author S. Queyrut P. Lledo
	 */
	public String getCapteurValueString() {
		// TODO retouner un string des valeurs Nord et Est 
		// type primitif Integer.toString(item.getId());   !!!
		
		return "2";
	} 
	

}
