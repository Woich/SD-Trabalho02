package HelloWorld;

import java.rmi.Remote;

public interface InterfaceCli extends Remote{
	
	public void notificar(InterfaceCli texto);
	
}
