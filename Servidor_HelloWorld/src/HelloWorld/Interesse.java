package HelloWorld;

public class Interesse {
	
	private ClienteControle cliente;
	private Empresa empresa;
	
	public Interesse(ClienteControle cliente, Empresa empresa) {
		
		this.cliente = cliente;
		this.empresa = empresa;
		
	}
	
	/*GETS E SETS*/
	public ClienteControle getCliente() {
		return cliente;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
}
