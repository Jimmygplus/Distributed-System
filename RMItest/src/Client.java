import java.util.*;
import java.rmi.*;
import java.net.*;
public class timeClient {
	public static void main(String args[]) {
		if (args.length == 0 || !args[0].startsWith("rmi:")){{
			System.err.println("Usage: java timeClient rmi://localhost:0000/myTimeServer");
			return;
		}
		long lt = 0;
		try {
			Object o = Naming.lookup(args[0]);
			timeServer ts = (timeServer) o;
			lt = ts.getTime();
 		} catch (MalformedURLException ex) {
 			System.err.println(args[0] + "is not a valid RMI URL");
 		} catch (RemoteException ex) {
 			System.err.println("Remote object threw exception" + ex);
 		} catch (NotBoundException ex) {
 			System.err.println("Could not find the requested remote object on the server");
 		}
		Date date = new Date(System.currentTimeMillis());
		System.out.println("The date now is " + date);
		System.out.println("The time now is " + lt); 
	}
}
