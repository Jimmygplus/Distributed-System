/**
 * 
 */
package Server;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import Client.ClientInterface;
/**
 * @author Jimmy
 *
 */
public class Server extends UnicastRemoteObject implements ServerInterface {
	private static final long serialVersionUID = 1L;
	private Vector<ClientInterface> employees;
	private int material;
	private int product;
	
	protected Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void startRMIRegistry() {
		
	}
	
	@SuppressWarnings("unused")
	private void registerEmployee(String[] hostDetails) {
		
	}
	
	public void offline(String[] userName){
		
	}
	
	public void sendToAll(String newMsg){
		
	}
	
	public void sendPrivate(String privateMessage, int[] privateGroup) {
		
	}
	public String[] getEmployeeList() {
		return null;
	}
	public void updateEmployeeList() {
		
	}
	public void updateChattingList() {
		
	}
	public int getMaterial() {
		return 0;
	}
	public int getProduct() {
		return 0;
	}
	public void loadHistory() {
		
	}
	public void updateHistory() {
		
	}
	public void changeMaterial(int num) {
		
	}
	public void changeProduct(int num) {
		
	}

	public void getOrder(int a, int b) {
		
	}

	@Override
	public void registerListener(String[] hostDetails) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void offline(String userName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadHistroy() {
		// TODO Auto-generated method stub
		
	}

}
