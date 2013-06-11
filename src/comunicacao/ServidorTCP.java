package comunicacao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class ServidorTCP extends Servidor {
	
	private ServerSocket server;
	private DataInputStream dis;
	private DataOutputStream dos;

	public ServidorTCP(int porta) {
		super(porta);
		try {
			server = new ServerSocket(porta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Cliente aceitaConexao() throws IOException {
		Cliente cliente = new ClienteTCP(server.accept());
		
		return cliente;
	}

	@Override
	public void envia(String mensagem) throws IOException {
		dos.writeUTF(mensagem);
	}
	
	public String escutar() throws IOException {
		return dis.readUTF();
	}
}