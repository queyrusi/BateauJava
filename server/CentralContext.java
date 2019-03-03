package server;

import java.util.ArrayList;
import java.util.Hashtable;

import server.entities.Ship;
import server.entities.User;
import server.patterns.tcp.*;

/**
 * <strong>Description : </strong> Classe impl�mentant IContext. Elle permet de recenser les donn�es utiles pour le server TCP (les donn�es 'bateau', les donn�es 'client' et les diff�rents gestionnaires de requ�tes instanci�s). Viennent s'ajouter les diff�rentes m�thodes d'acc�s et d'�criture aux dites donn�es.
 * @author C.Silva, R.Cuinat
 */
public class CentralContext implements IContext {
	private Hashtable<String, User> DictionaryUsers;
	private Hashtable<String, Ship> DictionaryShips;
	private ArrayList<RequestHandler> CurrentRequestHandlers; //sert � compter le nombre de thread et � kill le serveur et donc tous les threads ouverts.
	//remarque : les hashtables sont synchronized
	/**
	 * <strong>Description : </strong> Constructeur de la classe impl�mentant IContext. Elle permet de recenser les donn�es utiles pour le server TCP (les donn�es 'bateau', les donn�es 'client' et les diff�rents gestionnaires de requ�tes instanci�s). Viennent s'ajouter les diff�rentes m�thodes d'acc�s et d'�criture aux dites donn�es.
	 * @author C.Silva, R.Cuinat
	 */
	public CentralContext() {
		DictionaryUsers = new Hashtable<String, User>();
		DictionaryShips = new Hashtable<String, Ship>();
		CurrentRequestHandlers = new ArrayList<RequestHandler>();
	}
	/**
	 * <strong>Description : </strong> M�thode permettant d'ajouter un gestionnaire de requ�tes � la liste les centralisant.
	 * @author C.Silva, R.Cuinat
	 * @param thread un thread correspondant � un gestionnaire de requ�tes
	 */
	public void addRequestHandler(RequestHandler thread) {
		CurrentRequestHandlers.add(thread);
	}
	
	/**
	 * <strong>Description : </strong> M�thode permettant de supprimer un gestionnaire de requ�tes � la liste les centralisant.
	 * @author C.Silva, R.Cuinat
	 * @param thread un thread correspondant � un gestionnaire de requ�tes
	 */
	public void removeRequestHandler(RequestHandler thread) {
		CurrentRequestHandlers.remove(thread);
	}
	
	/**
	 * <strong>Description : </strong> M�thode permettant d'ajouter un gestionnaire de requ�tes � la liste les centralisant.
	 * @author C.Silva, R.Cuinat
	 * @return le nombre de threads instanci�s
	 */
	public int countRunningRequestHandler() {
		return CurrentRequestHandlers.size();
	}
	
	/**
	 * <strong>Description : </strong> M�thode permettant de supprimer un utilisateur de la liste (ici un Hashtable) des utilisateurs enregistr�s.
	 * @author C.Silva, R.Cuinat
	 * @param clef identifiant de l'utilisateur � retirer du syst�me
	 * @throws IllegalAccessException lorsque l'indentifant est inconnu
	 */
	public void removeUser(String clef) throws IllegalAccessException {
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
			DictionaryShips.remove(ship.getImmatriculation());
			DictionaryUsers.remove(clef);}
	}
	/**
	 * <strong>Description : </strong> M�thode permettant de r�cup�rer un utilisateur de la liste (ici un Hashtable) des utilisateurs enregistr�s.
	 * @author C.Silva, R.Cuinat
	 * @param id Un ID utilisateur
	 * @return l'utilisateur correspondant � l'ID pass� en param�tre
	 * @throws IllegalAccessException lorsque l'indentifant est inconnu
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
	 * @param valeur un utilisateur
	 * @throws IllegalAccessException lorsque l'utilisateur est inconnu
	 */
	public void addUser(User valeur) throws IllegalAccessException {
		if (DictionaryUsers.containsKey(valeur.getId()) == true) {
			throw new IllegalAccessException("Utilisateur d�j� enregistr�"); }
		else DictionaryUsers.put(valeur.getId(), valeur);
	}
	/**
	 * <strong>Description : </strong> M�thode permettant de r�cup�rer un bateau de la liste (ici un Hashtable) des bateaux enregistr�s.
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Une immatriculation
	 * @return Le bateau associ� � l'immatriculation donn�e en param�tre
	 * @throws IllegalAccessException lorsque l'immatriculation est inconnue du syst�me
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
	 * @param valeur un bateau
	 * @throws IllegalAccessException lorsque le bateau est inconnu
	 */
	public void addShip(Ship valeur) throws IllegalAccessException {
		if (DictionaryShips.containsKey(valeur.getImmatriculation()) == true) {
			throw new IllegalAccessException("Bateau d�j� enregistr�"); }
		else {DictionaryShips.put(valeur.getImmatriculation(), valeur);}
	}
	/**
	 * <strong>Description : </strong> M�thode permettant de retirer un bateau de la liste (ici un Hashtable) des bateaux enregistr�s.
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Une immatriculation
	 * @throws IllegalAccessException lorsque l'immatriculation est inconnue du syst�me
	 */
	public void removeShip(String immatriculation) throws IllegalAccessException {
		if (DictionaryShips.containsKey(immatriculation) == false) {
			throw new IllegalAccessException("Bateau inconnu"); }
		else {DictionaryShips.remove(immatriculation);}
	}
}
