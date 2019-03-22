package server.patterns.update;

import java.util.ArrayList;
import java.util.Calendar;

import server.entities.Position;
import server.entities.Ship;
import server.entities.TrustedContact;
import server.entities.User;

public interface IUpdateCenter {
	
	
	/**
	 * <strong>Description : </strong> Méthode appelé lors de la suppression d'un User
	 * @author C.Silva, R.Cuinat
	 * @param user User à supprimer
	 */
	default public void userRemoved(User user) {}
	
	/**
	 * <strong>Description : </strong> Méthode appelé lors de l'ajout d'un User
	 * @author C.Silva, R.Cuinat
	 * @param valeur Nouveau User
	 */
	default public void userAdded(User valeur) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de l'ajout d'un bateau
	 * @author C.Silva, R.Cuinat
	 * @param valeur Nouveau bateau
	 */
	default public void shipAdded(Ship valeur) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la suppression d'un User
	 * @author C.Silva, R.Cuinat
	 * @param immatriculation Immatriculation du bateau à supprimer
	 */
	default public void shipRemoved(String immatriculation) {}
	
	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification de la liste d'email d'un User
	 * @author C.Silva, R.Cuinat
	 * @param old Ancienne liste d'email du User
	 * @param user User modifié
	 */
	default public void updateUserListEmail(ArrayList<String> old, User user){}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification du bateau d'un User
	 * @author C.Silva, R.Cuinat
	 * @param old Ancien mot de passe du User
	 * @param user User modifié
	 */
	default public void updateUserShip(Ship old, User user) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification du mot de passe d'un User
	 * @author C.Silva, R.Cuinat
	 * @param old Ancien haché du mot de passe du User
	 * @param user User modifié
	 */
	default public void updateUserPassword(int old, User user) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la suppression d'un email d'un User
	 * @author C.Silva, R.Cuinat
	 * @param email Email supprimer
	 * @param user User concerné
	 */
	default public void userEmailRemoved(String email, User user) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de l'ajout d'un email d'un User
	 * @author C.Silva, R.Cuinat
	 * @param email Email ajouté
	 * @param user User concerné
	 */
	default public void userEmailAdded(String email, User user) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de l'ajout d'un ami d'un User
	 * @author C.Silva, R.Cuinat
	 * @param ami ami ajouté
	 * @param user User concerné
	 */
	default public void userAmiAdded(TrustedContact ami, User user) {}
	
	/**
	 * <strong>Description : </strong> Méthode appelé lors de la suppression d'un ami d'un User
	 * @author C.Silva, R.Cuinat
	 * @param ami Ami supprimé
	 * @param user User concerné
	 */
	default public void userAmiRemoved(TrustedContact ami, User user) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification de la liste d'ami d'un User
	 * @author C.Silva, R.Cuinat
	 * @param old Ancienne liste d'ami du User
	 * @param user User modifié
	 */
	default public void updateUserListAmis(ArrayList<TrustedContact> old, User user) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification de l'adresse d'un User
	 * @author C.Silva, R.Cuinat
	 * @param old Ancienne adresse du User
	 * @param user User modifié
	 */
	default public void updateUserAdresse(String old, User user) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification du nom d'un User
	 * @author C.Silva, R.Cuinat
	 * @param old Ancien nom du User
	 * @param user User modifié
	 */
	default public void updateUserName(String old, User user) {}
	
	/**
	 * <strong>Description : </strong> Méthode appelé lors de la sauvegarde des logs de position d'un bateau sur le disque
	 * @author C.Silva, R.Cuinat
	 * @param ship Bateau concerné
	 */
	default public void shipPosLogFlushed(Ship ship) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de l'ajout d'un log de position à un bateau
	 * @author C.Silva, R.Cuinat
	 * @param date Date du log
	 * @param pos Position du log
	 * @param ship Bateau concerné
	 */
	default public void shipPosLogAdded(Calendar date, Position pos, Ship ship) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification du mot de passe d'un Bateau
	 * @author C.Silva, R.Cuinat
	 * @param old Ancien haché du mot de passe du Bateau
	 * @param ship Bateau modifié
	 */
	default public void updateShipPassword(int old, Ship ship) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification de l'état d'un Bateau
	 * @author C.Silva, R.Cuinat
	 * @param old Ancien état du Bateau
	 * @param ship Bateau modifié
	 */
	default public void updateShipState(String old, Ship ship) {
		if (old.compareTo("Tracking") != 0 & ship.getState().toString().compareTo("Tracking") == 0) {
			if (ship.getUser() != null) {
				User user = ship.getUser();
				if (user.getListe_emails() != null) {
					for (String email : user.getListe_emails())System.out.println("Alerte Vol envoyé à l'utilisateur " + user.getId() + " sur l'email '" + email  + "' .");
				}
				if (user.getListe_amis() != null) {
					for (TrustedContact TC : user.getListe_amis()) {
						if (TC.getEmail() != null) System.out.println("Alerte Vol envoyé à la personne de confiance " + TC.getNom() + " de l'utilisateur " + user.getId() + " sur l'email \'" + TC.getEmail() + "\' .");
						if (TC.getNum_tel() > 0) System.out.println("Alerte Vol envoyé à la personne de confiance " + TC.getNom() + " de l'utilisateur " + user.getId() + " sur le numéro \'0" + String.valueOf(TC.getNum_tel()) + "\' .");
					}
				}
			}
		}
	}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification de la position d'un Bateau
	 * @author C.Silva, R.Cuinat
	 * @param old Ancienne position du Bateau
	 * @param ship Bateau modifié
	 */
	default public void updateShipPosition(Position old, Ship ship) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification du lieu de stationnement d'un Bateau
	 * @author C.Silva, R.Cuinat
	 * @param old Ancien lieu de stationnement du Bateau
	 * @param ship Bateau modifié
	 */
	default public void updateShipStation(String old, Ship ship) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification du type d'un Bateau
	 * @author C.Silva, R.Cuinat
	 * @param old Ancien type du Bateau
	 * @param ship Bateau modifié
	 */
	default public void updateShipType(String old, Ship ship) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification du modèle d'un Bateau
	 * @author C.Silva, R.Cuinat
	 * @param old Ancien modèle du Bateau
	 * @param ship Bateau modifié
	 */
	default public void updateShipModele(String old, Ship ship) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification du nom d'un Bateau
	 * @author C.Silva, R.Cuinat
	 * @param old Ancien nom du Bateau
	 * @param ship Bateau modifié
	 */
	default public void updateShipName(String old, Ship ship) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification de la date de la dernière position reçu d'un Bateau
	 * @author C.Silva, R.Cuinat
	 * @param old Ancienne date sauvegardée
	 * @param ship Bateau modifié
	 */
	default public void updateShipDate(Calendar old, Ship ship) {}

	/**
	 * <strong>Description : </strong> Méthode appelé lors de la modification de l'état de connexion d'un User
	 * @author C.Silva, R.Cuinat
	 * @param old Ancien état
	 * @param ship User concerné
	 */
	default public void updateUserOnline(boolean old, User user) {};
}
