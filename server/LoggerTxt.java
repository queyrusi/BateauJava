package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import server.patterns.io.ILogger;
/**
 * <strong>Description : </strong> Classe implémentant l'interface Ilogger
 * @author C.Silva, R.Cuinat
 */
public class LoggerTxt implements ILogger {
	private String immatriculation;
	private File f;
	/**
	 * <strong>Description : </strong>Constructeur de la classe LoggerTxt qui permet de logger des informations dans un fichier au format txt
	 * @author C.Silva, R.Cuinat
	 * @param immat immatriculation du bateau auquel on associe un logger
	 */
	public LoggerTxt(String immat) {
		immatriculation=immat;
		f = new File(this.immatriculation+".txt");
	}
	
	/**
	 * <strong>Description : </strong>Ecrit une ligne dans le fichier
	 * @author C.Silva, R.Cuinat
	 * @param line ligne à écrire
	 */
	@Override
	public final void writeLog(String line) {
	    PrintWriter ecrivain;
	    try {
			ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(f)));
		    ecrivain.println(line);
		    ecrivain.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
