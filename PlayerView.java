
/**
 * 
 * filename: PlayerView.java
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
import java.util.Scanner;

/*
 * This is the view class
 */
public class PlayerView {
	int maxRow, maxCol;
	Scanner sc = new Scanner(System.in);

	/*
	 * This method displays the string
	 */
	public void display(String s) {
		System.out.println(s);
	}

	/*
	 * This method reads the string
	 */
	public String read() {
		return sc.next();
	}

	/*
	 * This method sets maxRow
	 */
	public void setMaxRow() {
		this.maxRow = sc.nextInt();
	}

	/*
	 * This method sets maxCol
	 */
	public void setMaxCol() {
		this.maxCol = sc.nextInt();
	}

	/*
	 * This method gets maxRow
	 */
	public int getMaxRow() {
		return this.maxRow;
	}

	/*
	 * This method gets maxCol
	 */
	public int getMaxCol() {
		return this.maxCol;
	}

	/*
	 * This method displays error message
	 */
	public void displayError(String valid) {
		if (valid.equals("orientation")) {
			System.out.println("Invalid character");
		} else if (valid.equals("rowCol")) {
			System.out.println("invalid row or column");
		} else
			System.out.println("Overlapping coordinates");
	}

	/*
	 * This method displays arrangement grid
	 */
	public void displayGridArr(int[][] tempGridArr) {
		for (int i = 0; i < this.maxRow; i++) {
			for (int j = 0; j < this.maxCol; j++) {
				System.out.print(tempGridArr[i][j]);
			}
			System.out.println();
		}
	}

	/*
	 * This method initializes maxRow and maxCol
	 */
	public void init(int r, int c) {
		this.maxRow = r;
		this.maxCol = c;
	}

	/*
	 * This method displays the tracking Grid
	 */
	public void displayGridTrack(int[][] tempArrtrack) {

		System.out.println("Your tracking grid:");
		System.out.println();
		for (int i = 0; i < this.maxRow; i++) {
			for (int j = 0; j < this.maxCol; j++) {
				System.out.print(tempArrtrack[i][j]);
			}
			System.out.println();
		}

	}

	/*
	 * This method displays won message
	 */
	public void displayWon() {
		System.out.println("You won");
	}

	/*
	 * This method displays lost meassage
	 */
	public void displayLost() {
		System.out.println("You lost");

	}

}
