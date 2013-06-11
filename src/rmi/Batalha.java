package rmi;

import java.io.IOException;
import java.rmi.Remote;

import distrimon.Tipo;

public interface Batalha extends Remote {

	void turnoJogador1() throws IOException;
	
	void turnoJogador2() throws IOException;
	
	int calculaDano(String atck, Tipo jogAtacante, Tipo jogDefensor);
	
	int danoFraco();
	
	int danoNormal();
	
	int danoForte();
}