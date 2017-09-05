package pee.mecproc;

import pee.PassoSolucao;
import pee.plantraj.modprob.Estado;
import pee.plantraj.modprob.Operador;

public class No implements PassoSolucao{

	private Estado estado;
	private Operador operador;
	private No antecessor;
	
	private int profundidade = 0;
	private double custo = 0;
	
	public No(Estado estado){ //estado inicial, porque nao tem atecedentes nem operadores
		this.estado = estado; //os valores sao os iniciais 
	}
	
	public No(Estado estado, Operador operador, No antecessor){ //nos no meio da arvore
		this(estado); //chama o construtor com o parametro estado
		this.operador = operador;
		this.antecessor = antecessor;
		
		if(antecessor != null){
			profundidade = antecessor.getProfundidade() + 1; //profundidade que está no nó? antecessor+1
			custo = operador.custo(antecessor.getEstado(), estado) + antecessor.getCusto(); //custo total ate ao no
		}
	}
	
	public Estado getEstado(){
		return this.estado;
	}
	
	public Operador getOperador(){
		return this.operador;
	}
	
	public No getAntecessor(){
		return this.antecessor;
	}
	
	public int getProfundidade(){
		return this.profundidade;
	}
	
	public double getCusto(){
		return this.custo;
	}
}
