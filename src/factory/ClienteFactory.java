package factory;

import java.net.InetAddress;

import comunicacao.*;
import configuracao.*;

public class ClienteFactory {
	
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Cliente conectar(InetAddress endereco, int porta){
		
		if(Configuracao.ConfiguracoaAtual().getMecanismoComunicacao() == MecanismoComunicacao.UDP){
			cliente = new ClienteUDP(endereco, porta);
		}else{
			cliente = new ClienteTCP(endereco, porta);
		}
		
		return cliente;
	}

}
