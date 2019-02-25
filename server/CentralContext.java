package server;

import java.util.ArrayList;
import java.util.Hashtable;

import server.entities.Ship;
import server.entities.User;
import server.patterns.tcp.*;

public class CentralContext implements IContext {
	/**
	 * <strong>Description : </strong> Classe implémentant IContext. Elle permet de recenser les données utiles pour le server TCP (les données 'bateau', les données 'client' et les différents gestionnaires de requêtes instanciés). Viennent s'ajouter les différentes méthodes d'accès et d'écriture aux dites données.
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
	 * <strong>Description : </strong> Méthode permettant d'ajouter un gestionnaire de requêtes à la liste les centralisant.
	 * @author C.Silva, R.Cuinat
	 * @param un thread correspondant à un gestionnaire de requêtes.
	 */
	public void addRequestHandler(RequestHandler thread) {
		CurrentRequestHandlers.add(thread);
	}
	
	/**
	 * <strong>Description : </strong> Méthode permettant de supprimer un gestionnaire de requêtes à la liste les centralisant.
	 * @author C.Silva, R.Cuinat
	 * @param un thread correspondant à un gestionnaire de requêtes.
	 */
	public void removeRequestHandler(RequestHandler thread) {
		CurrentRequestHandlers.remove(thread);
	}
	
	/**
	 * <strong>Description : </strong> Méthode permettant d'ajouter un gestionnaire de requêtes à la liste les centralisant.
	 * @author C.Silva, R.Cuinat
	 * @return Le nombre de threads instanciés. 
	 */
	public int countRunningRequestHandler() {
		return CurrentRequestHandlers.size();
	}
	
	/**
	 * <strong>Description : </strong> Méthode permettant de supprimer un utilisateur de la liste (ici un Hashtable) des utilisateurs enregistrés.
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
	 * <strong>Description : </strong> Méthode permettant de récupérer un utilisateur de la liste (ici un Hashtable) des utilisateurs enregistrés.
	 * @author C.Silva, R.Cuinat
	 * @param Un ID utilisateur.
	 * @return L'utilisateur correspondant à l'ID passé en paramètre. 
	 */
	public User getUser(String id) throws IllegalAccessException{
		if (DictionaryUsers.containsKey(id) == false) {
			throw new IllegalAccessException("Utilisateur inconnu");
		}
		else return DictionaryUsers.get(id);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant d'ajouter un utilisateur à la liste (ici un Hashtable) des utilisateurs enregistrés.
	 * @author C.Silva, R.Cuinat
	 * @param Un ID utilisateur et un utilisateur.
	 */
	public void addUser(String clef, User valeur) throws IllegalAccessException {
		if (DictionaryUsers.containsKey(clef) == true) {
			throw new IllegalAccessException("Utilisateur déjà enregistré"); }
		else DictionaryUsers.put(clef, valeur);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de récupérer un bateau de la liste (ici un Hashtable) des bateaux enregistrés.
	 * @author C.Silva, R.Cuinat
	 * @param Une immatriculation.
	 * @return Le bateau associé à l'immatriculation donnée en paramètre.
	 */
	public Ship getShip(String immatriculation) throws IllegalAccessException{
		if (DictionaryShips.containsKey(immatriculation) == false) {
			throw new IllegalAccessException("Bateau inconnu");
		}
		else return DictionaryShips.get(immatriculation);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant d'ajouter un bateau à la liste (ici un Hashtable) des bateaux enregistrés.
	 * @author C.Silva, R.Cuinat
	 * @param Une immatriculation et un bateau.
	 */
	public void addShip(String clef, Ship valeur) throws IllegalAccessException {
		if (DictionaryShips.containsKey(clef) == true) {
			throw new IllegalAccessException("Bateau déjà enregistré"); }
		else {DictionaryShips.put(clef, valeur);}
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de retirer un bateau de la liste (ici un Hashtable) des bateaux enregistrés.
	 * @author C.Silva, R.Cuinat
	 * @param Une immatriculation.
	 */
	public void removeShip(String immatriculation) throws IllegalAccessException {
		if (DictionaryShips.containsKey(immatriculation) == false) {
			throw new IllegalAccessException("Bateau inconnu"); }
		else {DictionaryShips.remove(immatriculation);}
	}
}
