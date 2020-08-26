package HelloWorld;

public class Cliente {

	public static void main(String[] args) {
		
		Registry referenciaServicoNomes = LocateRegistry.getRegistry();
		
		InterfaceServ referenciaServidor = (InterfaceServ) referenciaServicoNomes.lookup(“HelloWorld”);
		
		new CliImpl(referenciaServidor);
	}

}
