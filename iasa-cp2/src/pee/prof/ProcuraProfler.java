package pee.prof;

import pee.Solucao;
import pee.plantraj.modprob.Problema;

public class ProcuraProfler extends ProcuraProf{
	
	
	private int incProf = 1; //para a profundidade maxima, vamos incrementando este valor
	
	public int getIncProf(){
		return incProf;
	}
	
	public void setIncProf(int incProf){
		this.incProf = incProf;
	}
	
	private Solucao resolver(Problema problema, int profMax, int incProf){
		for(int profMaxIter = incProf; profMaxIter <= profMax; profMaxIter+=incProf){
			Solucao solucao = super.resolver(problema);
			if(solucao != null) //se existe solucao ou nao
				return solucao;
		}
		return null;
	}
	
	public Solucao resolver(Problema problema, int profMax){
		return resolver(problema, profMax, incProf);
	}
}
