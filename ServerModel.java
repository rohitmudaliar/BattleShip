
/**
 * 
 * filename: ServerModel.java
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
import java.util.Vector;

/*
 * This class has all the grids 
 */
public class ServerModel {

	int maxRow;
	int maxCol;
	Vector<int[][]> arrGrid, trGrid;

	public ServerModel(int maxRow, int maxCol) {
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		trGrid = new Vector<int[][]>();
		arrGrid = new Vector<int[][]>();
		trGrid.add(new int[maxRow][maxCol]);
		trGrid.add(new int[maxRow][maxCol]);
		arrGrid.add(new int[maxRow][maxCol]);
		arrGrid.add(new int[maxRow][maxCol]);

	}

	/*
	 * This function returns arrangement grid
	 */
	public int[][] getArrangementGrid(int player) {
		return this.arrGrid.get(player);
	}

	/*
	 * This function returns tracking grid
	 */
	public int[][] getTrackingGrid(int player) {
		return this.trGrid.get(player);
	}

	/*
	 * This function sets arrangement grid
	 */
	public void setArrangementGrid(int player, int r, int c) {
		this.arrGrid.get(player)[r][c] = 1;
	}

	/*
	 * This function sets tracking grid
	 */
	public void setTrackingGridMiss(int player, int r, int c) {
		this.trGrid.get(player)[r][c] = 1;

	}

	/*
	 * This function sets tracking grid
	 */
	public void setTrackingGridHit(int player, int r, int c) {
		this.trGrid.get(player)[r][c] = 2;

	}

	/*
	 * This function returns value in arrangement grid
	 */
	public int getValueInArrangementGrid(int player, int r, int c) {
		return this.arrGrid.get(player)[r][c];
	}

	/*
	 * This function returns value in tracking grid
	 */
	public int getValueInTrackingGrid(int player, int r, int c) {
		return this.trGrid.get(player)[r][c];
	}

}
