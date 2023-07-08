package servico;

public class ProdutoNaoPedidoException extends ServicoException{
	public ProdutoNaoPedidoException() {
		super("Produto não foi pedido nesta comanda");
	}
}
