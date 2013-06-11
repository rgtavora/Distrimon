package factory;

import comunicacao.*;
import configuracao.*;

public class ServidorFactory {

	private Servidor servidor;
	
	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	
	public Servidor iniciar(int porta){
		
		if(Configuracao.ConfiguracoaAtual().getMecanismoComunicacao() == MecanismoComunicacao.UDP){
			servidor = new ServidorUDP(porta);
		}else{
			servidor = new ServidorTCP(porta);
		}
		return servidor;
	}
}
