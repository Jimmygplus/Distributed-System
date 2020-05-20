package Client;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;

import Server.ServerInterface;

public class Client extends UnicastRemoteObject implements ClientInterface {

    private static final long serialVersionUID = -389992449104276663L;
    ClientGUI clientGUI;
    ClientGUIAfterLogin clientGUIAfterLogin;
    private String name;
    private String clientServiceName;
    public ServerInterface ServerInterface;
    protected boolean connectionProblem = false;

    public Client(ClientGUI aChatGUI, ClientGUIAfterLogin newClientGUI, String userName) throws RemoteException {
        super();
        this.clientGUI = aChatGUI;
        this.clientGUIAfterLogin = newClientGUI;
        this.name = userName;
        this.clientServiceName = "ClientListenService_" + userName;
    }

    public void connectToServer() throws RemoteException {

        String[] details = {name, "localhost", clientServiceName};

        try {
            Naming.rebind("rmi://" + "localhost" + "/" + clientServiceName, this);
            ServerInterface = (ServerInterface) Naming.lookup("rmi://" + "localhost" + "/" + "WarehouseService");
        } catch (ConnectException e) {
            JOptionPane.showMessageDialog(
                    clientGUI.frame, "The server is unavailable\ntry later",
                    "Connection problem", JOptionPane.ERROR_MESSAGE);
            connectionProblem = true;
            e.printStackTrace();
        } catch (NotBoundException | MalformedURLException me) {
            connectionProblem = true;
            me.printStackTrace();
        }
        if (!connectionProblem) {
            try {
                //serverIF.passIDentity(this.ref);//now redundant ??
                ServerInterface.registerListener(details);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Client Listen RMI Server is running good...\n");
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
        System.out.println(message.length());
        clientGUIAfterLogin.textArea.append(message);
        //make the gui display the last appended text, ie scroll to bottom
        clientGUIAfterLogin.textArea.setCaretPosition(clientGUIAfterLogin.textArea.getDocument().getLength());
    }

    @Override
    public void updateEmployeeList(String[] currentEmployees) throws RemoteException {

        System.out.println("Current Employees number : " + currentEmployees.length);
        /**/
        if (currentEmployees.length < 2) {
            clientGUIAfterLogin.privatesendButton.setEnabled(false);
        } else {
            clientGUIAfterLogin.privatesendButton.setEnabled(true);
        }

        clientGUIAfterLogin.listModel.removeAllElements();

        for (String s : currentEmployees) {
            System.out.println(s);
            clientGUIAfterLogin.listModel.addElement(s);
        }

        clientGUIAfterLogin.onlineEmplouee_panel.repaint();
        clientGUIAfterLogin.onlineEmplouee_panel.revalidate();

    }


    protected void sendToALl(String chatMessage) throws RemoteException {
        this.ServerInterface.updateChattingList(name, chatMessage);
        System.out.println("+");
    }

    public String getName() throws RemoteException {
        return name;
    }

    public void sendPrivate(String message, int[] selectedIndex) throws RemoteException {
        this.ServerInterface.sendPrivate(name, message, selectedIndex);
    }

    public void getOrder(int type, int amount) throws RemoteException {
        ServerInterface.getOrder(name, type, amount);

    }

    public void receiveMnP(int numberofM, int numberofP) throws RemoteException {
        // change gui
        this.clientGUIAfterLogin.textField.setText(" "+numberofM);
        this.clientGUIAfterLogin.textField_1.setText(" "+numberofP);

        clientGUIAfterLogin.Currentquantity_panel.repaint();
        clientGUIAfterLogin.Currentquantity_panel.revalidate();
    }

    public void receiveHistory(String newRecord) throws RemoteException {

        this.clientGUIAfterLogin.textArea_2.append(newRecord+"\n");

        clientGUIAfterLogin.onlineEmplouee_panel.repaint();
        clientGUIAfterLogin.onlineEmplouee_panel.revalidate();

    }
}