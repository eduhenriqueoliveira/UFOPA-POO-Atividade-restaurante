package repositorio.produto;
import java.util.List;
import java.util.ArrayList;

import servico.produto.Industrializado;
import servico.produto.Prato;
import servico.produto.Produto;

public class RepositorioDeProdutosLista implements RepositorioDeProdutos{
	private List<Produto> produtos;
	
	public RepositorioDeProdutosLista() {
		produtos = new ArrayList<Produto>();
	}

	
	@Override
	public List<Produto> getAllProdutos() throws CardapioVazioException{
		if(produtos.size() == 0)
			throw new CardapioVazioException();
		return produtos;
	}

	@Override
	public List<Produto> getSomentePratos() throws SemPratosException{
		List<Produto> listaDeRetorno = new ArrayList<Produto>();
		
		int tamanhoDoCardapio = produtos.size();
		for(int i=0; i<tamanhoDoCardapio; i++) {
			if(produtos.get(i) instanceof Prato) {
				listaDeRetorno.add(produtos.get(i));
			}
		}
		if (listaDeRetorno.size()==0)
			throw new SemPratosException();
		return listaDeRetorno;
	}
	
	@Override
	public List<Produto> getSomenteIndustrializados() throws SemIndustrializadoException{
		List<Produto> listaDeRetorno = new ArrayList<Produto>();
		
		int tamanhoDoCardapio = produtos.size();
		for(int i=0; i<tamanhoDoCardapio; i++) {
			if(produtos.get(i) instanceof Industrializado) {
				listaDeRetorno.add(produtos.get(i));
			}
		}
		if (listaDeRetorno.size()==0)
			throw new SemIndustrializadoException();
		return listaDeRetorno;
	}

	
	@Override
	public void addNewProduto(Prato pratoNovo) {
		this.produtos.add(pratoNovo);
	}

	@Override
	public void addNewProduto(Industrializado industrializadoNovo) {
		this.produtos.add(industrializadoNovo);
		
	}
	
}
