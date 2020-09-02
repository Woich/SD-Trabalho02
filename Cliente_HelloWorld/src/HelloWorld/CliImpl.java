package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CliImpl extends UnicastRemoteObject implements InterfaceCli{
	public CliImpl (InterfaceCli referenciaServidor) throws RemoteException {
		notificar(referenciaServidor);
	}
	
	public void notificar(InterfaceCli texto) 
	{
		System.out.println(texto);
	}
}
