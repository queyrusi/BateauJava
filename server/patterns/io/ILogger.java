package server.patterns.io;

/**
 * <strong>Description : </strong> Interface pour logger des informations
 * @author C.Silva, R.Cuinat
 */
public interface ILogger {
	/**
	 * <strong>Description : </strong>Ecrit une ligne dans un fichier
	 * @author C.Silva, R.Cuinat
	 * @param line ligne à écrire
	 */
	public void writeLog(String line);

}
