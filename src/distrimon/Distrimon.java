package distrimon;

public class Distrimon {
	
	int hp;
	Tipo tipo;
	
	public Distrimon(Tipo tipo) {
		super();
		this.tipo = tipo;
		this.hp = 100;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

}