package pee;

import pee.plantraj.modprob.Problema;

public abstract interface Procura {
	
	public abstract Solucao resolver(Problema problema);
	public abstract Solucao resolver(Problema problema, int profMax);
	
}
