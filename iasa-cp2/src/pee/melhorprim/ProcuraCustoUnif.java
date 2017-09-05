package pee.melhorprim;

import pee.Procura;
import pee.mecproc.No;
import pee.plantraj.modprob.Problema;

public class ProcuraCustoUnif extends ProcuraMelhorPrim<Problema> implements Procura{
	
	protected double f(No no){
		return no.getCusto();
	}
}
