package servico.produto;

public class Produto {
	private int codigoDeProduto;
	private String nome;
	private double preco;
	private int quantidadeDisponivel;
	
	public Produto(String nomeI, double precoI, int codigo) {
		this.nome = nomeI;
		this.preco = precoI;
		this.codigoDeProduto = codigo;
	}
	
	@Override
	public String toString() {
		String disponivel;
		disponivel = (quantidadeDisponivel>0)? "Sim":"Não";
		return String.format("%-4d%-24s%-11sR$ %.2f\n", this.codigoDeProduto, this.nome, disponivel, this.preco);
	}

	public int getCodigoDeProduto() {
		return codigoDeProduto;
	}
	
	public void setCodigoDeProduto(int codigoDeProduto) {
		this.codigoDeProduto = codigoDeProduto;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}
	public void setQuantidadeDisponivel(int quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}
	
}
