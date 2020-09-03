package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServImpl extends UnicastRemoteObject implements InterfaceServ{
	public ServImpl () throws RemoteException{
		
	}
	
	public void registrarInteresse(String texto, InterfaceCli referenciaCliente) {
		referenciaCliente.notificar("Deu certo");
	}
	
}
