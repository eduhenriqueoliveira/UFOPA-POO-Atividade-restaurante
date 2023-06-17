package repositorio.produto;
import java.util.List;
import java.util.ArrayList;
import servico.produto.Produto;

public class RepositorioDeProdutosLista implements RepositorioDeProdutos{
	private List<Produto> produtos;
	
	public RepositorioDeProdutosLista() {
		produtos = new ArrayList<Produto>();
	}
	
	public void getSomentePratos() throws SemPratosException {
		
	}
	
	public void getSomenteIndustrializados() throws SemIndustrializadoException {
		
	}
	@Override
	public List<Produto> getAllProdutos() throws CardapioVazioException{
		if(produtos.size() == 0)
			throw new CardapioVazioException();
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
