
/**
 * 
 * filename: PlayerInterfce.java
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

/*
 * This is a player interface
 */
public interface PlayerInterface extends Remote {
	public String[] getFleetInput() throws RemoteException;

	public String[] nextMove() throws RemoteException;

	public void errorDisplay(String valid) throws RemoteException;

	public int[] getMaxRowMaxCol() throws RemoteException;

	public void displayGridArr(int[][] arrangementGrid) throws RemoteException;

	public void init(int maxRow, int maxCol) throws RemoteException;

	public void displayGridTrack(int[][] trackingGrid) throws RemoteException;

	public void displayWon() throws RemoteException;

	public void displayLost() throws RemoteException;

	public void display(String string) throws RemoteException;;

}
