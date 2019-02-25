package server.patterns.io;

import java.io.OutputStream;
/**
 * <strong>Description : </strong> Interface pour logger des informations
 * @author C.Silva, R.Cuinat
 */
public interface ILogger {
	/**
	 * <strong>Description : </strong>Ecrit une ligne dans le flux de sortie
	 * @author C.Silva, R.Cuinat
	 * @param line ligne à écrire
	 * @param oStream flux d'écriture
	 */
	public void writeLog(String line, OutputStream oStream);

}
