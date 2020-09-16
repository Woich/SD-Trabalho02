package HelloWorld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/*
 * 
 * Autores:
 * Lucas Shoiti (lucastakahashi@alunos.utfpr.edu.br)
 * Pedro Henrique Woiciechovski (minewoichbr@gmail.com)
 * 
 * */
public class ClienteControle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private InterfaceCli interfaceCliente;
	
	
	public ClienteControle() {
		this.id = UUID.randomUUID();
	}
	
	public ClienteControle(InterfaceCli interfaceCliente) {
		this.id = UUID.randomUUID();
		this.interfaceCliente = interfaceCliente;
	}
	
	public UUID getID() {
		return this.id;
	}

	public InterfaceCli getInterfaceCliente() {
		return interfaceCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteControle other = (ClienteControle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
