/**
 * 
 */
package Server;
import java.rmi.*;;

/**
 * @author Jimmy
 *
 */
public interface ServerInterface extends Remote {

	public void updateChattingList();
	
	public void registerListener(String[] hostDetails)throws RemoteException;
	
	public void offline(String userName)throws RemoteException;
	
	public void sendPrivate(String privateMessage, int[] privateGroup)throws RemoteException;
	
	public void loadHistroy();
	
	public void getOrder(int a, int b);
	
	public int getMaterial();
	
	public int getProduct();

}

/*
 * 
 * 	+ updateChattingList(): void
	+ registerListener(String[]): void
	+ offline(String): void
	+ sendPrivate(String, int[]): void
	+ loadHistory(): void
	+ getOrder(int, int): void
	+ getMaterial(): int
	+ getProduct(): int
*/
