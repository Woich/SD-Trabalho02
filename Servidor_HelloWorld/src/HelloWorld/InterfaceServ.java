package HelloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface InterfaceServ extends Remote{
	public UUID registrar() throws RemoteException;
	public void registrarInteresse(String texto, InterfaceCli referenciaCliente) throws RemoteException;
}
