package HelloWorld;

import java.util.List;
import java.util.UUID;

public class Empresa {
	
	private UUID id;
	private String nome;
	private int quantidadeTotalAcoes;
	private List<Acao> acoes;
	
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

	public List<Acao> getAcoes() {
		return acoes;
	}
	
	
}
