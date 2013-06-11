package comunicacao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public abstract class Cliente {

	protected InetAddress endereco;
	protected int porta;
	
	protected Socket socket;
	protected DataInputStream dis;
	protected DataOutputStream dos;

	public Cliente(InetAddress endereco, int porta) {
		this.endereco = endereco;
		this.porta = porta;
	}
	
	public Cliente(Socket socket) {
		this.socket = socket;
	}
	
	public abstract void envia(String mensagem) throws IOException;
	public abstract String recebe() throws IOException;
}
