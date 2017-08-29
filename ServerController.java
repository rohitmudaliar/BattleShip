
/**
 * 
 * filename: ServerController.java
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
import java.util.Vector;

@SuppressWarnings("serial")
/*
 * This is the main class of Server which does the fleet arrangement and plays
 * the game
 */
public class ServerController extends UnicastRemoteObject implements ServerControllerInterface {

	Vector<PlayerInterface> players;
	ServerModel sm;
	int maxRow;
	int maxCol;
	int[] temp = new int[2];

	private final int Carrier = 5;
	private final int Battleship = 4;
	private final int Cruiser = 3;
	private final int Destroyer = 2;

	public ServerController(int maxRow, int maxCol) throws Exception {
		Naming.rebind("rmi://localhost:2000/ss", this);
		System.out.println("Binding done on server");

		this.maxRow = maxRow;
		this.maxCol = maxCol;
		sm = new ServerModel(this.maxRow, this.maxCol);
		players = new Vector<PlayerInterface>();
		while (players.size() < 2) {
			Thread.sleep(100);
		}
		players.get(0).init(maxRow, maxCol);
		players.get(1).init(maxRow, maxCol);

		for (int i = 0; i < 2; i++) {
			this.fleetArrangement(i);
		}
		this.startGame();

	}

	/*
	 * This method returns the size of the ship
	 */
	public int getSizeOfShip(String s) {
		if (s.charAt(0) == 'a') {
			return getCarrier();
		} else if (s.charAt(0) == 'b') {
			return getBattleship();
		} else if (s.charAt(0) == 'c') {
			return getCruiser();
		} else if (s.charAt(0) == 'd') {
			return getDestroyer();
		} else
			return 0;
	}

	/*
	 * This method returns the size of the Carrier ship
	 */
	public int getCarrier() {
		return Carrier;
	}

	/*
	 * This method returns the size of the Battleship
	 */
	public int getBattleship() {
		return Battleship;
	}

	/*
	 * This method returns the size of the Cruiser ship
	 */
	public int getCruiser() {
		return Cruiser;
	}

	/*
	 * This method returns the size of the Destroyer ship
	 */
	public int getDestroyer() {
		return Destroyer;
	}

	/**
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 * 
	 * 
	 */
	public static void main(String[] args) throws Exception {

		new ServerController(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

	}

	/*
	 * This method takes the references of the players
	 */
	public void login(PlayerInterface pc) throws RemoteException {
		players.add(pc);
	}

	public boolean isAlive() {
		if (players.size() < 2)
			return false;
		else
			return true;
	}

	/*
	 * This method gets the fleet arrangement
	 */
	public void fleetArrangement(int player) throws Exception {
		String input[] = new String[4];
		for (int i = 0; i < 4; i++) {
			input = players.get(player).getFleetInput();
			String valid = validationFleet(input, player);
			if (valid.equals("allValid")) {

				if (input[3].equals("h")) {
					for (int j = 0; j < this.getSizeOfShip(input[0]); j++) {
						sm.setArrangementGrid(player, Integer.parseInt(input[1]), j + Integer.parseInt(input[2]));
					}

					if (input[3].equals("v")) {
						for (int j = 0; j < this.getSizeOfShip(input[0]); j++) {
							sm.setArrangementGrid(player, j + Integer.parseInt(input[1]), Integer.parseInt(input[2]));
						}

					}
				}

			} else
				players.get(player).errorDisplay(valid);

		}
		players.get(player).displayGridArr(sm.getArrangementGrid(player));
	}

	/*
	 * This method validates the fleet arrangement
	 */
	public String validationFleet(String[] input, int player) {
		int row = Integer.parseInt(input[1]);
		int col = Integer.parseInt(input[2]);
		String orientation = input[3];
		String valid = "invalid";
		int[][] tempArrangement = sm.arrGrid.get(player);
		if (row >= 0 && col >= 0 && row <= sm.maxRow && col <= sm.maxCol) {
			if (orientation.charAt(0) == 'v' || orientation.charAt(0) == 'h') {
				if (orientation.charAt(0) == 'v') {
					for (int i = row; i < this.getSizeOfShip(input[0]) + row; i++) {
						if (tempArrangement[i][col] == 0) {
							valid = "allValid";
						} else
							valid = "overlapping";
					}
				} else {
					for (int i = col; i < this.getSizeOfShip(input[0]) + col; i++) {
						if (tempArrangement[row][i] == 0) {
							valid = "allValid";
						} else
							valid = "overlapping";
					}
				}
			} else {
				valid = "orientation";
			}
		} else {
			valid = "rowCol";
		}
		return valid;
	}

	/*
	 * This method starts the game
	 */
	public void startGame() throws Exception {
		int value;

		int[] count = { 14, 14 };
		int gameOver = 0;
		int p = 0;
		do {

			while (true) {
				if (!isAlive()) {
					if (players.size() == 1)
						players.get(0).displayWon();
					return;
				}
				players.get(p).displayGridTrack(sm.getTrackingGrid(p));

				String[] input = players.get(p).nextMove();
				int row = Integer.parseInt(input[0]);
				int col = Integer.parseInt(input[1]);

				value = sm.getValueInArrangementGrid((p + 1) % 2, row, col);
				if (value == 0) {
					sm.setTrackingGridMiss(p, row, col);
					players.get(p).display("It's a miss");
					p = (p + 1) % 2;
				} else if (value == 1) {
					sm.setTrackingGridHit(p, row, col);
					count[p]--;

					players.get(p).display("It's a hit");

					if (count[p] == 0) {
						gameOver = 1;
						players.get(p).displayWon();
						players.get((p + 1) % 2).displayLost();
						return;
					}
				} else if (value == 1 && (sm.getValueInTrackingGrid(p, row, col)) != 0) {
					players.get(p).display("Invalid Move");
				}
			}

		} while (gameOver != 1);
	}

}
