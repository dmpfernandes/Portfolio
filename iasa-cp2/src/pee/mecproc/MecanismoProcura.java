package pee.mecproc;

import pee.Solucao;
import pee.mecproc.mem.MemoriaProcura;
import pee.plantraj.modprob.Estado;
import pee.plantraj.modprob.Operador;
import pee.plantraj.modprob.Problema;

public abstract class MecanismoProcura<P extends Problema> { //abstrato - porque vai incluir o algoritmo geral, concretizecoes especificas 
									//tipo generico, mas só pode ser da Classe Problema
	protected P problema;
	private MemoriaProcura memoriaProcura;
	
	public MecanismoProcura(){ 
		memoriaProcura = iniciarMemoria();
	}
	
	protected abstract MemoriaProcura iniciarMemoria();
	
	public Solucao resolver(P problema){
		return resolver(problema, Integer.MAX_VALUE); //MAX_VALUE - numero infinito da maquina
	}
	
	public Solucao resolver(P problema, int profMax){
		this.problema = problema;
		memoriaProcura.limpar();
		No noInicial = new No(problema.getEstadoInicial());
		
		//System.out.println(noInicial.getEstado());
		
		memoriaProcura.inserir(noInicial);
		
		while (!memoriaProcura.fronteiraVazia()){
			No no = memoriaProcura.remover();
			if(problema.objectivo(no.getEstado()))
				return gerarSolucao(no);
			else{
				if(no.getProfundidade() < profMax)
					expandir(no);
			}
		}
		return null;
	}
	
	private void expandir(No no){
		Estado estado = no.getEstado();
		Operador[] operadores = problema.getOperadores();
		
		for(Operador operador: operadores){
			Estado estadoSuc = operador.aplicar(estado);
			if(estadoSuc != null){
				No noSuc = new No(estadoSuc, operador, no);
				memoriaProcura.inserir(noSuc);
			}
		}
	}
	
	private Solucao gerarSolucao(No noFinal){ 
		Percurso percurso = new Percurso();
		No no = noFinal;
		
		while(no != null){ //estou sempre a juntar ao inicio
			//System.out.println(no.getEstado());
			percurso.juntarInicio(no);
			no = no.getAntecessor(); //no = antecessor
		}
		return percurso; 
	}
}
