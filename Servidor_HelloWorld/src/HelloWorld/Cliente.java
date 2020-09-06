package HelloWorld;

import java.util.ArrayList;
import java.util.UUID;

public class Cliente {
	UUID id;
	ArrayList<Acao> listaAcoes;
	ArrayList<Acao> interesses;
	
	
	public Cliente() {
		this.id = UUID.randomUUID();
		this.listaAcoes = new ArrayList<Acao>();
		this.interesses = new ArrayList<Acao>();
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
		this.listaAcoes.removeIf(x -> x.codigo == codigo);
	}
	
	public void insertInteresse(Acao acao) {
		this.interesses.add(acao);
	}
	
	public void removeInteresse(String codigo) {
		this.interesses.removeIf(x -> x.codigo == codigo);
	}
	
	public ArrayList<Acao> getCotacoes() {
		ArrayList<Acao> cotacoes = getAcoes();
		cotacoes.addAll(this.interesses);
		return cotacoes;
	}
	
}
