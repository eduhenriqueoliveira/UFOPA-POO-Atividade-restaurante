package servico.produto;

public class Industrializado extends Produto{
	private double custoDeCompra;
	
	public Industrializado(String nomeI, double precoI, int codigo, double custo) {
		super(nomeI, precoI, codigo);
		this.custoDeCompra = custo;
	}
	
	
	public double getCustoDeCompra() {
		return custoDeCompra;
	}

	public void setCustoDeCompra(double custoDeCompra) {
		this.custoDeCompra = custoDeCompra;
	}
}
