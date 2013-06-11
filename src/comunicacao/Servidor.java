package comunicacao;

import java.io.IOException;

public abstract class Servidor extends Thread {

	protected int porta;
	
	public Servidor(int porta) {
		this.porta = porta;
	}
	
	public abstract Cliente aceitaConexao() throws IOException;
	public abstract void envia(String mensagem) throws IOException;
	public abstract String escutar() throws IOException;
}