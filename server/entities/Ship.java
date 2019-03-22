package server.entities;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Hashtable;

import server.patterns.ShipState;
import server.patterns.io.ILogger;
import server.patterns.update.IUpdateCenter;
import server.shipStates.NotMonitoredState;

/**
 * <strong>Description : </strong> Classe héritant de Client et définissant la structure d'un bateau.
 * @author C.Silva, R.Cuinat
 */
public class Ship extends Client {
	private final String immatriculation;
	private int password;
	private String nom;
	private String modele;
	private String type;
	private String endroit_stationnement;
	private Position position;
	private ShipState state;
	private Calendar date;
	private ILogger logger;
	private Hashtable<Calendar, Position> posLog;
	private User user;
	private IUpdateCenter updateCenter;

	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de Client et définissant la structure d'un bateau.
	 * Ce constructeur permet d'initialiser le bateau avec une position courante.
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Immatriculation du bateau
	 * @param nom Nom du bateau
	 * @param modele Modèle du bateau
	 * @param type Type de bateau
	 * @param station Lieu de stationnement du bateau
	 * @param pos Position actuelle du bateau
	 * @param classname nom de la classe de Logger à utiliser
	 * @param password mot de passe de connexion
	 */
	public Ship(String immatriculation,String nom, String password,String modele, String type, String station,Position pos, String classname) {
		this.immatriculation=immatriculation;
		this.nom=nom;
		this.password = password.hashCode();
		this.modele=modele;
		this.type=type;
		this.endroit_stationnement=station;
		this.position=pos;
		this.state= new NotMonitoredState(this);
		this.date = Calendar.getInstance();
		this.posLog = new Hashtable<Calendar, Position>();
		this.updateCenter = new IUpdateCenter() {};
		try {
			this.logger = (ILogger) Class.forName(classname).getConstructor(String.class).newInstance(this.immatriculation);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de Client et définissant la structure d'un bateau.
	 * Ce constructeur permet de créer un bateau sans nom et sans position intiale.
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Immatriculation du bateau
	 * @param modele Modèle du bateau
	 * @param type Type de bateau
	 * @param station Lieu de stationnement du bateau
	 *  @param password mot de passe de connexion
	 */
	public Ship(String immatriculation, String password ,String modele, String type, String station) {
		this(immatriculation,null,password,modele,type,station,null,"server.LoggerTxt");
		this.setDate(null);
	}
	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de Client et définissant la structure d'un bateau.
	 * Ce constructeur permet de créer un bateau sans position initiale.
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Immatriculation du bateau
	 * @param modele Modèle du bateau
	 * @param nom Nom du bateau
	 * @param type Type de bateau
	 * @param station Lieu de stationnement du bateau
	 *  @param password mot de passe de connexion
	 */
	public Ship(String immatriculation,String nom, String password,String modele, String type, String station) {
		this(immatriculation,nom,password,modele,type,station,null,"server.LoggerTxt");
		this.setDate(null);
	}
	
	/**
	 * <strong>Description : </strong> Constructeur de la classe héritant de Client et définissant la structure d'un bateau.
	 * Ce constructeur permet de créer un bateau sans nom.
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Immatriculation du bateau
	 * @param modele Modèle du bateau
	 * @param type Type de bateau
	 * @param station Lieu de stationnement du bateau
	 * @param pos Position actuelle du bateau
	 * @param password mot de passe de connexion
	 */
	public Ship(String immatriculation ,String password, String modele, String type, String station, Position pos) {
		this(immatriculation,null,password,modele,type,station,pos,"server.LoggerTxt");
	}
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance date
	 * @author C.Silva, R.Cuinat
	 * @return Date de la dernière mise à jour de position
	 */
	public final synchronized Calendar getDate() {
		return date;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance date
	 * @author C.Silva, R.Cuinat
	 * @param date Date de la dernière mise à jour de position
	 */
	public final synchronized void setDate(Calendar date) {
		Calendar old = null;
		if (this.date != null) {
			old = (Calendar) this.date.clone();
		}
		this.date = date;
		this.updateCenter.updateShipDate(old,this);
	}
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance immatriculation
	 * @author C.Silva, R.Cuinat
	 * @return Immatriculation du bateau
	 */
	public final synchronized String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance nom
	 * @author C.Silva, R.Cuinat
	 * @return Nom du bateau
	 */
	public final synchronized String getNom() {
		return nom;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance nom
	 * @author C.Silva, R.Cuinat
	 * @param nom Nom du bateau
	 */
	public final synchronized void setNom(String nom) {
		String old = this.nom;
		this.nom = nom;
		this.updateCenter.updateShipName(old,this);
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance modele
	 * @author C.Silva, R.Cuinat
	 * @return Modèle du bateau
	 */
	public final synchronized String getModele() {
		return modele;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance modele
	 * @author C.Silva, R.Cuinat
	 * @param modele Modèle du bateau
	 */
	public final synchronized void setModele(String modele) {
		String old = this.modele;
		this.modele = modele;
		this.updateCenter.updateShipModele(old,this);
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance type
	 * @author C.Silva, R.Cuinat
	 * @return Type du bateau
	 */
	public final synchronized String getType() {
		return type;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance type
	 * @author C.Silva, R.Cuinat
	 * @param type Type du bateau
	 */
	public final synchronized void setType(String type) {
		String old = this.type;
		this.type = type;
		this.updateCenter.updateShipType(old,this);
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance endroit_stationnement
	 * @author C.Silva, R.Cuinat
	 * @return Lieu de stationnement du bateau
	 */
	public final synchronized String getEndroit_stationnement() {
		return endroit_stationnement;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance endroit_stationnement
	 * @author C.Silva, R.Cuinat
	 * @param endroit_stationnement Lieu de stationnement du bateau
	 */
	public final synchronized void setEndroit_stationnement(String endroit_stationnement) {
		String old = this.endroit_stationnement;
		this.endroit_stationnement = endroit_stationnement;
		this.updateCenter.updateShipStation(old,this);
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance position
	 * @author C.Silva, R.Cuinat
	 * @return Position courante du bateau
	 */
	public final synchronized Position getPosition() {
		return position;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance position
	 * @author C.Silva, R.Cuinat
	 * @param position Position courante du bateau
	 */
	public final synchronized void setPosition(Position position) {
		Position old = null;
		if (this.position != null) {
			old = new Position(this.position.getCoordsInDegree()[0], this.position.getCoordsInDegree()[1]);
		}
		this.position = position;
		this.updateCenter.updateShipPosition(old, this);
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance state
	 * @author C.Silva, R.Cuinat
	 * @return Etat du bateau
	 */
	public final synchronized ShipState getState() {
		return state;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance state
	 * @author C.Silva, R.Cuinat
	 * @param state Etat du bateau
	 */
	public final synchronized void setState(ShipState state) {
		String old = this.state.toString();
		this.state = state;
		this.updateCenter.updateShipState(old, this);
	}
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance logger
	 * @author C.Silva, R.Cuinat
	 * @return Logger du bateau
	 */
	public final synchronized ILogger getLogger() {
		return logger;
	}
	
	/**
	 * <strong>Description : </strong> Setter de la variable d'instance logger
	 * @author C.Silva, R.Cuinat
	 * @param logger Logger du bateau
	 */
	public final synchronized void setLogger(ILogger logger) {
		this.logger = logger;
	}
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance user
	 * @author C.Silva, R.Cuinat
	 * @return Propriétaire du bateau
	 */
	public final synchronized User getUser() {
		return user;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance user
	 * @author C.Silva, R.Cuinat
	 * @param user Propriétaire du bateau
	 */
	public final synchronized void setUser(User user) {
		this.user = user;
	}
	
	public String toString() {
		return "Bateau immatriculé : "+immatriculation;
	}
	
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance password
	 * @author C.Silva, R.Cuinat
	 * @return Hash du mot de passe du bateau
	 */
	public final synchronized int getPassword() {
		return password;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance password
	 * @author C.Silva, R.Cuinat
	 * @param password Mot de passe du bateau
	 */
	public final synchronized void setPassword(String password) {
		int old = this.password;
		this.password = password.hashCode();
		this.updateCenter.updateShipPassword(old, this);
	}
	
	/**
	 * <strong>Description : </strong> Getter de la variable d'instance updateCenter
	 * @author C.Silva, R.Cuinat
	 * @return updateCenter du bateau
	 */
	public final synchronized IUpdateCenter getUpdateCenter() {
		return updateCenter;
	}

	/**
	 * <strong>Description : </strong> Setter de la variable d'instance updateCenter
	 * @author C.Silva, R.Cuinat
	 * @param updateCenter du bateau
	 */
	public final synchronized void setUpdateCenter(IUpdateCenter updateCenter) {
		this.updateCenter = updateCenter;
	}

	/**
	 * <strong>Description : </strong> Getter de la variable d'instance posLog
	 * @author C.Silva, R.Cuinat
	 * @return Historique des positions en tracking
	 */
	public final synchronized Hashtable<Calendar, Position> getPosLog(){
		return this.posLog;
	}
	
	/**
	 * <strong>Description : </strong> Ajout d'un élément à l'historique des positions en tracking
	 * @author C.Silva, R.Cuinat
	 * @param date Dat de l'élément à ajouter
	 * @param pos Position à ajouter
	 */
	public final synchronized void addPosLog(Calendar date, Position pos) {
		this.posLog.put(date, pos);
		this.updateCenter.shipPosLogAdded(date,pos,this);
	}
	
	/**
	 * <strong>Description : </strong> Renvoie le message à transmettre pour échanger le log de position
	 * @author C.Silva, R.Cuinat
	 * @return Chaine à transmettre
	 */
	public final String flushPosLog() {
		String line = "@log ";
		String line_log = "";
		Hashtable<Calendar,Position> log = this.getPosLog();
		this.posLog = new Hashtable<Calendar,Position>();
		ArrayList<Calendar> keys = new ArrayList<Calendar>(log.keySet());
		Collections.sort(keys);
		for (Calendar date : keys) {
			Position pos = log.get(date);
			line += date.getTime().toString().replaceAll(" ", "_") + " " + String.valueOf(pos.getCoordsInDegree()[0]) + "/" + String.valueOf(pos.getCoordsInDegree()[1]) + " ";
			line_log += date.getTime().toString() + " : " + pos.toString() + System.getProperty("line.separator");
		}
		this.logger.writeLog(line_log);
		this.updateCenter.shipPosLogFlushed(this);
		return line;
	}
}
