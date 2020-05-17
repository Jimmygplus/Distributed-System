package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    public void receiveMessage(String message) throws RemoteException;

    public void updateEmployeeList(String[] currentEmployees) throws RemoteException;

    public String getName() throws RemoteException;

    public void updateHistory() throws RemoteException;

    public void recreceiveMnP(int numberofM, int numberofP) throws RemoteException;

    public void receiveHistory(String newRecord)  throws RemoteException;

}