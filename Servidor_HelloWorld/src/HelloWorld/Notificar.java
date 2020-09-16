package HelloWorld;

import java.util.List;

public class Notificar extends Thread{
	
	private List<Empresa> listaEmpresas;//Lista de empresas
	private List<Interesse> listaInteresses;//Lista de Iteresses
	private Empresa empresa;
	
	private int tipoNotificacao;//1 - Aumento de preco geral | 2 - Diminuição de preco geral | 3 - Ocorreu uma Venda
	
	String notificacao;
	
	//Notificar de mudança geral nas empresas
	public Notificar(List<Empresa> listaEmpresas, List<Interesse> listaInteresses, int tipoNotificacao) {
		this.listaInteresses = listaInteresses;
		this.listaEmpresas = listaEmpresas;
		this.tipoNotificacao = tipoNotificacao;
		this.start();
	}
	
	//Notificar de mudança em um empresa apenas
	public Notificar(Empresa empresa, List<Interesse> listaInteresses) {
		this.listaInteresses = listaInteresses;
		this.empresa = empresa;
		this.tipoNotificacao = 3;
		this.start();
	}
	
	public void run() {
		
		try {
			
			if(tipoNotificacao == 1) {
				
				notificacao = "Ocorreu um aumento de preço na empresa";
				
				//Percorre as listas de empresa e interesse
				//Se faz isso pois a empresa na lista de interesse é apenas um referencia da empresa no momento.
				//por isso se passa o objeto mais atualizado
				for(Empresa empresa : listaEmpresas) {
					
					for(Interesse interesse : listaInteresses) {
						
						if(interesse.getEmpresa().getCodigo() == empresa.getCodigo()) {
							interesse.getCliente().getInterfaceCliente().notificar(empresa, notificacao);
						}
						
					}
					
				}
				
			}else if(tipoNotificacao == 2){
				notificacao = "Ocorreu uma diminuição de preço na empresa";
				
				//Percorre as listas de empresa e interesse
				//Se faz isso pois a empresa na lista de interesse é apenas um referencia da empresa no momento.
				//por isso se passa o objeto mais atualizado
				for(Empresa empresaItem : listaEmpresas) {
					
					for(Interesse interesse : listaInteresses) {
						
						if(interesse.getEmpresa().getCodigo() == empresaItem.getCodigo()) {
							interesse.getCliente().getInterfaceCliente().notificar(empresaItem, notificacao);
						}
						
					}
					
				}
			}else if(tipoNotificacao == 3) {
				notificacao = "Ocorreu uma venda dessa empresa";
				for(Interesse interesse : listaInteresses) {
					if(interesse.getEmpresa().getCodigo() == empresa.getCodigo()) {
						interesse.getCliente().getInterfaceCliente().notificar(empresa, notificacao);
					}
				}
				
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
}
