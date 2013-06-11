package rmi;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import distrimon.Tipo;

public class BatalhaImpl extends UnicastRemoteObject implements Batalha{

	private static final long serialVersionUID = 1L;

	public BatalhaImpl() throws RemoteException{
		super();
	}
	
	public static void main(String[] args) {
		
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		
		try {
			BatalhaImpl arq = new BatalhaImpl();
			
			Naming.rebind("rmi://localhost:port/Batalha", arq);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void turnoJogador1() throws IOException {
	}

	@Override
	public void turnoJogador2() throws IOException {
	}

	@Override
	public int calculaDano(String atck, Tipo jogAtacante, Tipo jogDefensor) {
		return 0;
	}

	@Override
	public int danoFraco() {
		return 0;
	}

	@Override
	public int danoNormal() {
		return 0;
	}

	@Override
	public int danoForte() {
		return 0;
	}
}