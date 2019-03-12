/**
 * 
 */
package client;

//===================
//TODO 12/3/19 19h06
//===================

/**
 * Interface pour chaque Capteur (feuille) et chaque GroupeCapteur (composite)
 *  
 * @author S. Queyrut P. Lledo
 *
 */
public interface CapteurComposant {
	
	/**
	 * <strong>Description : </strong>Methode ajoutant un capteur à la liste de capteurs
	 * 
	 *
	 * @param newCapteurComposant Capteur à ajouter à la liste
	 *
	 * @author S. Queyrut P. Lledo
	 */
	public default void add(CapteurComposant newCapteurComposant) {}
	
	/**
	 * <strong>Description : </strong>Methode supprimant un capteur de la liste de capteurs
	 * 
	 * @param newCapteurComposent Capteur à supprimer de la liste
	 *
	 * @author S. Queyrut P. Lledo
	 */
	public default void remove(CapteurComposant newCapteurComposent) {}
	
	/**
	 * <strong>Description : </strong>Getter pour un composant spécifique
	 * 
	 * @param composantIndex Capteur que l'on souhaite obtenir
	 *
	 * @author S. Queyrut P. Lledo
	 */
	public default CapteurComposant getComposant(int composantIndex) {
		return null;}
	
	/**
	 * <strong>Description : </strong>Getter pour le nom du Capteur
	 * 
	 * @author S. Queyrut P. Lledo
	 */
	public default String getCapteurLabel() {
		return null;}

	/**
	 * <strong>Description : </strong>Affiche les infos du capteurs
	 * 
	 * @author S. Queyrut P. Lledo
	 */
	public default void displayCapteurInfo() {}

	/**
	 * <strong>Description : </strong>Getter pour obtenir la valeur du capteur
	 * 
	 * @author S. Queyrut P. Lledo
	 */
	public String getCapteurValueString();
	
}
