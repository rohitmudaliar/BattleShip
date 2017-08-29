
/**
 * 
 * filename: ServerControllerInterface.java
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
 * This is a server interface
 */
public interface ServerControllerInterface extends Remote {
	public void login(PlayerInterface p) throws RemoteException;
}
