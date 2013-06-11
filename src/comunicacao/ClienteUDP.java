package comunicacao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ClienteUDP extends Cliente {
	
	private DatagramSocket ds;

	public ClienteUDP(InetAddress endereco, int porta) {
		super(endereco, porta);
	}

	@Override
	public void envia(String mensagem) throws IOException {
		ds = new DatagramSocket();
		byte[] data = mensagem.getBytes();
		ds.send(new DatagramPacket(data, data.length, endereco, porta));
	}

	@Override
	public String recebe() throws IOException {
		byte[] buffer = new byte[1024];
		DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
		ds.receive(datagram);
		return limparMensagem(datagram);
	}
	
	public static String limparMensagem(DatagramPacket datagram) {
		if(datagram == null) {
			return "";
		}
		
		ArrayList<Byte> bytesLimpos = new ArrayList<Byte>();
		
		for(byte bytes : datagram.getData()) {
			if (bytes == 0) {
				break;
			}
			bytesLimpos.add(bytes);
		}
		
		byte[] bytes = new byte[bytesLimpos.size()];
		
		for(int i = 0; i < bytesLimpos.size(); i++) {
			bytes[i] = bytesLimpos.get(i);
		}

		return new String(bytes);
	}
}