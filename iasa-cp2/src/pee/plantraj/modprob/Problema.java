package pee.plantraj.modprob;

public abstract class Problema {
	
	private Estado estadoInicial;
	private Operador[] operadores;

	public Problema(Estado estadoInicial, Operador[] operadores){
		this.estadoInicial = estadoInicial;
		this.operadores = operadores;
	}
	
	public Estado getEstadoInicial(){
		return this.estadoInicial;
	}
	
	public Operador[] getOperadores(){
		return this.operadores;
	}
	
	public abstract boolean objectivo(Estado estado);
}
