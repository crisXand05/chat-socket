package chr.model.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class PackageOutput implements Serializable{
	private String nick, ip, cuerpo;
	/*private ArrayList<String> ipsClient = new ArrayList<String>();*/
	private HashMap<String, String> ipsClient = new HashMap<String, String>();

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getIp() {
		return ip;
	}

	public HashMap<String, String> getIpsClient() {
		return ipsClient;
	}

	public void setIpClient(String nickName, String ipClient) {
		this.ipsClient.put(nickName, ipClient);
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String mensaje) {
		this.cuerpo = mensaje;
		}
		
		
	}

