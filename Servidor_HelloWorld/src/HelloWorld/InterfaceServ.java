package HelloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface InterfaceServ extends Remote{
	public UUID registrar(InterfaceCli interfaceCliente) throws RemoteException;
	public void registrarInteresse(UUID clienteId, Acao acao) throws RemoteException;
}
