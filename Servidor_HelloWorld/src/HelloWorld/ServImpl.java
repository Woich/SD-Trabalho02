package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * 
 * Autores:
 * Lucas Shoiti (lucastakahashi@alunos.utfpr.edu.br)
 * Pedro Henrique Woiciechovski (minewoichbr@gmail.com)
 * 
 * */
public class ServImpl extends UnicastRemoteObject implements InterfaceServ{
	ArrayList<ClienteControle> listaClientes = new ArrayList<ClienteControle>();
	List<Interesse> listaInteresses = new ArrayList<>();
	List<Ordem> listaOrdensVendas = new ArrayList<>();
	List<Ordem> listaOrdensCompras = new ArrayList<>();
	List<Acao> listaAcoes = new ArrayList<>();
	List<Empresa> listaEmpresas = new ArrayList<Empresa>();
	int totalEmpresas = 0;
	
	
	public ServImpl () throws RemoteException{}
	
	public synchronized UUID registrar(InterfaceCli interfaceCliente) throws RemoteException{
		ClienteControle cliente = new ClienteControle(interfaceCliente); //registra novo cliente
		
		this.listaClientes.add(cliente); //adiciona a lista de clientes
		return cliente.getID(); //retorna ao cliente seu ID
	}
	
	public synchronized void registrarInteresse(UUID clienteId, String codEmpresa) throws RemoteException{
		ClienteControle cliente = this.listaClientes.stream().filter(c -> c.getID().equals(clienteId)).findAny().orElse(null);//busca o cliente que fez a requisicao
		Empresa empr = this.listaEmpresas.stream().filter(e -> e.getCodigo().equals(codEmpresa)).findAny().orElse(null); //busca a empresa informada
		
		listaInteresses.add(new Interesse(cliente, empr));//Adiciona cliente na lista de interesses
	}
	
	public synchronized void removeInteresse(UUID clienteId, String codEmpresa) throws RemoteException{
		ClienteControle cliente = this.listaClientes.stream().filter(c -> c.getID().equals(clienteId)).findAny().orElse(null);//busca o cliente que fez a requisicao
		Empresa empr = this.listaEmpresas.stream().filter(e -> e.getCodigo().equals(codEmpresa)).findAny().orElse(null); //busca a empresa informada
		
		Interesse interesse = this.listaInteresses.stream().filter(i -> i.getCliente().equals(cliente) && i.getEmpresa().equals(empr)).findAny().orElse(null);
		
		if(interesse != null) {
			listaInteresses.remove(interesse);//remove da lista de interesses
		}
	}
	
	public void listarCotacoes(UUID idCliente) throws RemoteException{
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		ClienteControle cliente = this.listaClientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null); //busca o cliente que fez a requisicao
		if(cliente != null) {}
	}
	
	public synchronized void insertCotacao(double valorCotacao) throws RemoteException{
		for(Empresa empresa : listaEmpresas) {
			empresa.setValorEmpresa(empresa.getValorEmpresa() + valorCotacao);
		}
		
		new Notificar(listaEmpresas, listaInteresses, 1);
	}
	
	public synchronized void removeCotacao(double valorCotacao) throws RemoteException{
		for(Empresa empresa : listaEmpresas) {
			empresa.setValorEmpresa(empresa.getValorEmpresa() - valorCotacao);
		}
		
		new Notificar(listaEmpresas, listaInteresses, 2);
	}
	
	public synchronized void comprarAcao(String codigo, int precoMaximoCompra, int prazo, UUID idCliente) throws RemoteException{
		//Inicia limpando as listas de possíveis ordens que expiraram
		excluirCompra();
		excluirVenda();
		
		Empresa emrpresa = listaEmpresas.stream().filter(x -> x.getCodigo().equals(codigo)).findAny().orElse(null);   //busca empresa com o codigo passado na lista de acoes do cliente
		if(emrpresa != null) {
			Ordem ordem = new Ordem(idCliente, codigo, (double)precoMaximoCompra, prazo);                //cria nova ordem de compra
			this.listaOrdensCompras.add(ordem);                                                                    //adiciona a lista de ordens
			System.out.println(listaOrdensCompras.size());
			checkOrdens();
		}	
	}
	
	public synchronized void venderAcao(String codigo, int precoMinimoVenda, int prazo, UUID idCliente) throws RemoteException{
		//Inicia limpando as listas de possíveis ordens que expiraram
		excluirCompra();
		excluirVenda();
		
		ClienteControle cliente = this.listaClientes.stream().filter(c -> c.getID().equals(idCliente)).findAny().orElse(null);   //busca o cliente que fez a requisicao
		Acao acao = listaAcoes.stream().filter(x -> x.getCodigo().equals(codigo) && x.getClienteDono().equals(cliente)).findAny().orElse(null);   //busca acao com o codigo passado na lista de acoes do cliente
		if(acao != null) {
			Ordem ordem = new Ordem(idCliente, codigo, 0, (double)precoMinimoVenda, prazo);                     //cria nova ordem de compra
			this.listaOrdensVendas.add(ordem);                                                                        //adiciona a lista de ordens
			System.out.println(listaOrdensVendas.size());
			checkOrdens();
		}
	}
	
	public synchronized boolean insertEmpresa(String nomeEmpresa, int qtdAcoes, UUID idCliente, boolean clienteDonoEmpresa) throws RemoteException{
		
		if(nomeEmpresa != null) {//Verifica se foi passado um empresa
			
			Empresa empresa = new Empresa(nomeEmpresa, qtdAcoes);
			
			totalEmpresas++;//Aumenta o total de empresas
			empresa.setCodigo(empresa.getCodigo() + totalEmpresas);
			empresa.gerarAcoes();
			listaEmpresas.add(empresa);//Adiciona a empresa na lista de empresas
			
			if(clienteDonoEmpresa) {
				ClienteControle cliente = this.listaClientes.stream().filter(c -> c.getID().equals(idCliente)).findAny().orElse(null);//Obtem cliente que está adicionandoa empresa
				if(cliente != null) {
					for(Acao acao : empresa.getAcoes()) {
						acao.setClienteDono(cliente);//Set de todas as ações com o dono sendo o cliente
					}
				}
				
			}
			
			listaAcoes.addAll(empresa.getAcoes());//Adiciona todas as Ações na lista de ações
			return true;
		}
		
		return false;
		
	}
	
	public List<Empresa> listarEmpresas() throws RemoteException{
		return listaEmpresas;//Retorna todas as empresas
	}

	public synchronized void realizarVenda(Ordem venda, Ordem compra) throws RemoteException{
		Acao acao = listaAcoes.stream().filter(x -> x.getCodigo().equals(venda.getCodigoAcao())).findAny().orElse(null);
		ClienteControle clienteComprador = this.listaClientes.stream().filter(c -> c.getID().equals(compra.getIdCliente())).findAny().orElse(null);   //busca o cliente que está comprando
		Empresa empr = this.listaEmpresas.stream().filter(e -> e.getCodigo().equals(compra.getCodigoEmpresa())).findAny().orElse(null); //busca a empresa informada

		acao.setClienteDono(clienteComprador);
		
		empr.setValorEmpresa(compra.getPrecoMaximoCompra());
		
		new Notificar(empr, listaInteresses);
	}
	
	public List<Acao> listarAcoesCliente(UUID idCliente) throws RemoteException{
		List<Acao> acoesDoCliente = new ArrayList<Acao>(0);
		
		for(Acao acao :listaAcoes) {
			if(acao.getClienteDono().getID().equals(idCliente)) {
				acoesDoCliente.add(acao);
			}
		}
		
		return acoesDoCliente.size() > 0 ? acoesDoCliente : null; //Caso a lista seja maior que zero retorna a lista, senão retorna null
	}
	
	public List<Empresa> listarEmpresasInteressado(UUID idCliente) throws RemoteException{
		List<Empresa> empresaInteressado = new ArrayList<Empresa>(0);
		
		for(Interesse inte : listaInteresses) {
			if(inte.getCliente().getID().equals(idCliente)) {
				empresaInteressado.add(inte.getEmpresa());
			}
		}
		
		return empresaInteressado.size() > 0 ? empresaInteressado : null; //Caso a lista seja maior que zero retorna a lista, senão retorna null
	}

	@Override
	public void excluirCompra() throws RemoteException {
		if(listaOrdensCompras != null && listaOrdensCompras.size() > 0) {
			for(Ordem item : listaOrdensCompras){
				if(item.getDataLimite().isBefore(LocalDateTime.now())) {
					listaOrdensCompras.remove(item);
				}
			}
		}
		
	}

	@Override
	public void excluirVenda() throws RemoteException {
		if(listaOrdensVendas != null && listaOrdensVendas.size() > 0) {
			for(Ordem item : listaOrdensVendas){
				if(item.getDataLimite().isBefore(LocalDateTime.now())) {
					listaOrdensVendas.remove(item);
				}
			}
		}
	}
	
	private void checkOrdens() {
		try {
			if(listaOrdensCompras != null && listaOrdensCompras.size() > 0 && listaOrdensVendas != null && listaOrdensVendas.size() > 0) {
				for(Ordem compra : listaOrdensCompras) {
					for(Ordem venda : listaOrdensVendas) {
						if(venda.getCodigoAcao().substring(0, 4).equals(compra.getCodigoEmpresa())) {
							realizarVenda(venda, compra);
							listaOrdensCompras.remove(compra);
							listaOrdensVendas.remove(venda);
						}
					}
				}
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
