package comunicacao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP extends Servidor {
	
	private DatagramPacket dp;
	private DatagramSocket ds;

	public ServidorUDP(int porta) {
		super(porta);
	}

	@Override
	public Cliente aceitaConexao() throws IOException {
		ds = new DatagramSocket(porta);
		
		byte[] buffer = new byte[1024];
		dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		
		return new ClienteUDP(ds.getInetAddress(), porta);
	}

	@Override
	public void envia(String mensagem) throws IOException {
		byte[] buffer = mensagem.getBytes();
		ds.send(new DatagramPacket(buffer, buffer.length, dp.getAddress(), dp.getPort()));
	}

	@Override
	public String escutar() throws IOException {
		return null;
	}
}