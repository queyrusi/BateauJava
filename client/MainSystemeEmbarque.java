/**
 * 
 */
package client;

/**
 * @author chenqun
 *
 */
public class MainSystemeEmbarque {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SystemeEmbarque newSys =  new SystemeEmbarque("LAPTOP-D7S9B5VD", 6666, "tata");  // TODO : toto aussi ?
		
		System.out.println("Done ship");
		newSys.connecterAuServeur();
		
		while (true) {}
	}

}
