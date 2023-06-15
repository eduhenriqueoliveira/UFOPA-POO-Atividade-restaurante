package repositorio.produto;

import java.util.List;

import servico.produto.Produto;

public interface RepositorioDeProdutos{
	
	public void getSomentePratos() throws SemPratosException;
	
	public void getSomenteIndustrializados() throws SemIndustrializadoException;

	public List<Produto> getAllProdutos() throws CardapioVazioException;

	public void setProdutos(List<Produto> produtos);
}
