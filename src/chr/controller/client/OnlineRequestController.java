package chr.controller.client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectOutputStream;
import java.net.Socket;

import chr.model.client.PackageOutput;

public class OnlineRequestController  extends WindowAdapter {
	public void windowOpened(WindowEvent we) {
		try {
			Socket onlineRequestSocket = new Socket("192.168.0.2",9999);
			PackageOutput onlineRequestData = new PackageOutput();
			onlineRequestData.setCuerpo(" online");
			ObjectOutputStream onlineRequestStream = new ObjectOutputStream(onlineRequestSocket.getOutputStream());
			onlineRequestStream.writeObject(onlineRequestData);
			onlineRequestSocket.close();
		}catch(Exception e) {
			
		}
	}
}