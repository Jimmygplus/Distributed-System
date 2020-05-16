package Server;

import Client.Client;
import Client.ClientInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.util.Vector;


public class Server extends UnicastRemoteObject implements ServerInterface {
    private static final long serialVersionUID = 1L;
    private Vector<ClientInterface> employees;
    private int material;
    private int product;

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

    @SuppressWarnings("unused")
    private void registerEmployee(String[] hostDetails) {
        try {
            ClientInterface nextClient = (ClientInterface) Naming.lookup("rmi://" + details[1] + "/" + details[2]);

            employees.addElement(nextClient);

            nextClient.messageFromServer("[Boss] : " + details[0] + ", U R welcomed\n");

            sendToAll("[Boss] : " + details[0] + " is working.\n");

            updateEmployeeList();
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }

    }

    public void sendToAll(String newMsg) {
        for (ClientInterface c : employees) {
            try {
                c.messageFromServer(newMsg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendPrivate(String privateMessage, int[] privateGroup) {
        ClientInterface individual;
        for (int i : privateGroup) {
            individual = employees.elementAt(i);
            individual.messageFromServer(privateMessage);
        }
    }

    private String[] getEmployeeList() {
        String[] allEmployees = new String[employees.size()];
        for (int i = 0; i < allEmployees.length; i++) {
            allEmployees[i] = employees.elementAt(i).getName();
        }
        return allEmployees;
    }

    private String[] updateEmployeeList() {

        String[] allUsers = new String[employees.size()];

        for (int i = 0; i < allUsers.length; i++) {
            allUsers[i] = employees.elementAt(i).getName();
        }

        return allUsers;
    }

    public void updateChattingList(String name, String context) {
        String message = name + ":  " + context + "\n";
        sendToAll(message);
    }

    public int getMaterial() {

        return 0;
    }

    public int getProduct() {

        return 0;
    }

    public void loadHistory() {

    }

    private void updateHistory() {

    }

    private void changeMaterial(int num) {

    }

    private void changeProduct(int num) {

    }

    public int getOrder(int a, int b) {
        return 0;
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

    @Override
    public void loadHistroy() {

    }

}
