package HelloWorld;

public class Acao {
	String codigo;
	double preco;
	double precoDeCompra;
	
	public Acao (String codigo, double preco) {
		this.codigo = codigo;
		this.preco = preco;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public double getPreco() {
		return this.preco;
	}
	
	public void setPrecoDeCompra(double precoDeCompra) {
		this.precoDeCompra = precoDeCompra;
	}
	
	public double getPrecoDeCompra() {
		return this.precoDeCompra;
	}
	
}
