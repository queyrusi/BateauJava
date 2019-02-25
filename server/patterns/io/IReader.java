package server.patterns.io;

import java.io.InputStream;
/**
 * <strong>Description : </strong>Interface pour lire des informations
 * @author C.Silva, R.Cuinat
 */
public interface IReader {
	/**
	 * <strong>Description : </strong>Lis la premi�re ligne du InputStream et v�rifie la validit� du code ('@User' ou '@Ship'). Lance le gestionnaire associ�
	 * @author C.Silva, R.Cuinat
	 * @param iStream Stream de lecture
	 * @return ligne lue
	 */
	public String readLog(InputStream iStream);

}
