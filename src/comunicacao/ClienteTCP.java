package comunicacao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClienteTCP extends Cliente {
	
	public ClienteTCP(InetAddress endereco, int porta) {
		super(endereco, porta);
		try {
			socket = new Socket(endereco, porta);
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ClienteTCP(Socket socket) {
		super(socket);
		try {
			endereco = socket.getInetAddress();
			porta = socket.getLocalPort();
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void envia(String mensagem) throws IOException {
		dos.writeUTF(mensagem);
		dos.flush();
	}

	@Override
	public String recebe() throws IOException {
		return dis.readUTF();
	}
}