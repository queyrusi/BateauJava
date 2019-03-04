/**
 * 
 */
package client;

//===================
//TODO 4/2/19 23h03
//===================

/**
 * Interface pour chaque Capteur (feuille) et chaque GroupeCapteur (composite)
 *  
 * @author chenqun
 *
 */
public interface CapteurComposant {
	
	// ADD
	// ====
	public default void add(CapteurComposant newCapteurComposant) {}
	
	// REMOVE
	// ======
	public default void remove(CapteurComposant newCapteurComposent) {}
	
	// GET
	// ====
	public default CapteurComposant getComposant(int composantIndex) {
		return null;}
	
	// GETINFO
	// =======
	public default String getCapteurLabel() {
		return null;}

	// DISPLAY INFO
	// ============
	public default void displayCapteurInfo() {}
	
}
