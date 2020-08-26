package HelloWorld;

public class Servidor {

	public static void main(String[] args) {
		
		Registry referenciaServicoNomes = LocateRegistry.createRegistry();
		
		InterfaceServ referenciaServidor = new ServImpl();
		
		referenciaServicoNomes.rebind(“HelloWorld”, referenciaServidor);
	}

}
