package respuzzle;

import pee.plantraj.modprob.Estado;
import pee.plantraj.modprob.Operador;
import puzzle.Puzzle;

public class OperadorMovimentoPuzzle implements Operador{
	
	Puzzle.Movimento movimento;
	
	public OperadorMovimentoPuzzle(Puzzle.Movimento movimento){
		this.movimento = movimento;
	}

	@Override
	public Estado aplicar(Estado estadoPuzzle){
		
		Puzzle novoPuzzle = ((EstadoPuzzle) estadoPuzzle).getPuzzle().movimentar(movimento);
		
		if (novoPuzzle != null)
			return new EstadoPuzzle(novoPuzzle);
		return null;
	}
	
	@Override
	public float custo(Estado estadoPuzzle, Estado estadoSucPuzzle){
		return 1;
	}

}