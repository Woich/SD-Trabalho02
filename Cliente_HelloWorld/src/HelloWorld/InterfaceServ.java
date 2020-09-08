package HelloWorld;

import java.rmi.Remote;
import java.util.UUID;

public interface InterfaceServ extends Remote{
	public UUID registrar(CliImpl cliImpl);
	public void registrarInteresse(String texto, InterfaceCli referenciaCliente);
}
