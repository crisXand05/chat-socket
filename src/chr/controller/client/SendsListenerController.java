package chr.controller.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chr.model.client.PackageOutput;

public class SendsListenerController implements ActionListener{
	private JTextArea fieldChat;
	private JTextField bodyField;
	private JComboBox ipCombo;
	private JLabel nick;

	
	public SendsListenerController(JTextArea fieldChat, JTextField bodyField, JComboBox ipCombo, JLabel nick) {
		this.fieldChat = fieldChat;
		this.bodyField = bodyField;
		this.ipCombo = ipCombo;
		this.nick = nick;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(campo1.getText());
		fieldChat.append(nick.getText() + ": " + bodyField.getText() + "\n");
		try {
			
			Socket chat = new Socket("192.168.0.2",9999);
			
			/*DataOutputStream flujoSalida = new DataOutputStream(chat.getOutputStream());
			flujoSalida.writeUTF(campo1.getText());
			flujoSalida.close();*/
			
			PackageOutput mensaje = new PackageOutput();
			mensaje.setNick(nick.getText());
			mensaje.setIp(ipCombo.getSelectedItem().toString());
			mensaje.setCuerpo(bodyField.getText());
			
			ObjectOutputStream outputObject = new ObjectOutputStream(chat.getOutputStream());
			outputObject.writeObject(mensaje);
			chat.close();
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		
		
	}
	
}