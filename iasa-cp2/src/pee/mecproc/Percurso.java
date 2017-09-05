package pee.mecproc;

import java.util.Iterator;
import java.util.LinkedList;

import pee.PassoSolucao;
import pee.Solucao;

public class Percurso implements Solucao{
	
	private LinkedList<PassoSolucao> percurso = new LinkedList<PassoSolucao>();
	
	@Override
	public int getDimensao(){
		return percurso.size();
	}
	
	@Override
	public double getCusto(){
		if(percurso.isEmpty()){
			return 0.0; 
		}
		return percurso.getLast().getCusto(); //custo do percurso ate ao ultimo no
	}

	@Override
	public Iterator<PassoSolucao> iterator(){
		return percurso.iterator();
	}
	
	public void juntarInicio(No no){
		percurso.addFirst(no);
	}
}
