package pee.plantraj.modprob;

public abstract class Estado{

	public boolean equals(Object obj){
		//igualar o objeto a 
		//Identidade a um objeto? o que destingue os diferentes objetos
		//como defino identidade de um objeto? identidade do objeto tem que ser unico
		// identidade do objeto é o endereco - artificial
		
		// Dois tipos de Identidade: identificacao por conteudo e por referencia
		
		// hashCode é unico para distinguir os objetos tem o mesmo conteudo
		if(obj instanceof Estado){
			if( obj.hashCode() == hashCode())
				return true;
			
		}
		return false;
	}
	
	public abstract int hashCode();
}
