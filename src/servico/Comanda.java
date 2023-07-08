package servico;
import java.util.List;

import servico.produto.Produto;

import java.time.LocalDateTime;

public class Comanda {
	private Mesa mesa;
	private List<Produto> pedidos;
	private int codigoDeComanda;
	private boolean status;
	private double totalAPgar;
	private double valorPago;
	private LocalDateTime dataDeAbertura;
	private LocalDateTime dataDeFechamento;
	
	public Comanda(Mesa mesa, int codigo, List<Produto> pedidosI) {
		this.mesa = mesa;
		this.pedidos = pedidosI;
		this.codigoDeComanda = codigo;
		this.status = true;
		this.totalAPgar = somatorioDosProdutos(pedidosI);
		this.valorPago = 0;
		this.dataDeAbertura = LocalDateTime.now();
	}
	
	public void fecharComanda(double valorPago) {
		this.status = false;
		this.valorPago = valorPago;
		this.dataDeFechamento = LocalDateTime.now();
	}
	
	public double somatorioDosProdutos(List<Produto> pedidos) {
		double retorno = 0;
		for (int i=0; i<pedidos.size(); i++) {
			retorno += pedidos.get(i).getPreco();
		}
		return retorno;
	}
	
	
	public void addPedidoNaComanda(List<Produto> produtos) {
		int tamanho = produtos.size();
		for(int i=0; i<tamanho; i++) {
			this.pedidos.add(produtos.get(i));
		}
		this.totalAPgar += somatorioDosProdutos(produtos);
	}

	public void atualizarPreco() {
		this.totalAPgar = somatorioDosProdutos(this.pedidos);
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
	
	public double getValorPago() {
		return this.valorPago;
	}
}
