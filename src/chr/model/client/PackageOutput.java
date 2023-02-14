package chr.model.client;

import java.io.Serializable;
import java.util.ArrayList;


public class PackageOutput implements Serializable{
	private String nick, ip, cuerpo;
	private ArrayList<String> ipsClient = new ArrayList<String>();

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getIp() {
		return ip;
	}

	public ArrayList<String> getIpsClient() {
		return ipsClient;
	}

	public void setIpsClient(ArrayList<String> ipsClient) {
		this.ipsClient = ipsClient;
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

