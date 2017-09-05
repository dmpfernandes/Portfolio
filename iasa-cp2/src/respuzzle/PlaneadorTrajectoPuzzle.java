package respuzzle;

import pee.PassoSolucao;
import pee.Procura;
import pee.Solucao;
import pee.plantraj.modprob.Problema;
import pee.prof.ProcuraProf;
import puzzle.Puzzle;

public class PlaneadorTrajectoPuzzle {

	public static void main(String[] args) {
		
		// Modelo --> quais sao os conceitos que estou a modular
		// Arquitectura
		// Implementacao
		
		//0 e a posicao vazia do puzzle
		int[][] configuracaoInicialA = new int[][] {{1, 2, 3}, {8, 4, 5}, {6, 7, 0}};
		int[][] configuracaoInicialB = new int[][] {{8, 4, 5}, {6, 1, 2}, {3, 7, 0}};
		int[][] configuracaoFinal = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
		
		Puzzle puzzleA = new Puzzle(configuracaoInicialA);
		Puzzle puzzleB = new Puzzle(configuracaoInicialB);
		Puzzle puzzleFinal = new Puzzle(configuracaoFinal);
		
		//2 - Definir o problema 
		Problema problema = new ProblemaPuzzle(puzzleB, puzzleFinal, definirOperadores());
		
		//3- Definir um mecanismo de procura
		Procura procura = new ProcuraProf();
		
		//4 - obter solucao para o problema com base no mecanismo da procura
		Solucao solucao = procura.resolver(problema);
		
		// 5 - Se existir solucao mostrar solucao 
		if(solucao != null)
			mostrarTrajecto(solucao);
		else
			System.out.println("Puzzle não encontrado");
	}
	
	//1 - definir operadores
	private static OperadorMovimentoPuzzle[] definirOperadores(){
		OperadorMovimentoPuzzle[] operadores = {
				
				new OperadorMovimentoPuzzle(Puzzle.Movimento.CIMA),
				new OperadorMovimentoPuzzle(Puzzle.Movimento.BAIXO),
				new OperadorMovimentoPuzzle(Puzzle.Movimento.ESQ),
				new OperadorMovimentoPuzzle(Puzzle.Movimento.DIR)
				 };
		return operadores;
	}
	
	private static void mostrarTrajecto(Solucao solucao){
		
		System.out.printf("Solucao do Puzzle:\n");
		System.out.println("Numero de passos: " + solucao.getDimensao());
		for(PassoSolucao no : solucao){
			System.out.println(">> Estado: "+no.getEstado()+ " - Custo: " + no.getCusto());
		}
		System.out.println("Custo Total: "+ solucao.getCusto());
	}
}
