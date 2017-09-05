package pee.melhorprim;

import pee.mecproc.No;
import pee.plantraj.modprob.ProblemaHeur;


public class ProcuraAA extends ProcuraMelhorPrim<ProblemaHeur> implements ProcuraHeur{

	@Override
	protected double f(No no) {
		return problema.heuristica(no.getEstado()) + no.getCusto();
	}

}