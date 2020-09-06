package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;

public class ServImpl extends UnicastRemoteObject implements InterfaceServ{
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	public ServImpl () throws RemoteException{
		
	}
	
	public synchronized UUID registrar() throws RemoteException{
		Cliente cliente = new Cliente();
		
		this.clientes.add(cliente); //isso funciona??
		
		this.clientes.forEach(x -> {
			System.out.println(x.id);
		});
		
		return cliente.getID();
	}
	
	public void registrarInteresse(String texto, InterfaceCli referenciaCliente) throws RemoteException{
		referenciaCliente.notificar("Deu certo");
	}
	
}
