package HelloWorld;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

	public static void main(String[] args) {
		
		//Nao consegui achar o arquivo do how to, por isso ainda está conflitando, mas aparentemente é só colocar um inteiro como parametro
		Registry referenciaServicoNomes = LocateRegistry.createRegistry();
		
		InterfaceServ referenciaServidor = new ServImpl();
		
		referenciaServicoNomes.rebind("HelloWorld", referenciaServidor);
	}

}
