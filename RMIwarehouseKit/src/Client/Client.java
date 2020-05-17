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
    //private String hostName = "localhost";
    //private String serviceName = "GroupChatService";
    private String clientServiceName;
    protected ServerInterface ServerInterface;
    protected boolean connectionProblem = false;

    /**
     * class constructor,
     * note may also use an overloaded constructor with
     * a port no passed in argument to super
     *
     * @throws RemoteException
     */

    public Client(ClientGUI aChatGUI, ClientGUIAfterLogin newClientGUI, String userName) throws RemoteException {
        super();
        this.clientGUI = aChatGUI;
        this.clientGUIAfterLogin = newClientGUI;
        this.name = userName;
        this.clientServiceName = "ClientListenService_" + userName;
    }

    /**
     * Register our own listening service/interface
     * lookup the server RMI interface, then send our details
     *
     * @throws RemoteException
     */

    public void connectToServer() throws RemoteException {

        String[] details = {name, "localhost", clientServiceName};

        try {
            Naming.rebind("rmi://" + "localhost" + "/" + clientServiceName, this);
            ServerInterface = (ServerInterface) Naming.lookup("rmi://" + "localhost" + "/" + "GroupChatService");
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

    /**
     * pass our username, hostname and RMI service name to
     * the server to register out interest in joining the chat
     */

//=====================================================================

    /**
     * Receive a string from the chat server
     * this is the clients RMI method, which will be used by the server
     * to send messages to us
     */

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
        clientGUIAfterLogin.textArea.append(message);
        //make the gui display the last appended text, ie scroll to bottom
        clientGUIAfterLogin.textArea.setCaretPosition(clientGUIAfterLogin.textArea.getDocument().getLength());
    }
    /**
     * A method to update the display of users
     * currently connected to the server
     */


    /**
     * - name: String
     * - employeeGUI: ClientGUI
     * - serverIF: serverInterface
     */

    @Override
    public void updateEmployeeList(String[] currentUsers) throws RemoteException {

        if (currentUsers.length < 2) {
            clientGUIAfterLogin.privatesendButton.setEnabled(false);
        }
        clientGUIAfterLogin.chatroom_panel.remove(clientGUIAfterLogin.chatroom_panel);
        //clientGUIAfterLogin.chatroom_panel.append(currentUsers);
        clientGUIAfterLogin.chatroom_panel.repaint();
        clientGUIAfterLogin.chatroom_panel.revalidate();
    }


    private void sendToALl(String chatMessage) throws RemoteException {
        this.ServerInterface.updateChattingList(name, chatMessage);
    }

    public String getName() throws RemoteException {
        return name;
    }

    public void sendPrivate(String massage, int[] selectedIndex) {

    }

    public void sendOder(int material, int product) {

    }

    public void updateHistory() throws RemoteException {

    }

    public void recreceiveMnP(int numberofM, int numberofP) throws RemoteException{

    }

    public void receiveHistory(String newRecord) throws RemoteException{

    }
}