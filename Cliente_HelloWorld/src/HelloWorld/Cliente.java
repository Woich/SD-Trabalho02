package HelloWorld;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class Cliente {

	public static void main(String[] args) {
		try {
			
			Registry referenciaServicoNomes = LocateRegistry.getRegistry();
			InterfaceServ referenciaServidor = (InterfaceServ) referenciaServicoNomes.lookup("HelloWorld");
			
			UUID idCliente = referenciaServidor.registrar();

			
			new CliImpl(referenciaServidor);
		
		}
		catch(Exception a) { }
	}

}
