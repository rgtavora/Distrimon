package distrimon;

import java.io.IOException;
import java.util.Random;

import comunicacao.Cliente;

public class Batalha extends Thread {
	
	Cliente jogador1;
	Cliente jogador2;
	
	String nomeJogador1;
	String nomeJogador2;
	
	Distrimon distrimon1;
	Distrimon distrimon2;
	
	int defesa1;
	int defesa2;
	
	boolean vivo1;
	boolean vivo2;
	
	public Batalha(Cliente jogador1, Cliente jogador2, String nomeJogador1, String nomeJogador2) {
		super();
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.nomeJogador1 = nomeJogador1;
		this.nomeJogador2 = nomeJogador2;
		this.defesa1 = 0;
		this.defesa2 = 0;
		this.vivo1 = true;
		this.vivo2 = true;
		
		GUIServer.jogadoresBatalhando.add(jogador1);
		GUIServer.jogadoresBatalhando.add(jogador2);
		
		this.start();
	}

	public void run() {
		try {
			
			this.jogador1.envia("BATALHA");
			this.jogador2.envia("BATALHA");
			
			
			this.jogador2.envia("AGUARDEADVERSARIO");
			this.jogador1.envia("ESCOLHER");
			String dtmn1 = this.jogador1.recebe();
			GUIServer.logger(nomeJogador1 + " escolheu " + dtmn1);
			
			this.jogador1.envia("AGUARDEADVERSARIO");
			this.jogador2.envia("ESCOLHER");
			String dtmn2 = this.jogador2.recebe();
			GUIServer.logger(nomeJogador2 + " escolheu " + dtmn2);
			
			this.jogador1.envia(dtmn2);
			this.jogador2.envia(dtmn1);
			
			if(dtmn1.equals("BULBASAUR")) {
				distrimon1 = new Distrimon(Tipo.PLANTA);
			}else if(dtmn1.equals("CHARMANDER")) {
				distrimon1 = new Distrimon(Tipo.FOGO);
			}else if(dtmn1.equals("SQUIRTLE")) {
				distrimon1 = new Distrimon(Tipo.AGUA);
			}if(dtmn1.equals("PIKACHU")) {
				distrimon1 = new Distrimon(Tipo.ELETRICO);
			}else if(dtmn1.equals("MEWTWO")) {
				distrimon1 = new Distrimon(Tipo.PSIQUICO);
			}
			
			if(dtmn2.equals("BULBASAUR")) {
				distrimon2 = new Distrimon(Tipo.PLANTA);
			}else if(dtmn2.equals("CHARMANDER")) {
				distrimon2 = new Distrimon(Tipo.FOGO);
			}else if(dtmn2.equals("SQUIRTLE")) {
				distrimon2 = new Distrimon(Tipo.AGUA);
			}if(dtmn2.equals("PIKACHU")) {
				distrimon2 = new Distrimon(Tipo.ELETRICO);
			}else if(dtmn2.equals("MEWTWO")) {
				distrimon2 = new Distrimon(Tipo.PSIQUICO);
			}
			
			while(vivo1 && vivo2) {
				if(vivo1)
					turnoJogador1();
				
				if(vivo2)
					turnoJogador2();
			}
			
			GUIServer.jogadoresBatalhando.remove(jogador1);
			GUIServer.jogadoresBatalhando.remove(jogador2);
			
			GUIServer.verificarBatalha();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void turnoJogador1() throws IOException {
		this.jogador2.envia("AGUARDETURNO");
		this.jogador1.envia("ATAQUE");
		
		String acao = this.jogador1.recebe();
		GUIServer.logger(nomeJogador1 + " usou " + acao + " contra " + nomeJogador2);
		
		int dano = 0;
		
		if(acao.equals("DEFESA")) {
			defesa1 = 5;
		}else {
			dano = calculaDano(acao, distrimon1.getTipo(), distrimon2.getTipo());
		}
		
		int danoFinal = dano - defesa1;
		defesa1 = 0;
		
		if(danoFinal < 0)
			danoFinal = 0;
		
		this.jogador1.envia("DANOADV " + danoFinal);
		this.jogador2.envia("DANODTM " + danoFinal);
		GUIServer.logger(nomeJogador2 + " sofreu " + danoFinal + " de dano!");
		
		String resultado = this.jogador2.recebe();
		
		if(resultado.equals("VIVO")) {
			GUIServer.logger(nomeJogador2 + " continua na batalha!");
		}else if(resultado.equals("MORTO")) {
			GUIServer.logger(nomeJogador2 + " perdeu a batalha!");
			GUIServer.logger(nomeJogador1 + " é o grande vencedor!");
			GUIServer.logger("Desconectando " + nomeJogador2 + "...");
			jogador2.envia("VAZA");
			GUIServer.server.desconectarJogador(nomeJogador2, jogador2);
			GUIServer.logger(nomeJogador2 + " desconectado!");
			vivo2 = false;
			
			jogador1.envia("PREMIO");
			String premio = jogador1.recebe();
			GUIServer.logger(nomeJogador1 + " escolheu " + premio + " como prêmio!");
			
			jogador1.envia("AGUARDE");
			GUIServer.logger(nomeJogador1 + " aguardando adversário...");
		}
	}
	
	public void turnoJogador2() throws IOException {
		this.jogador1.envia("AGUARDETURNO");
		this.jogador2.envia("ATAQUE");
		
		String acao = this.jogador2.recebe();
		GUIServer.logger(nomeJogador2 + " usou " + acao + " contra " + nomeJogador1);
		
		int dano = 0;
		
		if(acao.equals("DEFESA")) {
			defesa2 = 5;
		}else {
			dano = calculaDano(acao, distrimon2.getTipo(), distrimon1.getTipo());
		}
		
		int danoFinal = dano - defesa2;
		defesa2 = 0;
		
		if(danoFinal < 0)
			danoFinal = 0;
		
		this.jogador2.envia("DANOADV " + danoFinal);
		this.jogador1.envia("DANODTM " + danoFinal);
		GUIServer.logger(nomeJogador1 + " sofreu " + danoFinal + " de dano!");
		
		String resultado = this.jogador1.recebe();
		
		if(resultado.equals("VIVO")) {
			GUIServer.logger(nomeJogador1 + " continua na batalha!");
		}else if(resultado.equals("MORTO")) {
			GUIServer.logger(nomeJogador1 + " perdeu a batalha!");
			GUIServer.logger(nomeJogador2 + " é o grande vencedor!");
			GUIServer.logger("Desconectando " + nomeJogador1 + "...");
			jogador1.envia("VAZA");
			GUIServer.server.desconectarJogador(nomeJogador1, jogador1);
			GUIServer.logger(nomeJogador1 + " desconectado!");
			vivo1 = false;
			
			jogador2.envia("PREMIO");
			String premio = jogador2.recebe();
			GUIServer.logger(nomeJogador2 + " escolheu " + premio + " como prêmio!");
			
			jogador2.envia("AGUARDE");
			GUIServer.logger(nomeJogador2 + " aguardando adversário...");
		}
	}
	
	private int calculaDano(String atck, Tipo jogAtacante, Tipo jogDefensor) {
		int dano = danoNormal();
		if(jogAtacante == Tipo.PSIQUICO && jogDefensor ==  Tipo.ELETRICO) {
			dano = danoNormal();
		}else if(jogAtacante == Tipo.PSIQUICO) {
			dano = danoForte();
		}else if(jogAtacante == Tipo.ELETRICO) {
			dano = danoNormal();
		}else {
			if(atck.equals("ESPECIAL")) {
				if(jogAtacante == Tipo.AGUA) {
					if(jogDefensor == Tipo.PLANTA || jogDefensor == Tipo.ELETRICO)
						dano = danoFraco();
					else if(jogDefensor == Tipo.FOGO)
						dano = danoForte();
					else
						dano = danoNormal();
				}else if(jogAtacante == Tipo.FOGO) {
					if(jogDefensor == Tipo.AGUA || jogDefensor == Tipo.ELETRICO)
						dano = danoFraco();
					else if(jogDefensor == Tipo.PLANTA)
						dano = danoForte();
					else
						dano = danoNormal();
				}else if(jogAtacante == Tipo.PLANTA) {
					if(jogDefensor == Tipo.FOGO || jogDefensor == Tipo.ELETRICO)
						dano = danoFraco();
					else if(jogDefensor == Tipo.AGUA)
						dano = danoForte();
					else
						dano = danoNormal();
				}
			}
		}
		return dano;
	}
	
	private int danoFraco() {
		return new Random().nextInt(5 - 1 + 1) + 1;
	}
	
	private int danoNormal() {
		return new Random().nextInt(8 - 3 + 1) + 3;
	}
	
	private int danoForte() {
		return new Random().nextInt(12 - 8 + 1) + 8;
	}
}