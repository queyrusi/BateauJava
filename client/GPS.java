/**
 * 
 */
package client;

import java.util.Random;

//===================
//TODO 6/3/19 18h23
//===================

/**
 * @author chenqun
 *
 */
public class GPS extends Capteur {
	
	Random randomizer;
	
	float latitude;  // TODO a initialiser dans le constr
	
	
	float longitude; // TODO a initialiser dans le constr

	/**
	 * @param newCapteurName
	 * @param newCapteurLabel
	 */
	public GPS(String newCapteurLabel, SystAlarme newSystAlarme) {
		super(newCapteurLabel, newSystAlarme);
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
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public String getCapteurValueString() {
		// TODO retouner un string des valeurs Nord et Est 
		// type primitif Integer.toString(item.getId());   !!!
		
		return null;
	} 
	

}
