package server;

import java.util.ArrayList;
import java.util.Hashtable;

import server.entities.Ship;
import server.entities.User;
import server.patterns.tcp.*;

public class CentralContext implements IContext {
	/**
	 * <strong>Description : </strong> Classe impl�mentant IContext. Elle permet de recenser les donn�es utiles pour le server TCP (les donn�es 'bateau', les donn�es 'client' et les diff�rents gestionnaires de requ�tes instanci�s). Viennent s'ajouter les diff�rentes m�thodes d'acc�s et d'�criture aux dites donn�es.
	 * @author C.Silva, R.Cuinat
	 */
	private Hashtable<String, User> DictionaryUsers;
	private Hashtable<String, Ship> DictionaryShips;
	private ArrayList<RequestHandler> CurrentRequestHandlers;
	//remarque : les hashtables sont synchronized
	public CentralContext() {
		DictionaryUsers = new Hashtable<String, User>();
		DictionaryShips = new Hashtable<String, Ship>();
		CurrentRequestHandlers = new ArrayList<RequestHandler>();
	}
	/**
	 * <strong>Description : </strong> M�thode permettant d'ajouter un gestionnaire de requ�tes � la liste les centralisant.
	 * @author C.Silva, R.Cuinat
	 * @param un thread correspondant � un gestionnaire de requ�tes.
	 */
	public void addRequestHandler(RequestHandler thread) {
		CurrentRequestHandlers.add(thread);
	}
	
	/**
	 * <strong>Description : </strong> M�thode permettant de supprimer un gestionnaire de requ�tes � la liste les centralisant.
	 * @author C.Silva, R.Cuinat
	 * @param un thread correspondant � un gestionnaire de requ�tes.
	 */
	public void removeRequestHandler(RequestHandler thread) {
		CurrentRequestHandlers.remove(thread);
	}
	
	/**
	 * <strong>Description : </strong> M�thode permettant d'ajouter un gestionnaire de requ�tes � la liste les centralisant.
	 * @author C.Silva, R.Cuinat
	 * @return Le nombre de threads instanci�s. 
	 */
	public int countRunningRequestHandler() {
		return CurrentRequestHandlers.size();
	}
	
	/**
	 * <strong>Description : </strong> M�thode permettant de supprimer un utilisateur de la liste (ici un Hashtable) des utilisateurs enregistr�s.
	 * @author C.Silva, R.Cuinat
	 */
	public void removeUser(String clef) throws IllegalAccessException {
		if (DictionaryUsers.containsKey(clef) == false) {
			throw new IllegalAccessException("Utilisateur inconnu"); }
		else {
			DictionaryShips.remove(DictionaryUsers.get(clef).getBateau().getImmatriculation());
			DictionaryUsers.remove(clef);}
	}
	/**
	 * <strong>Description : </strong> M�thode permettant de r�cup�rer un utilisateur de la liste (ici un Hashtable) des utilisateurs enregistr�s.
	 * @author C.Silva, R.Cuinat
	 * @param Un ID utilisateur.
	 * @return L'utilisateur correspondant � l'ID pass� en param�tre. 
	 */
	public User getUser(String id) throws IllegalAccessException{
		if (DictionaryUsers.containsKey(id) == false) {
			throw new IllegalAccessException("Utilisateur inconnu");
		}
		else return DictionaryUsers.get(id);
	}
	/**
	 * <strong>Description : </strong> M�thode permettant d'ajouter un utilisateur � la liste (ici un Hashtable) des utilisateurs enregistr�s.
	 * @author C.Silva, R.Cuinat
	 * @param Un ID utilisateur et un utilisateur.
	 */
	public void addUser(String clef, User valeur) throws IllegalAccessException {
		if (DictionaryUsers.containsKey(clef) == true) {
			throw new IllegalAccessException("Utilisateur d�j� enregistr�"); }
		else DictionaryUsers.put(clef, valeur);
	}
	/**
	 * <strong>Description : </strong> M�thode permettant de r�cup�rer un bateau de la liste (ici un Hashtable) des bateaux enregistr�s.
	 * @author C.Silva, R.Cuinat
	 * @param Une immatriculation.
	 * @return Le bateau associ� � l'immatriculation donn�e en param�tre.
	 */
	public Ship getShip(String immatriculation) throws IllegalAccessException{
		if (DictionaryShips.containsKey(immatriculation) == false) {
			throw new IllegalAccessException("Bateau inconnu");
		}
		else return DictionaryShips.get(immatriculation);
	}
	/**
	 * <strong>Description : </strong> M�thode permettant d'ajouter un bateau � la liste (ici un Hashtable) des bateaux enregistr�s.
	 * @author C.Silva, R.Cuinat
	 * @param Une immatriculation et un bateau.
	 */
	public void addShip(String clef, Ship valeur) throws IllegalAccessException {
		if (DictionaryShips.containsKey(clef) == true) {
			throw new IllegalAccessException("Bateau d�j� enregistr�"); }
		else {DictionaryShips.put(clef, valeur);}
	}
	/**
	 * <strong>Description : </strong> M�thode permettant de retirer un bateau de la liste (ici un Hashtable) des bateaux enregistr�s.
	 * @author C.Silva, R.Cuinat
	 * @param Une immatriculation.
	 */
	public void removeShip(String immatriculation) throws IllegalAccessException {
		if (DictionaryShips.containsKey(immatriculation) == false) {
			throw new IllegalAccessException("Bateau inconnu"); }
		else {DictionaryShips.remove(immatriculation);}
	}
}
