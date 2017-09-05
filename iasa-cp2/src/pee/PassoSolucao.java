package pee;

import pee.plantraj.modprob.Estado;
import pee.plantraj.modprob.Operador;

public abstract interface PassoSolucao {
	
	public abstract Estado getEstado();
	public abstract Operador getOperador();
	public abstract double getCusto();
	
}
