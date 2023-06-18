package servico;
import java.util.List;

import servico.produto.Produto;

import java.util.ArrayList;
import java.time.LocalDateTime;
public class Comanda {
	private Mesa mesa;
	private List<Produto> pedidos;
	private int codigoDeComanda;
	private boolean status;
	private double totalAPgar;
	private LocalDateTime dataDeAbertura;
	private LocalDateTime dataDeFechamento;
	
	public boolean addPedido(Produto pedido) {
		return true;
	}
	
	public double totalAPagar() {
		return 0;
	}

	
	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public List<Produto> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Produto> pedidos) {
		this.pedidos = pedidos;
	}

	public int getCodigoDeComanda() {
		return codigoDeComanda;
	}

	public void setCodigoDeComanda(int codigoDeComanda) {
		this.codigoDeComanda = codigoDeComanda;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getTotalAPagar() {
		return totalAPgar;
	}

	public void setTotalAPagar(double totalAPgar) {
		this.totalAPgar = totalAPgar;
	}

	public LocalDateTime getDataDeAbertura() {
		return dataDeAbertura;
	}

	public void setDataDeAbertura(LocalDateTime dataDeAbertura) {
		this.dataDeAbertura = dataDeAbertura;
	}

	public LocalDateTime getDataDeFechamento() {
		return dataDeFechamento;
	}

	public void setDataDeFechamento(LocalDateTime dataDeFechamento) {
		this.dataDeFechamento = dataDeFechamento;
	}
	
}
