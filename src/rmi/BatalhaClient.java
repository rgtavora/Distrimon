package rmi;

import java.rmi.Naming;

public class BatalhaClient {
	
	public static void main(String[] args) {
		
		try {
			Batalha b = (Batalha) Naming.lookup("rmi:localhost/Batalha");
			
			b.turnoJogador1();
			
			b.turnoJogador2();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
