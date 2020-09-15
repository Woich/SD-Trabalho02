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
	
	public UUID getIdCliente() {
		return this.idCliente;
	}
	
	public String getCodigoAcao() {
		return this.codigoAcao;
	}
	
	public double getPrecoMaximoCompra() {
		return this.precoMaximoCompra;
	}
	
	public double getPrecoMinimoVenda() {
		return this.precoMinimoVenda;
	}
	
	public LocalDateTime getDataLimite() {
		return this.dataLimite;
	}

	public void setPrecoMaximoCompra(double precoMaximoCompra) {
		this.precoMaximoCompra = precoMaximoCompra;
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
	
	public boolean isUltrapassado() {
		return this.dataLimite.isBefore(LocalDateTime.now());
	}
	
	public boolean isVenda() {
		return this.getPrecoMinimoVenda() > 0;
	}
}
