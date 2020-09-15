package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.NumberFormat;
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
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	List<Interesse> listaIteresses = new ArrayList<>();
	List<Ordem> listaOrdensVendas = new ArrayList<>();
	List<Ordem> listaOrdensCompras = new ArrayList<>();
	List<Acao> listaAcoes = new ArrayList<>();
	List<Empresa> listaEmpresas = new ArrayList<Empresa>();
	int totalEmpresas = 0;
	
	
	public ServImpl () throws RemoteException{}
	
	public synchronized UUID registrar(InterfaceCli interfaceCliente) throws RemoteException{
		Cliente cliente = new Cliente(interfaceCliente); //registra novo cliente
		
		this.clientes.add(cliente); //adiciona a lista de clientes
		return cliente.getID(); //retorna ao cliente seu ID
	}
	
	public synchronized void registrarInteresse(UUID clienteId, String codEmpresa) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == clienteId).findAny().orElse(null);//busca o cliente que fez a requisicao
		Empresa empr = this.listaEmpresas.stream().filter(e -> e.getCodigo() == codEmpresa).findAny().orElse(null); //busca a empresa informada
		
		listaIteresses.add(new Interesse(cliente, empr));//Adiciona cliente na lista de interesses
		
		if(empr != null) {
			cliente.insertInteresse(empr);//insere emrpesa a lista de interesses do cliente
		}
	}
	
	public synchronized void removeInteresse(UUID clienteId, String codEmpresa) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == clienteId).findAny().orElse(null);//busca o cliente que fez a requisicao
		Empresa empr = this.listaEmpresas.stream().filter(e -> e.getCodigo() == codEmpresa).findAny().orElse(null); //busca a empresa informada
		
		Interesse interesse = this.listaIteresses.stream().filter(i -> i.getCliente() == cliente && i.getEmpresa() == empr).findAny().orElse(null);
		
		if(interesse != null) {
			listaIteresses.remove(interesse);//Adiciona cliente na lista de interesses
		}
		
		if(empr != null) {
			cliente.removeInteresse(codEmpresa);//insere emrpesa a lista de interesses do cliente
		}
	}
	
	public void listarCotacoes(UUID idCliente) throws RemoteException{
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null); //busca o cliente que fez a requisicao
		if(cliente != null) {
			cliente.getCotacoes().forEach(x -> {
				System.out.println(x.getCodigo() + " ----> " + formatter.format(x.getPreco())); //printa todas as acoes da lista de cotacoes do cliente(codigo e preco)
			});
		}
	}
	
	public synchronized void insertCotacao(String codigo, UUID idCliente) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null); //busca o cliente que fez a requisicao
		/*Acao acao = this.listaAcoes.stream().filter(x -> x.getCodigo() == codigo).findAny().orElse(null); //busca acao com o codigo passado
		if(acao != null) {
			cliente.insertInteresse(acao);                                                              //insere acao a lista de interesses do cliente
			System.out.println("Ação adicionada com sucesso");
		}*/
	}
	
	public synchronized void removeCotacao(String codigo, UUID idCliente) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null);  //busca o cliente que fez a requisicao
		/*Acao acao = cliente.getInteresses().stream().filter(x -> x.getCodigo() == codigo).findAny().orElse(null);   //busca acao com o codigo passado na lista de interesses do cliente
		if(acao != null) {
			cliente.removeInteresse(codigo);                                                              // remove a acao da lista de interesse do cliente
			System.out.println("Ação removida com sucesso");
		}*/
	}
	
	public synchronized void comprarAcao(String codigo, int precoMaximoCompra, int prazo, UUID idCliente) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null);   //busca o cliente que fez a requisicao
		Acao acao = this.listaAcoes.stream().filter(x -> x.getCodigo() == codigo).findAny().orElse(null);    //busca acao com o codigo passado
		if(acao != null) {
			Ordem ordem = new Ordem(idCliente, codigo, (double)precoMaximoCompra, 0, prazo);                //cria nova ordem de compra
			this.listaOrdensCompras.add(ordem);                                                                    //adiciona a lista de ordens
			System.out.println("Ordem de compra registrada com sucesso");
		}	
	}
	
	public synchronized void venderAcao(String codigo, int precoMinimoVenda, int prazo, UUID idCliente) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null);   //busca o cliente que fez a requisicao
		Acao acao = cliente.getAcoes().stream().filter(x -> x.getCodigo() == codigo).findAny().orElse(null);   //busca acao com o codigo passado na lista de acoes do cliente
		if(acao != null) {
			Ordem ordem = new Ordem(idCliente, codigo, 0, (double)precoMinimoVenda, prazo);                     //cria nova ordem de compra
			this.listaOrdensVendas.add(ordem);                                                                        //adiciona a lista de ordens
			System.out.println("Ordem de venda registrada com sucesso");
		}
	}
	
	public synchronized boolean insertEmpresa(String nomeEmpresa, int qtdAcoes, UUID idCliente, boolean clienteDonoEmpresa) throws RemoteException{
		
		if(nomeEmpresa != null) {//Verifica se foi passado um empresa
			System.out.println(nomeEmpresa);
			
			Empresa empresa = new Empresa(nomeEmpresa, qtdAcoes);
			
			totalEmpresas++;//Aumenta o total de empresas
			empresa.setCodigo(empresa.getCodigo() + totalEmpresas);
			listaEmpresas.add(empresa);//Adiciona a empresa na lista de empresas
			
			if(clienteDonoEmpresa) {
				Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null);//Obtem cliente que está adicionandoa empresa
				if(cliente != null) {
					for(Acao acao : empresa.getAcoes()) {
						acao.setClienteDono(cliente);//Set de todas as ações com o dono sendo o cliente
					}
					
					cliente.getAcoes().addAll(empresa.getAcoes());//Adiciona todas as Ações da empresa para o cliente
				}
				
			}
			
			listaAcoes.addAll(empresa.getAcoes());//Adiciona todas as Ações na lista de ações
			System.out.println("Deu boa");
			return true;
		}
		
		return false;
		
	}
	
	public List<Empresa> listarEmpresas() throws RemoteException{
		return listaEmpresas;//Retorna todas as empresas
	}

	public synchronized void realizarVenda(Ordem venda, Ordem compra) throws RemoteException{
		Acao acao = listaAcoes.stream().filter(x -> x.getCodigo() == venda.getCodigoAcao()).findAny().orElse(null);
		Cliente clienteVendedor = this.clientes.stream().filter(c -> c.getID() == venda.getIdCliente()).findAny().orElse(null);   //busca o cliente que está vendendo
		Cliente clienteComprador = this.clientes.stream().filter(c -> c.getID() == compra.getIdCliente()).findAny().orElse(null);   //busca o cliente que está comprando
		Empresa empr = this.listaEmpresas.stream().filter(e -> e == acao.getEmpresa()).findAny().orElse(null); //busca a empresa informada
		if(acao.getClienteDono() != null) {
			
			clienteVendedor.getAcoes().remove(acao);
		}
		acao.setClienteDono(clienteComprador);
		clienteComprador.getAcoes().add(acao);
		
		empr.setValorEmpresa(compra.getPrecoMaximoCompra());
	}
}
