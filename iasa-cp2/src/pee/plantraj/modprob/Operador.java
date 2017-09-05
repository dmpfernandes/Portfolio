package pee.plantraj.modprob;

public abstract interface Operador {
	
	public Estado aplicar(Estado estado);
	public float custo(Estado estado, Estado estadoSuc);

}
