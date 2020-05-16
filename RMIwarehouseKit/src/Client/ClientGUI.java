package Client;
import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.rmi.RemoteException;

public class ClientGUI implements ActionListener {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private String name, password;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        //set the look and feel to 'Nimbus'
        try{
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(Exception e){
        }
        new ClientGUI();
    }

    /**
     * Create the application.
     * Initialize the contents of the frame.
     */
    public ClientGUI() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Brew Day");
        lblNewLabel.setBounds(180, 10, 58, 15);
        frame.getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(138, 64, 140, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(139, 113, 140, 20);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("User name:");
        lblNewLabel_1.setBounds(65, 68, 71, 15);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Password:");
        lblNewLabel_2.setBounds(65, 118, 71, 15);
        frame.getContentPane().add(lblNewLabel_2);

        JButton btnNewButton = new JButton("Log in");
        btnNewButton.setBounds(158, 184, 97, 23);
        frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
