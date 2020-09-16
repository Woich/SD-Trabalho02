package HelloWorld;

import java.util.List;

public class Notificar extends Thread{
	
	private List<Empresa> listaEmpresas;//Lista de empresas
	private List<Interesse> listaInteresses;//Lista de Iteresses
	private Empresa empresa;
	
	private int tipoNotificacao;//1 - Aumento de preco geral | 2 - Diminui��o de preco geral | 3 - Ocorreu uma Venda | 4 - Vendeu A��o | 5 - Comprou a��o
	
	String notificacao;
	
	private ClienteControle cliente;
	
	//Notificar de mudan�a geral nas empresas
	public Notificar(List<Empresa> listaEmpresas, List<Interesse> listaInteresses, int tipoNotificacao) {
		this.listaInteresses = listaInteresses;
		this.listaEmpresas = listaEmpresas;
		this.tipoNotificacao = tipoNotificacao;
		this.notificacao = "[Interesse] O valor da empresa agora �: ";
		this.start();
	}
	
	//Notificar de mudan�a em um empresa apenas
	public Notificar(Empresa empresa, List<Interesse> listaInteresses) {
		this.listaInteresses = listaInteresses;
		this.empresa = empresa;
		this.tipoNotificacao = 3;
		this.notificacao = "[Interesse] O valor da empresa agora �: ";
		this.start();
	}
	
	public Notificar(ClienteControle cliente, Empresa empresa, int tipoNotificacao) {
		this.cliente = cliente;
		this.empresa = empresa;
		this.tipoNotificacao = tipoNotificacao;
		
		this.start();
	}
	
	public void run() {
		
		try {
			
			if(tipoNotificacao == 1 || tipoNotificacao == 2) {
				
				//Percorre as listas de empresa e interesse
				//Se faz isso pois a empresa na lista de interesse � apenas um referencia da empresa no momento.
				//por isso se passa o objeto mais atualizado
				for(Empresa empresa : listaEmpresas) {
					
					for(Interesse interesse : listaInteresses) {
						
						if(interesse.getEmpresa().getCodigo().equals(empresa.getCodigo()) 
								&& (empresa.getValorEmpresa() <= interesse.getValPerda() || empresa.getValorEmpresa() >= interesse.getValGanho())) {
							
							notificacao += empresa.getValorEmpresa();
							
							interesse.getCliente().getInterfaceCliente().notificar(empresa, notificacao);
						}
						
					}
					
				}
			}else if(tipoNotificacao == 3) {
				notificacao = "[Interesse] Ocorreu uma venda. O valor da empresa agora �: ";
				for(Interesse interesse : listaInteresses) {
					if(interesse.getEmpresa().getCodigo() == empresa.getCodigo()
							&& (empresa.getValorEmpresa() <= interesse.getValPerda() || empresa.getValorEmpresa() >= interesse.getValGanho())) {
						
						notificacao += empresa.getValorEmpresa();
						
						interesse.getCliente().getInterfaceCliente().notificar(empresa, notificacao);
					}
				}
				
			}else if(tipoNotificacao == 4) {
				if(cliente != null && empresa != null) {
					notificacao = "[Ordem de Venda] A a��o foi vendida por: ";
					notificacao += empresa.getValorEmpresa();
					
					cliente.getInterfaceCliente().notificar(empresa, notificacao);
				}
			}else if(tipoNotificacao == 5) {
				if(cliente != null && empresa != null) {
					notificacao = "[Ordem de Compra] A a��o foi comprada por: ";
					notificacao += empresa.getValorEmpresa();
					
					cliente.getInterfaceCliente().notificar(empresa, notificacao);
				}
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
}
