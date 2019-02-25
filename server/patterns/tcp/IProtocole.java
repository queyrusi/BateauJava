package server.patterns.tcp;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <strong>Description : </strong>Interface d�finissant le protocole du serveur
 * @author C.Silva, R.Cuinat
 */
public interface IProtocole {
	/**
	 * <strong>Description : </strong>M�thode appel�e dans le thread de connexion
	 * @author C.Silva, R.Cuinat
	 * @param aContext Contexte du serveur. Impl�mente l'interface IContext
	 * @param anInputStream Stream d'entr�e de la connexion
	 * @param anOutputStream Stream de sortie de la connexion
	 */
	public void execute( IContext aContext , InputStream anInputStream , OutputStream anOutputStream );
	
}
