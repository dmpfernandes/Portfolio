package pee.melhorprim;

import pee.Solucao;
import pee.plantraj.modprob.ProblemaHeur;

public interface ProcuraHeur {
	
	public Solucao resolver(ProblemaHeur problema);
	public Solucao resolver(ProblemaHeur problema, int profMAx);
}
