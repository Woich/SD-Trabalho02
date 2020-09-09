package HelloWorld;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ordem {
	private UUID idCliente;
	private String codigoAcao;
	private double precoMaximoCompra;
	private double precoMinimoVenda;
	private LocalDateTime dataLimite;
	private int quantidade;
	
	public Ordem(UUID idCliente, String codigoAcao, double precoMaximo , double precoMinimo, int prazo) {
		this.idCliente = idCliente;
		this.codigoAcao = codigoAcao;
		this.precoMaximoCompra = precoMaximo;
		this.precoMinimoVenda = precoMinimo;
		this.dataLimite = LocalDateTime.now().plusMinutes(prazo);
	}
	
	/*GETS E SETS*/
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

	//public double getPrecoMaximoCompra() {
	//	return precoMaximoCompra;
	//}
	
	public LocalDateTime getDataLimite() {
		return this.dataLimite;
	}

	public void setPrecoMaximoCompra(double precoMaximoCompra) {
		this.precoMaximoCompra = precoMaximoCompra;
	}

	public double getPrecoMinimoVenda() {
		return precoMinimoVenda;
	}

	public void setPrecoMinimoVenda(double precoMinimoVenda) {
		this.precoMinimoVenda = precoMinimoVenda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
