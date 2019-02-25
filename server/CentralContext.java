package server;

import java.util.ArrayList;
import java.util.Hashtable;

import server.entities.Ship;
import server.entities.User;
import server.patterns.tcp.*;

public class CentralContext implements IContext {
	
	private Hashtable<String, User> DictionaryUsers;
	private Hashtable<String, Ship> DictionaryShips;
	private ArrayList<RequestHandler> CurrentRequestHandlers;
	
	//remarque : les hashtables sont synchronized
	
	public CentralContext() {
		DictionaryUsers = new Hashtable<String, User>();
		DictionaryShips = new Hashtable<String, Ship>();
		CurrentRequestHandlers = new ArrayList<RequestHandler>();
	}
	
	public void addRequestHandler(RequestHandler thread) {
		CurrentRequestHandlers.add(thread);
	}
	
	public void removeRequestHandler(RequestHandler thread) {
		CurrentRequestHandlers.remove(thread);
	}
	
	public int countRunningRequestHandler() {
		return CurrentRequestHandlers.size();
	}
	
	public void removeUser(String clef) throws IllegalAccessException {
		if (DictionaryUsers.containsKey(clef) == false) {
			throw new IllegalAccessException("Utilisateur inconnu"); }
		else {
			DictionaryShips.remove(DictionaryUsers.get(clef).getBateau().getImmatriculation());
			DictionaryUsers.remove(clef);}
	}
	
	public User getUser(String id) throws IllegalAccessException{
		if (DictionaryUsers.containsKey(id) == false) {
			throw new IllegalAccessException("Utilisateur inconnu");
		}
		else return DictionaryUsers.get(id);
	}

	public void addUser(String clef, User valeur) throws IllegalAccessException {
		if (DictionaryUsers.containsKey(clef) == true) {
			throw new IllegalAccessException("Utilisateur déjà enregistré"); }
		else DictionaryUsers.put(clef, valeur);
	}

	public Ship getShip(String immatriculation) throws IllegalAccessException{
		if (DictionaryShips.containsKey(immatriculation) == false) {
			throw new IllegalAccessException("Bateau inconnu");
		}
		else return DictionaryShips.get(immatriculation);
	}

	public void addShip(String clef, Ship valeur) throws IllegalAccessException {
		if (DictionaryShips.containsKey(clef) == true) {
			throw new IllegalAccessException("Bateau déjà enregistré"); }
		else {DictionaryShips.put(clef, valeur);}
	}
}
