package HelloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface InterfaceServ extends Remote{
	public UUID registrar(InterfaceCli interfaceCliente) throws RemoteException;
	public void registrarInteresse(UUID clienteId, String codEmpresa) throws RemoteException;
	public void removeInteresse(UUID clienteId, String codEmpresa) throws RemoteException;
	public void listarCotacoes(UUID idCliente) throws RemoteException;
	public void insertCotacao(double valorCotacao) throws RemoteException;
	public boolean insertEmpresa(String nomeEmpresa, int qtdAcoes, UUID idCliente, boolean clienteDonoEmpresa) throws RemoteException;
	public void removeCotacao(double valorCotacao) throws RemoteException;
	public void comprarAcao(String codigo, int precoMaximoCompra, int prazo, UUID idCliente, int qtdComprar) throws RemoteException;
	public void venderAcao(String codigo, int precoMinimoVenda, int prazo, UUID idCliente, int qtdVendas) throws RemoteException;
	public List<Empresa> listarEmpresas() throws RemoteException;
	public void realizarVenda(Ordem venda, Ordem compra) throws RemoteException;
	public List<Acao> listarAcoesCliente(UUID idCliente) throws RemoteException;
	public List<Empresa> listarEmpresasInteressado(UUID idCliente) throws RemoteException;
	public void excluirCompra() throws RemoteException;
	public void excluirVenda() throws RemoteException;

}
