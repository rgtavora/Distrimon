package configuracao;

public class Configuracao {

	private static Configuracao config;
	private MecanismoComunicacao mecanismo;
	
	private Configuracao(){}
	
	public static Configuracao ConfiguracoaAtual(){
		
		if(config == null)
			config = new Configuracao();
		
		return config;
	}
	
	public void setMecanismoComunicacao(MecanismoComunicacao mecanismo){
		this.mecanismo = mecanismo;
	}
	
	public MecanismoComunicacao getMecanismoComunicacao(){
		return mecanismo;
	}
	
}
