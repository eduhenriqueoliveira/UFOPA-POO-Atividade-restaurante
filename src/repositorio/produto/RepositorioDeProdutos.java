package repositorio.produto;

import java.util.List;

import servico.produto.Industrializado;
import servico.produto.Prato;
import servico.produto.Produto;
import repositorio.produto.*;

public interface RepositorioDeProdutos{

	public List<Produto> getAllProdutos() throws CardapioVazioException;
	
	public List<Produto> getSomentePratos() throws SemPratosException;
	
	public List<Produto> getSomenteIndustrializados() throws SemIndustrializadoException;

	
	public void addNewProduto(Prato pratoNovo);
	
	public void addNewProduto(Industrializado industrializadoNovo);
}
