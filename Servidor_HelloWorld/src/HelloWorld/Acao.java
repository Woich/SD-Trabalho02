package HelloWorld;

import java.io.Serializable;

public class Acao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private double preco;
	private double precoDeCompra;
	Empresa empresa;
	Cliente clienteDono;
	
	public Acao (String codigo, double preco) {
		this.codigo = codigo;
		this.preco = preco;
		this.precoDeCompra = 0;
		this.clienteDono = null;
	}
	
	public Acao (String codigo, double preco, Empresa empresa) {
		this.codigo = codigo;
		this.preco = preco;
		this.precoDeCompra = 0;
		this.empresa = empresa;
		this.clienteDono = null;
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

	public Cliente getClienteDono() {
		return clienteDono;
	}

	public void setClienteDono(Cliente clienteDono) {
		this.clienteDono = clienteDono;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
}
