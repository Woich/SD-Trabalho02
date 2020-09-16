package HelloWorld;

public class Notificacao {
	private Empresa empresa;
	private String mensagem;
	
	public Notificacao(Empresa empresa, String mensagem) {
		this.empresa = empresa;
		this.mensagem = mensagem;
	}
	
	/*GETS & SETS*/
	public Empresa getEmpresa() {
		return empresa;
	}

	public String getMensagem() {
		return mensagem;
	}
}
