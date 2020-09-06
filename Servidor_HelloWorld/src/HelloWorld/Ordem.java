package HelloWorld;

import java.util.UUID;

public class Ordem {
	UUID idCliente;
	String codigoAcao;
	double precoMaximoCompra;
	double precoMinimoVenda; 
	
	public Ordem(UUID idCliente, String codigoAcao, double precoMaximo , double precoMinimo) {
		this.idCliente = idCliente;
		this.codigoAcao = codigoAcao;
		this.precoMaximoCompra = precoMaximo;
		this.precoMinimoVenda = precoMinimo;
	}
	
	public void setIdCliente(UUID idCliente) {
		this.idCliente = idCliente;
	}
	
	public void setCodigoAcao(String codigoAcao) {
		this.codigoAcao = codigoAcao;
	}
	
	public void setPrecoMaximo(double precoMaximo) {
		this.precoMaximoCompra = precoMaximo;
	}
	
	public void setPrecoMinimo(double precoMinimo) {
		this.precoMinimoVenda = precoMinimo;
	}
	
	public UUID getIdCliente() {
		return this.idCliente;
	}
	
	public String getCodigoAcao() {
		return this.codigoAcao;
	}
	
	public double getPrecoMaximo() {
		return this.precoMaximoCompra;
	}
	
	public double getPrecoMinimo() {
		return this.precoMinimoVenda;
	}
}
