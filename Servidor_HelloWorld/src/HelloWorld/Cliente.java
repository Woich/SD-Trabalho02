package HelloWorld;

import java.util.ArrayList;
import java.util.UUID;

/*
 * 
 * Autores:
 * Lucas Shoiti (lucastakahashi@alunos.utfpr.edu.br)
 * Pedro Henrique Woiciechovski (minewoichbr@gmail.com)
 * 
 * */
public class Cliente {
	private UUID id;
	private ArrayList<Acao> listaAcoes;
	private ArrayList<Empresa> interesses;
	private InterfaceCli interfaceCliente;
	
	
	public Cliente() {
		this.id = UUID.randomUUID();
		this.listaAcoes = new ArrayList<Acao>(0);
		this.interesses = new ArrayList<Empresa>(0);
	}
	
	public Cliente(InterfaceCli interfaceCliente) {
		this.id = UUID.randomUUID();
		this.listaAcoes = new ArrayList<Acao>(0);
		this.interesses = new ArrayList<Empresa>(0);
		this.interfaceCliente = interfaceCliente;
	}
	
	public UUID getID() {
		return this.id;
	}
	
	public ArrayList<Acao> getAcoes() {
		return this.listaAcoes;
	}
	
	public ArrayList<Empresa> getInteresses() {
		return this.interesses;
	}
	
	public void insertAcao(Acao acao) {
		this.listaAcoes.add(acao);
	}
	
	public void removeAcao(String codigo) {
		this.listaAcoes.removeIf(x -> x.getCodigo() == codigo);
	}
	
	public void insertInteresse(Empresa empresa) {
		this.interesses.add(empresa);
	}
	
	public void removeInteresse(String codEmpresa) {
		this.interesses.removeIf(x -> x.getCodigo() == codEmpresa); //remove interesse da lista 
	}
	
	public ArrayList<Acao> getCotacoes() {
		ArrayList<Acao> cotacoes = getAcoes();
		//cotacoes.addAll(this.interesses.get);
		return cotacoes;
	}

	public ArrayList<Acao> getListaAcoes() {
		return listaAcoes;
	}

	public InterfaceCli getInterfaceCliente() {
		return interfaceCliente;
	}
	
}
