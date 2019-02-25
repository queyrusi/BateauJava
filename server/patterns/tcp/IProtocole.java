package server.patterns.tcp;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <strong>Description : </strong>Interface définissant le protocole du serveur
 * @author C.Silva, R.Cuinat
 */
public interface IProtocole {
	/**
	 * <strong>Description : </strong>Méthode appelée dans le thread de connexion
	 * @author C.Silva, R.Cuinat
	 * @param aContext Contexte du serveur. Implémente l'interface IContext
	 * @param anInputStream Stream d'entrée de la connexion
	 * @param anOutputStream Stream de sortie de la connexion
	 */
	public void execute( IContext aContext , InputStream anInputStream , OutputStream anOutputStream );
	
}
