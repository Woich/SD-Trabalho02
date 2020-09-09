package HelloWorld;

import java.util.UUID;

public class Interesse {
	
	private UUID idCliente;
	private Empresa empresa;
	
	public Interesse(UUID idCliente, Empresa empresa) {
		
		this.idCliente = idCliente;
		this.empresa = empresa;
		
	}
	
	/*GETS E SETS*/
	public UUID getIdCliente() {
		return idCliente;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
}
