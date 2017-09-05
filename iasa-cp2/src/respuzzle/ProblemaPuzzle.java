package respuzzle;

import pee.plantraj.modprob.Estado;
import pee.plantraj.modprob.ProblemaHeur;
import puzzle.Puzzle;

public class ProblemaPuzzle extends ProblemaHeur {
	
	Puzzle puzzleFinal;

	public ProblemaPuzzle(Puzzle puzzleInicial, Puzzle puzzleFinal, OperadorMovimentoPuzzle[] operadores) {
		super(new EstadoPuzzle(puzzleInicial), operadores);
		this.puzzleFinal = puzzleFinal;
	}

	@Override
	public boolean objectivo(Estado estado) {
		return estado.equals(new EstadoPuzzle(puzzleFinal));
	}

	@Override
	public double heuristica(Estado estado) {
		return ((EstadoPuzzle) estado).getPuzzle().distManhattan(puzzleFinal);
//		return ((EstadoPuzzle) estado).getPuzzle().numPecasForaLugar(puzzleFinal);
	}
}
