package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    public void updateChattingList(String name, String message) throws RemoteException;

    public void registerListener(String[] hostDetails) throws RemoteException;

    public void offline(String userName) throws RemoteException;

    public void sendPrivate(String privateMessage, int[] selectedIndex) throws RemoteException;

    public String[] loadHistory();

    public int getOrder(String employeeName, int type, int amount);

    public int getMaterial();

    public int getProduct();

}