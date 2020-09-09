package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServImpl extends UnicastRemoteObject implements InterfaceServ{
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	List<Interesse> listaIteresses = new ArrayList<>();
	
	public ServImpl () throws RemoteException{
		
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
	
}
