package HelloWorld;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {

	public static void main(String[] args) {
		try {
			
			Registry referenciaServicoNomes = LocateRegistry.getRegistry();
			InterfaceServ referenciaServidor = (InterfaceServ) referenciaServicoNomes.lookup("HelloWorld");
		
			new CliImpl(referenciaServidor);
		
		}
		catch(Exception a) { }
	}

}
