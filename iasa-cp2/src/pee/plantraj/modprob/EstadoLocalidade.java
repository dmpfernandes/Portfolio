package pee.plantraj.modprob;

public class EstadoLocalidade extends Estado{
	
	private String localidade;
	
	public EstadoLocalidade(String localidade){
		this.localidade = localidade;
	}
	
	@Override
	public int hashCode(){
		return localidade.hashCode();
	}
	
	public String toString(){
		return localidade.toString();
	}
}
