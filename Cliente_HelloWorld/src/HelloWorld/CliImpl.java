package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CliImpl extends UnicastRemoteObject implements InterfaceCli{
	public CliImpl (InterfaceServ referenciaServidor) throws RemoteException {
		referenciaServidor.registrarInteresse("Oi", this);
	}
	
	public void notificar(String texto) 
	{
		System.out.println(texto);
	}
}
