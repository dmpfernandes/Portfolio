package pee.mecproc.mem;

import java.util.HashMap;
import java.util.Queue;

import pee.mecproc.No;
import pee.plantraj.modprob.Estado;

public class MemoriaProcura {

	// Queue interface generica que implementa tanto linkedlist (fifo) como lifo
	protected Queue<No> fronteira;
	protected HashMap<Estado, No> explorados; // chave estado, conteudo sao nos
	
	public MemoriaProcura(Queue<No> fronteira){
		this.fronteira = fronteira;
		explorados = new HashMap<Estado, No>();
	}
	
	public void limpar(){
		fronteira.clear();
		explorados.clear();
	}
	
	public void inserir(No no){
		Estado estado = no.getEstado();
		No noMem = explorados.get(estado);
		
		//se existir memoria
			//e se "no" do custo em memoria for maior que o custo do no novo
			//adiciona o novo no, que e mais barato do que esta em memoria
		if(noMem == null? true: noMem.getCusto() > no.getCusto()){
			fronteira.add(no);
			explorados.put(estado, no);
		}
	}
	
	// poll() retorna null se vazio, remove() retorna excepcao ou entao o no
	// cabeca da queue
	public No remover(){
		return fronteira.poll(); //remove o primeiro
	}
	
	public boolean fronteiraVazia(){
		return fronteira.isEmpty();
	}
}
