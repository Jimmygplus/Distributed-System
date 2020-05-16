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