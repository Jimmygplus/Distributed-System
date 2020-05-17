package Client;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.rmi.RemoteException;

public class ClientGUI implements ActionListener {

    protected JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    protected JButton btnNewButton;
    private String name;
    private Client client;
    private ClientGUIAfterLogin clientGUIAfterLogin;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
        new ClientGUI();
    }

    /**
     * Create the application.
     * Initialize the contents of the frame.
     */
    public ClientGUI() {
        frame = new JFrame("Employee's Console");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Remote Warehouse Kit");
        lblNewLabel.setBounds(180, 10, 150, 30);
        frame.getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(138, 64, 140, 30);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(139, 113, 140, 30);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Username:");
        lblNewLabel_1.setBounds(65, 68, 71, 30);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Password:");
        lblNewLabel_2.setBounds(65, 118, 71, 30);
        frame.getContentPane().add(lblNewLabel_2);

        btnNewButton = new JButton("Log in");
        btnNewButton.setBounds(158, 184, 97, 25);
        frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(this);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //get connected to chat service
            if (e.getSource() == btnNewButton) {
                name = textField.getText();

                clientGUIAfterLogin = new ClientGUIAfterLogin();
                client = new Client(this, clientGUIAfterLogin, name);
                clientGUIAfterLogin.setClient(client);
                clientGUIAfterLogin.Initialize();

                client.connectToServer();


                if (name.length() != 0 && client.ServerInterface.matchPassword(textField_1.getText())) {
                    clientGUIAfterLogin.frame.setTitle(name + "'s console ");
                    clientGUIAfterLogin.textArea.setText("");
                    clientGUIAfterLogin.textArea.append(name + " connecting to chat...\n");
                    if (!client.connectionProblem) {
                        clientGUIAfterLogin.sendButton.setEnabled(true);
                    }
                    frame.setVisible(false);

                } else if (!client.ServerInterface.matchPassword(textField_1.getText())) {
                    clientGUIAfterLogin.frame.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Password Wrong!");
                } else if (name.length() == 0) {
                    clientGUIAfterLogin.frame.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Username Cannot Be Empty!");
                }
            }
        } catch (RemoteException remoteExc) {
            remoteExc.printStackTrace();
        }
    }
}