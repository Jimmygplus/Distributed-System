/**
 *
 */
package Server;

import Client.Client;
import Client.ClientInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server extends UnicastRemoteObject implements ServerInterface {
    private static final long serialVersionUID = 1L;
    private Vector<ClientInterface> employees;
    private static ReentrantReadWriteLock materialLock;
    private static ReentrantReadWriteLock productLock;
    private static ReentrantReadWriteLock historyLock;


    public Server() throws RemoteException {
        super();
        employees = new Vector<ClientInterface>(30, 1);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        startRMIRegistry();
        String hostName = "localhost";
        String serviceName = "WarehouseService";

        if (args.length == 2) {
            hostName = args[0];
            serviceName = args[1];
        }

        try {
            ServerInterface hello = new Server();
            Naming.rebind("rmi://" + hostName + "/" + serviceName, hello);
            System.out.println("Warehouse helper is running...");
        } catch (Exception e) {
            System.out.println("Server Not good");
        }

    }

    public static void startRMIRegistry() {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI Server OK");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(String newMsg) {
        for (ClientInterface c : employees) {
            try {
                c.receiveMessage(newMsg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void registerEmployee(String[] hostDetails) {
        try {
            ClientInterface nextClient = (ClientInterface) Naming.lookup("rmi://" + hostDetails[1] + "/" + hostDetails[2]);

            employees.addElement(nextClient);

            nextClient.receiveMessage("[Boss] : " + hostDetails[0] + ", U R welcomed\n");

            sendToAll("[Boss] : " + hostDetails[0] + " is working.\n");

            updateEmployeeList();
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }

    }

    public void sendPrivate(String privateMessage, int[] privateGroup) {
        ClientInterface individual;
        for (int i : privateGroup) {
            individual = employees.elementAt(i);
            try {
                individual.receiveMessage(privateMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private String[] getEmployeeList() throws RemoteException {
        String[] allEmployees = new String[employees.size()];
        for (int i = 0; i < allEmployees.length; i++) {
            allEmployees[i] = employees.elementAt(i).getName();
        }
        return allEmployees;
    }

    public void updateChattingList(String name, String context) {
        String message = name + ":  " + context + "\n";
        sendToAll(message);
    }

    @Override
    public void registerListener(String[] hostDetails) throws RemoteException {
        // TODO Auto-generated method stub
        registerEmployee(hostDetails);
    }

    @Override
    public void offline(String userName) throws RemoteException {
        for (ClientInterface c : employees) {
            if (c.getName().equals(userName)) {
                System.out.println(userName + ":  I'm leaving");
                employees.remove(c);
                break;
            }
        }
        if (!employees.isEmpty()) {
            updateEmployeeList();
        }
    }

    private String[] updateEmployeeList() throws RemoteException {

        String[] allUsers = new String[employees.size()];

        for (int i = 0; i < allUsers.length; i++) {
            allUsers[i] = employees.elementAt(i).getName();
        }

        return allUsers;
    }

    public int getOrder(String EmploeeName, int type, int amount) {

        String typeName;

        if (type == 0) {
            changeMaterial(amount);
            typeName = "material";
        } else if (type == 1) {
            changeProduct(amount);
            typeName = "product";
        } else {
            return 0;
        }
        int numberofM = getMaterial();
        int numberofP = getProduct();
        updateMnP(numberofM, numberofP);
        updateHistory(" " + EmploeeName + " offered " + typeName + " " + amount);
        return 1;
    }

    public String[] loadHistory() {
        // load text
        historyLock.readLock().lock();
        String temp = RWtxt.readTxt("his.txt");
        historyLock.readLock().unlock();

        //turn String into string array
        String[] allHistory = temp.split("/");
        return allHistory;
    }

    private void updateHistory(String newRecord) {
        // record to txt
        historyLock.writeLock().lock();
        String temp = RWtxt.readTxt("his.txt");
        RWtxt.writeTxt("his.txt", temp + newRecord + "/");
        historyLock.writeLock().unlock();
        // tell everyone
        for (ClientInterface c : employees) {
            try {
                c.receiveHistory(newRecord);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getMaterial() {

        materialLock.readLock().lock();
        String material = RWtxt.readTxt("material.txt");
        int materialNumber = Integer.parseInt(material);
        materialLock.readLock().unlock();
        return materialNumber;

    }

    private void changeMaterial(int num) {
        materialLock.writeLock().lock();
        String material = RWtxt.readTxt("material.txt");
        int materialNumber = Integer.parseInt(material);
        materialNumber += num;
        RWtxt.writeTxt("material.txt", String.valueOf(materialNumber));
        materialLock.writeLock().unlock();
    }

    public int getProduct() {
        productLock.readLock().lock();
        String product = RWtxt.readTxt("product.txt");
        int productNumber = Integer.parseInt(product);
        materialLock.readLock().unlock();
        productLock.readLock().unlock();
        return productNumber;
    }

    private void changeProduct(int num) {
        productLock.writeLock().lock();
        String product = RWtxt.readTxt("product.txt");
        int productNumber = Integer.parseInt(product);
        productNumber += num;
        RWtxt.writeTxt("material.txt", String.valueOf(productNumber));
        productLock.writeLock().lock();
    }

    private void updateMnP(int numberofM, int numberofP) {
        for (ClientInterface c : employees) {
            try {
                c.recreceiveMnP(numberofM, numberofP);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean matchPassword(String password) {
        String realPassword = "123456";
        boolean find;
        if (password.equals(realPassword)) {
            find = true;
        } else {
            find = false;
        }
        return find;
    }


}
