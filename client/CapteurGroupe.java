/**
 * 
 */
package client;

import java.util.ArrayList;
import java.util.Iterator;

// ===================
// TODO 12/3/19 18h43
// ===================


/**<strong>Description : </strong>Implémentation composant-composite des capteurs
 * Regroupe l'ensemble des composants
 * 
 * @author S. Queyrut P.Lledo
 *
 */
public class CapteurGroupe implements CapteurComposant {
	
	ArrayList<CapteurComposant> capteurComposants = new ArrayList<CapteurComposant>();
	
	String groupName;
	
	/**
	 * <strong>Description : </strong>Constructeur de la classe CapteurGroupe
	 * 
	 * <strong>Exemple : </strong>CapteurGroupe("Ensemble capteur 1")
	 * 
	 * @param newGroupName Nom de l'ensemble de capteurs à instancier
	 *
	 * @author S. Queyrut P. Lledo
	 */
	public CapteurGroupe(String newGroupName){
		
		groupName = newGroupName;
		
	}
	
	/**
	 * <strong>Description : </strong>Getter de la variable GroupName
	 * 
	 * @return Retourne le nom du groupe
	 *
	 * @author S. Queyrut P. Lledo
	 */
	public String getGroupName() {
		
		return groupName; 
		
	}
	
	/**
	 * <strong>Description : </strong>Methode ajoutant un capteur à la liste de capteurs
	 * 
	 * @param newSongComponent Nom du capteur à ajouter
	 *
	 * @author S. Queyrut P. Lledo
	 */
	public void add(CapteurComposant newSongComponent) {
		
		capteurComposants.add(newSongComponent);
		
	}
	
	/**
	 * <strong>Description : </strong>Methode supprimant un capteur de la liste de capteurs
	 * 
	 * @param newSongComponent Nom du capteur à supprimer
	 *
	 * @author S. Queyrut P. Lledo
	 */
	public void remove(CapteurComposant newSongComponent) {
		
		capteurComposants.remove(newSongComponent);
		
	}
	
	/**
	 * <strong>Description : </strong>Getter pour un composant spécifique
	 * 
	 * @param componentIndex Index du capteur à obtenir
	 *
	 * @return Retourne le composant sélectionné
	 *
	 * @author S. Queyrut P. Lledo
	 */
	public CapteurComposant getComposant(int componentIndex) {
		
		return (CapteurComposant)capteurComposants.get(componentIndex);
		
	}
	
	/**
	 * <strong>Description : </strong>Affiche les infos de l'ensemble des capteurs
	 * 
	 * @author S. Queyrut P. Lledo
	 */
	public void displayCapteurInfo(){
		
		System.out.println(getGroupName() + "\n");
		
		Iterator<CapteurComposant> capteurIterator = capteurComposants.iterator();
		
		while(capteurIterator.hasNext()) { 
			
			CapteurComposant capteurInfo = capteurIterator.next();
			
		    capteurInfo.displayCapteurInfo();
			
		}
		
	}

	/**
	 * <strong>Description : </strong>Getter pour la liste des composants
	 * 
	 * @return Retourne la liste de tous les composants
	 *
	 * @author S. Queyrut P. Lledo
	 */
	public ArrayList<CapteurComposant> getcapteurComposants() {
		
		return capteurComposants;
	}

	@Override
	/**
	 * <strong>Description : </strong>Getter pour obtenir la valeur du capteur
	 * 
	 * @return null
	 *
	 * @author S. Queyrut P. Lledo
	 */
	public String getCapteurValueString() {
		// TODO Auto-generated method stub
		return null;
	}

}
