package pee.plantraj.modprob;

public class ProblemaPlanTraj extends Problema{

	private EstadoLocalidade estadoFinal;
	
	public ProblemaPlanTraj(String locIni, String locFin, OperadorLigacao[] operadores){
		super(new EstadoLocalidade(locIni), operadores);
		estadoFinal = new EstadoLocalidade(locFin);
	}
	
	@Override
	public boolean objectivo(Estado estado){
		return estadoFinal.equals(estado);
	}
}
