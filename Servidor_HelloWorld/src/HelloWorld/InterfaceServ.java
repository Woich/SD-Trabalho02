package HelloWorld;

import java.rmi.Remote;

public interface InterfaceServ extends Remote{
	public void registrarInteresse(String texto, InterfaceCli referenciaCliente);
}
