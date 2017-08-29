
/**
 * 
 * filename: PlayerController.java
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
import java.rmi.*;
import java.rmi.server.*;

/*
 * This class is the player controller 
 */
@SuppressWarnings("serial")
public class PlayerController extends UnicastRemoteObject implements PlayerInterface {

	PlayerView v;

	public PlayerController(PlayerView v) throws Exception {
		this.v = v;
		ServerControllerInterface stub = (ServerControllerInterface) Naming.lookup("rmi://localhost:2000/ss");

		stub.login(this);
	}

	/*
	 * This method gets maxRow and maxCol
	 */
	public int[] getMaxRowMaxCol() {
		int[] rc = new int[2];
		System.out.println("Enter the max row");
		v.setMaxRow();
		rc[0] = v.getMaxRow();

		System.out.println("Enter the max col");
		v.setMaxCol();
		rc[1] = v.getMaxCol();

		return rc;
	}

	/*
	 * This method takes input for fleet arrangement
	 */
	public String[] getFleetInput() {

		String inputForFleet[] = new String[4];

		System.out.println("Enter the Ship type(1.'a' for Carrier Ship   2. 'b' for Battle Ship   "
				+ "3. 'c' for Cruiser Ship  and 4. 'd' for Destroyer Ship , row, column and orientation");
		for (int i = 0; i < 4; i++) {
			inputForFleet[i] = v.read(); // sc.next();
		}
		return inputForFleet;

	}

	/*
	 * This method takes input for nextMove
	 */
	public String[] nextMove() {

		String[] inputForGame = new String[2];

		System.out.println("Enter the row and column where you want to hit");
		for (int j = 0; j < 2; j++)
			inputForGame[j] = v.read();
		return inputForGame;

	}

	/*
	 * This method calls view for display
	 */
	public void errorDisplay(String valid) {
		v.displayError(valid);

	}

	/*
	 * This method calls view for display
	 */
	public void displayGridArr(int[][] tempGridArr) {
		v.displayGridArr(tempGridArr);
	}

	/*
	 * This method initializes maxRow and maxCol in view
	 */
	public void init(int r, int c) {
		v.init(r, c);
	}

	/*
	 * This method calls view for display
	 */
	public void displayGridTrack(int[][] tempArrtrack) {
		v.displayGridTrack(tempArrtrack);
	}

	/*
	 * This method calls view for display
	 */
	public void displayWon() {
		v.displayWon();
	}

	/*
	 * This method calls view for display
	 */
	public void displayLost() {
		v.displayLost();
	}

	/*
	 * This method calls view for display
	 */
	public void display(String s) {
		v.display(s);
	}
}
