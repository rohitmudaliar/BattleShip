/**
 * 
 * filename: PlayerMain.java
 * 
 * version: 1.0 11/14/2016
 * 
 * @author Parvathi Nair
 * 
 * @author Rohit Mudaliar
 * 
 *         revisions: Initial version
 *
 */
/*
 * This is the main class of the client
 */
public class PlayerMain {
	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 * 
	 * 
	 */
	public static void main(String[] args) throws Exception {
		PlayerView v = new PlayerView();
		new PlayerController(v);

	}

}
