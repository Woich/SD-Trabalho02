package HelloWorld;

import java.util.UUID;

public class Interesse {
	
	private Cliente cliente;
	private Empresa empresa;
	
	public Interesse(Cliente cliente, Empresa empresa) {
		
		this.cliente = cliente;
		this.empresa = empresa;
		
	}
	
	/*GETS E SETS*/
	public Cliente getCliente() {
		return cliente;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
}
