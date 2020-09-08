package HelloWorld;

import java.util.UUID;

public class Empresa {
	
	private UUID id;
	private String nome;
	private int quantidadeTotalAcoes;
	
	
	public Empresa() {}
	
	/*GETS E SETS*/
	public UUID getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public int getQuantidadeTotalAcoes() {
		return quantidadeTotalAcoes;
	}
	
	
}
