package distrimon;

import java.io.IOException;
import java.util.ArrayList;

import comunicacao.Cliente;

import configuracao.Configuracao;
import configuracao.MecanismoComunicacao;
import factory.ServidorFactory;

public class DistriServer {
	
	int porta;
	ServidorFactory servidor;
	
	ArrayList<String> nomesJogadores;
	ArrayList<Cliente> jogadores;
	
	public DistriServer(int porta, boolean isTCP) {
		super();
		
		if(isTCP) {
			Configuracao.ConfiguracoaAtual().setMecanismoComunicacao(MecanismoComunicacao.TCP);
		}else {
			Configuracao.ConfiguracoaAtual().setMecanismoComunicacao(MecanismoComunicacao.UDP);
		}
		
		servidor = new ServidorFactory();
		servidor.iniciar(porta);
		
		nomesJogadores = new ArrayList<String>();
		jogadores = new ArrayList<Cliente>();
	}
	
	public void aceitaConexao() {
		while(true) {
			try {
				
				new DistriServerThread(servidor.getServidor().aceitaConexao()).start();
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean verificarNome(String nome) {
		for(int i = 0; i < nomesJogadores.size(); i++) {
			if(nomesJogadores.get(i).equals(nome))
				return true;
		}
		nomesJogadores.add(nome);
		GUIServer.atualizarListaJogadores(nomesJogadores);
		return false;
	}
	
	public void desconectarJogador(String nome, Cliente jogador) {
		jogadores.remove(jogador);
		nomesJogadores.remove(nome);
		GUIServer.atualizarListaJogadores(nomesJogadores);
	}
	
	public class DistriServerThread extends Thread {
		
		Cliente cliente;
		
		public DistriServerThread(Cliente socket) {
			this.cliente = socket;
		}
		
		public void run() {
			try {
				GUIServer.logger("Nova tentativa de conexão...");
				String nomeJogador = this.cliente.recebe();
				
				GUIServer.logger("Validando jogador...");
				if(verificarNome(nomeJogador)) {
					this.cliente.envia("OUTRONOME");
					GUIServer.logger("Jogador recusado!");
					//cliente.close();
					return;
				}else {
					this.cliente.envia("BEMVINDO");
					GUIServer.logger(nomeJogador + " conectado!");
					this.cliente.envia("AGUARDE");
					GUIServer.logger(nomeJogador + " aguardando adversário...");
				}
				
				if (nomeJogador == null) {
					return;
				}
				
				jogadores.add(cliente);
				
				GUIServer.verificarBatalha();
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}