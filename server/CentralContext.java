package server;

import java.util.ArrayList;
import java.util.Hashtable;

import server.entities.Ship;
import server.entities.User;
import server.patterns.tcp.*;
import server.patterns.update.IUpdateCenter;

/**
 * <strong>Description : </strong> Classe implémentant IContext. Elle permet de recenser les données utiles pour le server TCP (les données 'bateau', les données 'client' et les différents gestionnaires de requêtes instanciés). Viennent s'ajouter les différentes méthodes d'accès et d'écriture aux dites données.
 * @author C.Silva, R.Cuinat
 */
public class CentralContext implements IContext {
	private IUpdateCenter updateCenter;
	private Hashtable<String, User> DictionaryUsers;
	private Hashtable<String, Ship> DictionaryShips;
	private ArrayList<RequestHandler> CurrentRequestHandlers; //sert à compter le nombre de thread et à kill le serveur et donc tous les threads ouverts.
	//remarque : les hashtables sont synchronized
	
	/**
	 * <strong>Description : </strong> Constructeur de la classe implémentant IContext. Elle permet de recenser les données utiles pour le server TCP (les données 'bateau', les données 'client' et les différents gestionnaires de requêtes instanciés). Viennent s'ajouter les différentes méthodes d'accès et d'écriture aux dites données.
	 * @param updateCenter Interface pour la gestion de la mise à jour de données
	 * @author C.Silva, R.Cuinat
	 */
	public CentralContext(IUpdateCenter updateCenter) {
		DictionaryUsers = new Hashtable<String, User>();
		DictionaryShips = new Hashtable<String, Ship>();
		CurrentRequestHandlers = new ArrayList<RequestHandler>();
		this.setUpdateCenter(updateCenter);
	}
	
	/**
	 * <strong>Description : </strong> Constructeur de la classe implémentant IContext. Elle permet de recenser les données utiles pour le server TCP (les données 'bateau', les données 'client' et les différents gestionnaires de requêtes instanciés). Viennent s'ajouter les différentes méthodes d'accès et d'écriture aux dites données.
	 * @author C.Silva, R.Cuinat
	 */
	public CentralContext() {
		this(new IUpdateCenter() {});
	}
	/**
	 * <strong>Description : </strong> Méthode permettant d'ajouter un gestionnaire de requêtes à la liste les centralisant.
	 * @author C.Silva, R.Cuinat
	 * @param thread un thread correspondant à un gestionnaire de requêtes
	 */
	public final void addRequestHandler(RequestHandler thread) {
		CurrentRequestHandlers.add(thread);
	}
	
	/**
	 * <strong>Description : </strong> Méthode permettant de supprimer un gestionnaire de requêtes à la liste les centralisant.
	 * @author C.Silva, R.Cuinat
	 * @param thread un thread correspondant à un gestionnaire de requêtes
	 */
	public final void removeRequestHandler(RequestHandler thread) {
		CurrentRequestHandlers.remove(thread);
	}
	
	/**
	 * <strong>Description : </strong> Méthode permettant d'ajouter un gestionnaire de requêtes à la liste les centralisant.
	 * @author C.Silva, R.Cuinat
	 * @return le nombre de threads instanciés
	 */
	public final int countRunningRequestHandler() {
		return CurrentRequestHandlers.size();
	}
	
	/**
	 * <strong>Description : </strong> Méthode permettant de supprimer un utilisateur de la liste (ici un Hashtable) des utilisateurs enregistrés.
	 * @author C.Silva, R.Cuinat
	 * @param clef identifiant de l'utilisateur à retirer du système
	 * @throws IllegalAccessException lorsque l'indentifant est inconnu
	 */
	public final void removeUser(String clef) throws IllegalAccessException {
		if (DictionaryUsers.containsKey(clef) == false) {
			throw new IllegalAccessException("Utilisateur inconnu"); }
		else {
			User user = DictionaryUsers.get(clef);
			Ship ship = user.getBateau();
			if (user.getRequestHandler() != null) {
				user.getRequestHandler().interrupt();
				this.removeRequestHandler(user.getRequestHandler());
			}
			if (ship.getRequestHandler() != null) {
				ship.getRequestHandler().interrupt();
				this.removeRequestHandler(ship.getRequestHandler());
			}
			if (ship != null) this.removeShip(ship.getImmatriculation());
			DictionaryUsers.remove(clef);
		}
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de récupérer un utilisateur de la liste (ici un Hashtable) des utilisateurs enregistrés.
	 * @author C.Silva, R.Cuinat
	 * @param id Un ID utilisateur
	 * @return l'utilisateur correspondant à l'ID passé en paramètre
	 * @throws IllegalAccessException lorsque l'indentifant est inconnu
	 */
	public final User getUser(String id) throws IllegalAccessException{
		if (DictionaryUsers.containsKey(id) == false) {
			throw new IllegalAccessException("Utilisateur inconnu");
		}
		else return DictionaryUsers.get(id);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant d'ajouter un utilisateur à la liste (ici un Hashtable) des utilisateurs enregistrés.
	 * @author C.Silva, R.Cuinat
	 * @param valeur un utilisateur
	 * @throws IllegalAccessException lorsque l'utilisateur est inconnu
	 */
	public final void addUser(User valeur) throws IllegalAccessException {
		if (DictionaryUsers.containsKey(valeur.getId()) == true) {
			throw new IllegalAccessException("Utilisateur déjà enregistré"); }
		else {
			DictionaryUsers.put(valeur.getId(), valeur);
			valeur.setUpdateCenter(this.updateCenter);
			this.updateCenter.userAdded(valeur);
		}
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de récupérer un bateau de la liste (ici un Hashtable) des bateaux enregistrés.
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Une immatriculation
	 * @return Le bateau associé à l'immatriculation donnée en paramètre
	 * @throws IllegalAccessException lorsque l'immatriculation est inconnue du système
	 */
	public final Ship getShip(String immatriculation) throws IllegalAccessException{
		if (DictionaryShips.containsKey(immatriculation) == false) {
			throw new IllegalAccessException("Bateau inconnu");
		}
		else return DictionaryShips.get(immatriculation);
	}
	/**
	 * <strong>Description : </strong> Méthode permettant d'ajouter un bateau à la liste (ici un Hashtable) des bateaux enregistrés.
	 * @author C.Silva, R.Cuinat 
	 * @param valeur un bateau
	 * @throws IllegalAccessException lorsque le bateau est inconnu
	 */
	public final void addShip(Ship valeur) throws IllegalAccessException {
		if (DictionaryShips.containsKey(valeur.getImmatriculation()) == true) {
			throw new IllegalAccessException("Bateau déjà enregistré"); }
		else {
			DictionaryShips.put(valeur.getImmatriculation(), valeur);
			valeur.setUpdateCenter(this.updateCenter);
			this.updateCenter.shipAdded(valeur);
			}
	}
	/**
	 * <strong>Description : </strong> Méthode permettant de retirer un bateau de la liste (ici un Hashtable) des bateaux enregistrés.
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Une immatriculation
	 * @throws IllegalAccessException lorsque l'immatriculation est inconnue du système
	 */
	public final void removeShip(String immatriculation) throws IllegalAccessException {
		if (DictionaryShips.containsKey(immatriculation) == false) {
			throw new IllegalAccessException("Bateau inconnu"); }
		else {
			Ship ship = DictionaryShips.get(immatriculation);
			if (ship.getUser() != null) ship.getUser().setBateau(null);
			DictionaryShips.remove(immatriculation);
			this.updateCenter.shipRemoved(immatriculation);
			}
	}
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance updateCenter
	 * @author C.Silva, R.Cuinat
	 * @return updateCenter du context
	 */
	public final synchronized IUpdateCenter getUpdateCenter() {
		return updateCenter;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance updateCenter
	 * @author C.Silva, R.Cuinat
	 * @param updateCenter du context
	 */
	public final synchronized void setUpdateCenter(IUpdateCenter updateCenter) {
		this.updateCenter = updateCenter;
		for (User user : this.DictionaryUsers.values()) {
			user.setUpdateCenter(updateCenter);
		}
		for (Ship ship : this.DictionaryShips.values()) {
			ship.setUpdateCenter(updateCenter);
		}
	}
}
