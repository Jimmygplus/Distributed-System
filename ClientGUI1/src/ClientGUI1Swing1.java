import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.rmi.RemoteException;

public class ClientGUI1Swing1 implements ActionListener {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private String name,password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI1Swing1 window = new ClientGUI1Swing1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientGUI1Swing1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
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
