package pee.mecproc.mem;

import java.util.Comparator;
import java.util.PriorityQueue;

import pee.mecproc.No;

public class MemoriaPrioridade extends MemoriaProcura{
	
	public MemoriaPrioridade(Comparator<No> comparator){
		//lista que internamente tem outra estrutura de dados associada
		super(new PriorityQueue(1, comparator));
	}
}
