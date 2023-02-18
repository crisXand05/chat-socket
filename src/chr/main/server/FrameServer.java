package chr.main.server;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import chr.model.client.PackageOutput;

class FrameServer extends JFrame implements Runnable{
	private	JTextArea areatexto;
	public FrameServer(){
		
		setBounds(600,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		
		Thread listenerThread = new Thread(this);
		listenerThread.start();
		}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("dasdasd");
		try {
			ServerSocket serverChat = new ServerSocket(9999);
			
			PackageOutput messageReceived = new PackageOutput();
			PackageOutput ipsClientOuput = new PackageOutput();
			ipsClientOuput.setCuerpo(" online");
			ArrayList<String> ipsConnected = new ArrayList<String>();
			
			while(true) {				
				Socket chatServerListener = serverChat.accept();
				ObjectInputStream objectInput = new ObjectInputStream(chatServerListener.getInputStream());
				
				messageReceived = (PackageOutput) objectInput.readObject();
								
				String nickName, ip, body;
				
				nickName = messageReceived.getNick();
				
				if( !messageReceived.getCuerpo().equals(" online")) {
					
					ip = messageReceived.getIp();
					body = messageReceived.getCuerpo();
					areatexto.append(nickName + ":" + body + " to " + ip + "\n");
					sendMessage(messageReceived, ip);
				}else {
					String ipClient = chatServerListener.getInetAddress().getHostAddress();
					
					ipsConnected.add(ipClient);
					ipsClientOuput.setIpClient(nickName, ipClient);
					
					for(int i=0; i<ipsConnected.size(); i++) {
						
						sendMessage(ipsClientOuput, ipsConnected.get(i));							
						
					}
					System.out.println(ipsConnected.toString());
				}
				
				chatServerListener.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}
	}
	
	private void sendMessage(PackageOutput message,String ip) {
		try {
			
			Socket sendDestiny = new Socket(ip, 9090);
			ObjectOutputStream objectOutputDestiny = new ObjectOutputStream(sendDestiny.getOutputStream());
			objectOutputDestiny.writeObject(message);
			objectOutputDestiny.close();
			sendDestiny.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}