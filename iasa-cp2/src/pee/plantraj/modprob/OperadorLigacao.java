package pee.plantraj.modprob;

public class OperadorLigacao implements Operador{
	
	private int custoLigacao;
	private EstadoLocalidade estadoOrigem;
	private EstadoLocalidade estadoDestino;
	//TODO private Estado estadoOrigem, estadoDestino; 
	
	public OperadorLigacao(String locIni, String locFin, int custo){
		this.estadoOrigem = new EstadoLocalidade(locIni);
		this.estadoDestino = new EstadoLocalidade(locFin);
		this.custoLigacao = custo;
	}
	
	@Override
	public Estado aplicar(Estado estado){
		if(this.estadoOrigem.equals(estado)) //se for um estado de origem pode ir para o destino
			return estadoDestino;
		return null;
	}
	
	@Override
	public float custo(Estado estado, Estado estado_suc){
		return this.custoLigacao;
	}
}
