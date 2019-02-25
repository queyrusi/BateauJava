package server.patterns.io;

import java.io.InputStream;
/**
 * <strong>Description : </strong>Interface pour lire des informations
 * @author C.Silva, R.Cuinat
 */
public interface IReader {
	/**
	 * <strong>Description : </strong>Lis la première ligne du InputStream et vérifie la validité du code ('@User' ou '@Ship'). Lance le gestionnaire associé
	 * @author C.Silva, R.Cuinat
	 * @param iStream Stream de lecture
	 * @return ligne lue
	 */
	public String readLog(InputStream iStream);

}
