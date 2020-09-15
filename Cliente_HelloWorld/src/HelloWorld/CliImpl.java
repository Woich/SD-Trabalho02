package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class CliImpl extends UnicastRemoteObject implements InterfaceCli{
	
	InterfaceServ servidor;
	UUID idCliente;
	
	public CliImpl (InterfaceServ referenciaServidor) throws RemoteException {
		this.servidor = referenciaServidor;
	}
	
	public void notificar(String texto)  throws RemoteException 
	{
		System.out.println(texto);
	}
	
}
