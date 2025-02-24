package servico.produto;

public class Prato extends Produto{
	private double custoDeProducao;
	public Prato(String nomeI, double precoI, int codigo, double custo) {
		super(nomeI, precoI, codigo);
		this.custoDeProducao = custo;
	}

	public double getCustoDeProducao() {
		return custoDeProducao;
	}

	public void setCustoDeProducao(double custoDeProducao) {
		this.custoDeProducao = custoDeProducao;
	}
}
