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
		Cliente cliente = new Cliente(interfaceCliente);
		
		this.clientes.add(cliente);
		
		this.clientes.forEach(x -> {
			System.out.println(x.id);
		});
		
		return cliente.getID();
	}
	
	public void registrarInteresse(UUID clienteId, Empresa empresa) throws RemoteException{
		listaIteresses.add(new Interesse(clienteId, empresa));
	}
	
	public void listarCotacoes(UUID idCliente) throws RemoteException{
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null);
		if(cliente != null) {
			cliente.getCotacoes().forEach(x -> {
				System.out.println(x.codigo + " ----> " + "R$" + formatter.format(x.preco));
			});
		}
	}
	
	public void insertCotacao(String codigo, UUID idCliente) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null);
		Acao acao = this.listaAcoes.stream().filter(x -> x.codigo == codigo).findAny().orElse(null);
		if(acao != null) {
			cliente.insertInteresse(acao);
			System.out.println("Ação adicionada com sucesso");
		}
	}
	
	public void removeCotacao(String codigo, UUID idCliente) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null);
		Acao acao = this.listaAcoes.stream().filter(x -> x.codigo == codigo).findAny().orElse(null);
		if(acao != null) {
			cliente.removeInteresse(codigo);
			System.out.println("Ação removida com sucesso");
		}
	}
	
	public void comprarAcao(String codigo, int precoMaximoCompra, int prazo, UUID idCliente) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null);
		Acao acao = cliente.getAcoes().stream().filter(x -> x.codigo == codigo).findAny().orElse(null);
		if(acao != null) {
			Ordem ordem = new Ordem(idCliente, codigo, (double)precoMaximoCompra, 0, prazo);
			this.listaOrdens.add(ordem);
			System.out.println("Ordem de compra registrada com sucesso");
		}	
	}
	
	public void venderAcao(String codigo, int precoMinimoVenda, int prazo, UUID idCliente) throws RemoteException{
		Cliente cliente = this.clientes.stream().filter(c -> c.getID() == idCliente).findAny().orElse(null);
		Acao acao = cliente.getAcoes().stream().filter(x -> x.codigo == codigo).findAny().orElse(null);
		if(acao != null) {
			Ordem ordem = new Ordem(idCliente, codigo, 0, (double)precoMinimoVenda, prazo);
			this.listaOrdens.add(ordem);
			System.out.println("Ordem de venda registrada com sucesso");
		}
	}


}
