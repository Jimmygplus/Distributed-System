package Client;

import Server.ServerInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.DefaultListModel;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class ClientGUIAfterLogin implements ActionListener {

    protected JFrame frame;
    protected JTextField textField;
    protected JTextField textField_1;
    protected JTextArea textArea, textArea_2, chatDisplayArea;
    protected JButton privatesendButton, sendButton, ConfirmButton;
    protected JPanel chatroom_panel, onlineEmplouee_panel, Currentquantity_panel, history_panel;
    private JSpinner spinner_1, spinner_2;
    protected JList<String> list;
    protected DefaultListModel<String> listModel;

    protected Client client;
    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    ClientGUIAfterLogin window = new ClientGUIAfterLogin();
//                    window.frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the application.
     */
    public ClientGUIAfterLogin() {

        frame = new JFrame();
        frame.setBounds(100, 100, 815, 670);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if(client != null){
                    try {
                        client.ServerInterface.offline(client.getName());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                System.exit(0);
            }
        });
        chatroom_panel = new JPanel();
        chatroom_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        chatroom_panel.setBounds(34, 35, 447, 376);
        frame.getContentPane().add(chatroom_panel);
        chatroom_panel.setLayout(null);

        chatDisplayArea = new JTextArea();
        chatDisplayArea.setBounds(117, 291, 320, 59);
        chatroom_panel.add(chatDisplayArea);

        textArea = new JTextArea();
        textArea.setBounds(117, 35, 320, 235);
        chatroom_panel.add(textArea);
        textArea.setText("what");



        privatesendButton = new JButton("Send PM");
        privatesendButton.setBounds(10, 292, 97, 23);
        chatroom_panel.add(privatesendButton);
        privatesendButton.addActionListener(this);

        this.listModel = new DefaultListModel<String>();
        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setBounds(10, 74, 120, 194);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setVisibleRowCount(12);

        onlineEmplouee_panel = new JPanel();
        onlineEmplouee_panel.setBounds(10, 74, 120, 194);
        //frame.getContentPane().add(onlineEmplouee_panel);
        chatroom_panel.add(onlineEmplouee_panel);


    }




    public void Initialize() {



        JLabel lblNewLabel = new JLabel("Chat Room");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setBounds(10, 10, 427, 15);
        chatroom_panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Current User");
        lblNewLabel_1.setBounds(10, 44, 80, 15);
        chatroom_panel.add(lblNewLabel_1);


        sendButton = new JButton("Send");
        sendButton.setBounds(10, 327, 97, 23);
        chatroom_panel.add(sendButton);
        sendButton.addActionListener(this);

        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        onlineEmplouee_panel.add(listScrollPane);

        JPanel warehouse_panel = new JPanel();
        warehouse_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        warehouse_panel.setBounds(491, 35, 300, 376);

        frame.getContentPane().add(warehouse_panel);
        warehouse_panel.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("Warehouse");
        lblNewLabel_2.setBounds(10, 10, 280, 15);
        warehouse_panel.add(lblNewLabel_2);

        Currentquantity_panel = new JPanel();
        Currentquantity_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        Currentquantity_panel.setBounds(20, 35, 256, 125);
        Currentquantity_panel.setBackground(Color.white);
        warehouse_panel.add(Currentquantity_panel);
        Currentquantity_panel.setLayout(null);

        JLabel lblNewLabel_4 = new JLabel("Current Quantitiy");
        lblNewLabel_4.setBounds(10, 10, 163, 15);
        Currentquantity_panel.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Product");
        lblNewLabel_5.setBounds(30, 80, 66, 21);
        Currentquantity_panel.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Material");
        lblNewLabel_6.setBounds(30, 40, 66, 21);
        Currentquantity_panel.add(lblNewLabel_6);

        //System.out.println("what ");

        textField = new JTextField();
        try{
            Client oldclient = this.client;
            System.out.println(oldclient.getName());
            ServerInterface oldIF = oldclient.ServerInterface;
            int MM = oldIF.getMaterial();
            textField.setText(" "+ MM);
        }catch (RemoteException e){
            e.getStackTrace();
        }
        textField.setEnabled(false);
        textField.setBounds(107, 40, 66, 21);
        Currentquantity_panel.add(textField);
        textField.setColumns(10);



        textField_1 = new JTextField();
        try{
            int PP = this.client.ServerInterface.getProduct();

            textField_1.setText(" "+ PP);
        }catch (RemoteException e){
            e.getStackTrace();
        }
        textField_1.setEnabled(false);
        textField_1.setBounds(107, 80, 66, 21);
        Currentquantity_panel.add(textField_1);
        textField_1.setColumns(10);

        JPanel update_panel = new JPanel();
        update_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        update_panel.setBounds(20, 190, 256, 125);
        warehouse_panel.add(update_panel);
        update_panel.setLayout(null);

        JLabel lblNewLabel_7 = new JLabel("Material");
        lblNewLabel_7.setBounds(30, 20, 58, 15);
        update_panel.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("Product");
        lblNewLabel_8.setBounds(30, 49, 58, 15);
        update_panel.add(lblNewLabel_8);

        spinner_1 = new JSpinner();
        spinner_1.setBounds(108, 17, 64, 22);
        update_panel.add(spinner_1);

        spinner_2 = new JSpinner();
        spinner_2.setBounds(108, 46, 64, 22);
        update_panel.add(spinner_2);

        ConfirmButton = new JButton("Confirm");
        ConfirmButton.setBounds(75, 92, 97, 23);
        update_panel.add(ConfirmButton);
        ConfirmButton.addActionListener(this);

        history_panel = new JPanel();
        history_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        history_panel.setBounds(34, 433, 757, 190);
        frame.getContentPane().add(history_panel);
        history_panel.setLayout(null);

        JLabel lblNewLabel_3 = new JLabel("History");
        lblNewLabel_3.setBounds(10, 10, 737, 15);
        history_panel.add(lblNewLabel_3);

        textArea_2 = new JTextArea();
        textArea_2.setBounds(10, 35, 737, 145);
        history_panel.add(textArea_2);
        try{
            String[] histories = this.client.ServerInterface.loadHistory();
            for(String history:histories){
                textArea_2.append(history+"\n");
            }
        }catch (RemoteException e){
            e.getStackTrace();
        }
        textArea_2.setEnabled(false);

        frame.setVisible(true);

    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("&");
        try {
            //get connected to chat service
            String tempText;
            if (e.getSource() == privatesendButton) {
                tempText = chatDisplayArea.getText();
                int[] tempList = this.list.getSelectedIndices();
                this.client.sendPrivate(tempText,tempList);
                chatDisplayArea.setText("");
            }else if(e.getSource() == sendButton){
                tempText = chatDisplayArea.getText();
                this.client.sendToALl(tempText);
                chatDisplayArea.setText("");
            }else if(e.getSource() == ConfirmButton){
                int m = (int)spinner_1.getValue();
                int p = (int)spinner_2.getValue();
                if(m != 0){
                    this.client.getOrder(0,m);
                }
                if(p != 0){
                    this.client.getOrder(1,p);
                }
            }else{
                System.out.println("wrong");
            }
        } catch (Exception remoteExc) {
            remoteExc.printStackTrace();
        }
    }

}
