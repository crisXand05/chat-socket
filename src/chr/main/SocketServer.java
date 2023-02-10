package chr.main;



import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.net.*;

public class SocketServer  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable{
	
	public MarcoServidor(){
		
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
	
	private	JTextArea areatexto;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("dasdasd");
		try {
			ServerSocket servidor = new ServerSocket(9999);
			while(true) {				
				Socket chat = servidor.accept();
				DataInputStream flujoEntrada = new DataInputStream(chat.getInputStream());
				String mensaje = flujoEntrada.readUTF();
				areatexto.append("\n"+mensaje);
				chat.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
