package HelloWorld;

import java.util.ArrayList;
import java.util.UUID;

public class Cliente {
	private UUID id;
	private ArrayList<Acao> listaAcoes;
	private ArrayList<Acao> interesses;
	private InterfaceCli interfaceCliente;
	
	
	public Cliente() {
		this.id = UUID.randomUUID();
		this.listaAcoes = new ArrayList<Acao>();
		this.interesses = new ArrayList<Acao>();
	}
	
	public Cliente(InterfaceCli interfaceCliente) {
		this.id = UUID.randomUUID();
		this.listaAcoes = new ArrayList<Acao>();
		this.interesses = new ArrayList<Acao>();
		this.interfaceCliente = interfaceCliente;
	}
	
	public UUID getID() {
		return this.id;
	}
	
	public ArrayList<Acao> getAcoes() {
		return this.listaAcoes;
	}
	
	public ArrayList<Acao> getInteresses() {
		return this.interesses;
	}
	
	public void insertAcao(Acao acao) {
		this.listaAcoes.add(acao);
	}
	
	public void removeAcao(String codigo) {
		this.listaAcoes.removeIf(x -> x.getCodigo() == codigo);
	}
	
	public void insertInteresse(Acao acao) {
		this.interesses.add(acao);
	}
	
	public void removeInteresse(String codigo) {
		this.interesses.removeIf(x -> x.getCodigo() == codigo); //remove interesse da lista 
	}
	
	public ArrayList<Acao> getCotacoes() {
		ArrayList<Acao> cotacoes = getAcoes();
		cotacoes.addAll(this.interesses);
		return cotacoes;
	}
	
}
