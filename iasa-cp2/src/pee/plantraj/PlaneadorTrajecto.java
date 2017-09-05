package pee.plantraj;

import pee.PassoSolucao;
import pee.Procura;
import pee.Solucao;
import pee.melhorprim.ProcuraCustoUnif;
import pee.plantraj.modprob.OperadorLigacao;
import pee.plantraj.modprob.Problema;
import pee.plantraj.modprob.ProblemaPlanTraj;

public class PlaneadorTrajecto{
	
	public static void main(String[] args) {
		String locIni = "Loc-0";
		String locFin = "Loc-4";
		//2 - Definir o problema 
		Problema problema = new ProblemaPlanTraj(locIni, locFin, definirOperadores());
		
		//3- Definir um mecanismo de procura
		// ProcuraLarg();
		Procura procura = new ProcuraCustoUnif();
		
		//4 - obter solucao para o problema com base no mecanismo da procura
		Solucao solucao = procura.resolver(problema);
		
		// 5 - Se existir solucao mostrar solucao 
		if(solucao != null)
			mostrarTrajecto(solucao);
		else
			System.out.println("Trajecto não encontrado");
	}
	
	//1 - definir operadores
	private static OperadorLigacao[] definirOperadores(){
		OperadorLigacao operadores[] = {
				new OperadorLigacao("Loc-0", "Loc-1", 5), 
				new OperadorLigacao("Loc-0", "Loc-2", 25),
				new OperadorLigacao("Loc-1", "Loc-3", 12),
				new OperadorLigacao("Loc-1", "Loc-6", 5),
				new OperadorLigacao("Loc-2", "Loc-4", 30),
				new OperadorLigacao("Loc-3", "Loc-2", 10),
				new OperadorLigacao("Loc-3", "Loc-5", 5),
				new OperadorLigacao("Loc-4", "Loc-3", 2),
				new OperadorLigacao("Loc-5", "Loc-6", 8),
				new OperadorLigacao("Loc-5", "Loc-4", 10),
				new OperadorLigacao("Loc-6", "Loc-3", 15) };
		return operadores;
	}
	
	private static void mostrarTrajecto(Solucao solucao){
		
		System.out.printf("Trajecto:\n");
		System.out.println("Numero de passos: " + solucao.getDimensao());
		for(PassoSolucao no : solucao){
			System.out.println(">> Estado: "+no.getEstado()+ " - Custo: " + no.getCusto());
		}
		System.out.println("Custo Total: "+ solucao.getCusto());
	}
}
