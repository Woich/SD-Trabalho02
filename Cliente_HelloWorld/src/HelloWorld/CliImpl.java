package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CliImpl extends UnicastRemoteObject implements InterfaceCli{
	
	List<Notificacao> listaNotificacoes = new ArrayList<>();
	
	InterfaceServ servidor;
	UUID idCliente;
	
	public CliImpl (InterfaceServ referenciaServidor) throws RemoteException {
		this.servidor = referenciaServidor;
	}
	
	public void notificar(Empresa empresa, String mensagem) throws RemoteException 
	{
		listaNotificacoes.add(new Notificacao(empresa, mensagem));
	}
	
	public void listarNotificacoes()  throws RemoteException 
	{
		System.out.println("-----------------------------------------");
		System.out.println("EMPRESA : NOTIFICAÇÃO");
		for(Notificacao notificacao : listaNotificacoes) {
			System.out.println("-----------------------------------------");
			System.out.println(notificacao.getEmpresa().getNome()+":"+notificacao.getMensagem());
		}
		System.out.println("-----------------------------------------");
		System.out.println();
	}
	
}
