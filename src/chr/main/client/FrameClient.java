package chr.main.client;

import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chr.controller.client.OnlineRequestController;
import chr.controller.client.SendsListenerController;
import chr.model.client.PackageOutput;

public class FrameClient extends JFrame{
	
	public FrameClient(){
		
		setBounds(600,300,280,350);
				
		LeafClient milamina=new LeafClient();
		
		add(milamina);
		
		setVisible(true);
		addWindowListener(new OnlineRequestController(milamina.getUserNick()));
		}	
}





