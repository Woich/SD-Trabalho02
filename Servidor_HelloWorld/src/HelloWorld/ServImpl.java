package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServImpl extends UnicastRemoteObject implements InterfaceServ{
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	List<Interesse> listaIteresses = new ArrayList<>();
	List<Ordem> listaOrdens = new ArrayList<>();
	List<Acao> listaAcoes = new ArrayList<>();
	Acao a1 = new Acao("A1", 10);
	Acao a2 = new Acao("A2", 20);
	
	
	
	public ServImpl () throws RemoteException{
		this.listaAcoes.add(this.a1);
		this.listaAcoes.add(this.a2);
	}
	
	public synchronized UUID registrar(InterfaceCli interfaceCliente) throws RemoteException{
		Cliente cliente = new Cliente(interfaceCliente); //registra novo cliente
		
		this.clientes.add(cliente); //adiciona a lista de clientes
		
		return cliente.getID(); //retorna ao cliente seu ID
	}
	
	public void registrarInteresse(UUID clienteId, Empresa empresa) throws RemoteException{
		listaIteresses.add(new Interesse(clienteId, empresa));
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
		Acao acao = this.listaAcoes.stream().filter(x -> x.getCodigo() == codigo).findAny().orElse(null); //busca acao com o codigo passado
		if(acao != null) {
			cliente.insertInteresse(acao);                                                              //insere acao a lista de interesses do cliente
			System.out.println("Ação adicionada com sucesso");
		}
	}
	
	public synchronized void removeCotacao(String codigo, UUID idCliente) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null);  //busca o cliente que fez a requisicao
		Acao acao = cliente.getInteresses().stream().filter(x -> x.getCodigo() == codigo).findAny().orElse(null);   //busca acao com o codigo passado na lista de interesses do cliente
		if(acao != null) {
			cliente.removeInteresse(codigo);                                                              // remove a acao da lista de interesse do cliente
			System.out.println("Ação removida com sucesso");
		}
	}
	
	public synchronized void comprarAcao(String codigo, int precoMaximoCompra, int prazo, UUID idCliente) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null);   //busca o cliente que fez a requisicao
		Acao acao = this.listaAcoes.stream().filter(x -> x.getCodigo() == codigo).findAny().orElse(null);    //busca acao com o codigo passado
		if(acao != null) {
			Ordem ordem = new Ordem(idCliente, codigo, (double)precoMaximoCompra, 0, prazo);                //cria nova ordem de compra
			this.listaOrdens.add(ordem);                                                                    //adiciona a lista de ordens
			System.out.println("Ordem de compra registrada com sucesso");
		}	
	}
	
	public synchronized void venderAcao(String codigo, int precoMinimoVenda, int prazo, UUID idCliente) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null);   //busca o cliente que fez a requisicao
		Acao acao = cliente.getAcoes().stream().filter(x -> x.getCodigo() == codigo).findAny().orElse(null);   //busca acao com o codigo passado na lista de acoes do cliente
		if(acao != null) {
			Ordem ordem = new Ordem(idCliente, codigo, 0, (double)precoMinimoVenda, prazo);                     //cria nova ordem de compra
			this.listaOrdens.add(ordem);                                                                        //adiciona a lista de ordens
			System.out.println("Ordem de venda registrada com sucesso");
		}
	}


}
