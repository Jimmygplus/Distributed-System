/**
 * 
 */
package Client;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
/**
 * @author Jimmy
 *
 */
import java.rmi.server.UnicastRemoteObject;
public class Client extends UnicastRemoteObject implements ClientInterface {

	private static final long serialVersionUID = -389992449104276663L;
	
	protected Client() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	- name: String
	- employeeGUI: ClientGUI
	- serverIF: serverInterface
	 */
	

	/**
	 * @param args
	 */
}