package HelloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceCli extends Remote{
	
	public void notificar(Empresa empresa, String mensagem)  throws RemoteException;
	public void listarNotificacoes()  throws RemoteException;
	
}
