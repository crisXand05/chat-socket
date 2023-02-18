package chr.main.client;

import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chr.controller.client.SendsListenerController;
import chr.model.client.PackageOutput;

public class LeafClient extends JPanel implements Runnable{
	
	private JTextField bodyField;
	private JComboBox ipCombo;
	private JLabel nick;
	private JButton sendButton;
	private JTextArea fieldChat;
	private String userNick;
	public String getUserNick() {
		return userNick;
	}

	public static HashMap<String, String> userConected;
	public LeafClient(){
		userNick = JOptionPane.showInputDialog("Nick: ");
		JLabel nickLabel= new JLabel("Nick: ");
		nick = new JLabel();
		nick.setText(userNick);
		add(nickLabel);
		add(nick);
		
		JLabel texto=new JLabel("ONLINE");
		add(texto);
		
		ipCombo = new JComboBox();
		
		add(ipCombo);
		
		fieldChat = new JTextArea(12, 20);
		add(fieldChat);
		
		bodyField=new JTextField(20);
	
		add(bodyField);		
	
		sendButton=new JButton("Enviar");
		SendsListenerController et = new SendsListenerController(fieldChat, bodyField, ipCombo, nick);
		sendButton.addActionListener(et);
		add(sendButton);	
		
		Thread serverClientChatListenerThread = new Thread(this);
		serverClientChatListenerThread.start();
	}


	@Override
	public void run() {
		
		try {
			ServerSocket clientServer = new ServerSocket(9090);
			Socket clientSocket;
			PackageOutput receivedMessage;
			
			while(true) {
				clientSocket = clientServer.accept();
				ObjectInputStream inputStreamFlow = new ObjectInputStream(clientSocket.getInputStream());
				receivedMessage = (PackageOutput) inputStreamFlow.readObject();
				if(receivedMessage.getCuerpo().equals(" online")) {
					System.out.println(receivedMessage.getIpsClient().toString());
					ipCombo.removeAllItems();
					LeafClient.userConected = receivedMessage.getIpsClient();
					for(String nickName : receivedMessage.getIpsClient().keySet()) {
						if( !receivedMessage.getIpsClient().get(nickName).equals(InetAddress.getLocalHost().getHostAddress())) {
							
							ipCombo.addItem(nickName);
						}
					}
				}else {
					fieldChat.append(receivedMessage.getNick() + ": " + receivedMessage.getCuerpo() + "\n");					
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}