package HelloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface InterfaceServ extends Remote{
	public UUID registrar(InterfaceCli interfaceCliente) throws RemoteException;
	public void registrarInteresse(UUID clienteId, Empresa empresa) throws RemoteException;
	public void listarCotacoes(UUID idCliente) throws RemoteException;
	public void insertCotacao(String codigo, UUID idCliente) throws RemoteException;
	public void removeCotacao(String codigo, UUID idCliente) throws RemoteException;
	public void comprarAcao(String codigo, int precoMaximoCompra, int prazo, UUID idCliente) throws RemoteException;
	public void venderAcao(String codigo, int precoMinimoVenda, int prazo, UUID idCliente) throws RemoteException;


	

}
