package HelloWorld;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

	public static void main(String[] args) {
		try {
			
			Registry referenciaServicoNomes = LocateRegistry.createRegistry(1099);
		
			InterfaceServ referenciaServidor = new ServImpl();
		
			referenciaServicoNomes.rebind("Servidor", referenciaServidor);
		
		} catch(Exception e) {}
	}

}
