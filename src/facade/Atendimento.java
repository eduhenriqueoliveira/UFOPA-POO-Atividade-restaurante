package facade;
import java.util.ArrayList;
import java.util.List;

import repositorio.comanda.NenhumaComandaException;
import repositorio.comanda.RepositorioDeComandas;
import repositorio.comanda.RepositorioDeComandasLista;
import repositorio.produto.CardapioVazioException;
import repositorio.produto.CodigoInvalidoException;
import repositorio.produto.RepositorioDeProdutos;
import repositorio.produto.RepositorioDeProdutosLista;
import repositorio.produto.SemIndustrializadoException;
import repositorio.produto.SemPratosException;

import servico.Comanda;

import servico.Mesa;
import servico.produto.Industrializado;
import servico.produto.Prato;
import servico.produto.Produto;

public class Atendimento {
	private RepositorioDeComandas comandas;
	private RepositorioDeProdutos cardapio;
	private List<Mesa> mesas;
	
	public Atendimento() {
		this.comandas = new RepositorioDeComandasLista();
		this.cardapio = new RepositorioDeProdutosLista();
		this.mesas = new ArrayList<Mesa>();
	}
	
	public boolean abrirComanda(Mesa naMesa) {
		return true;
	}
	
	public boolean fecharComanda(Mesa naMesa, double pagamento) {
		return true;
	}
	
	public boolean checarDisponibilidadeDeMesa(Mesa mesa) {
		return true;
	}

	public Produto getProduto(int codigo) throws CodigoInvalidoException{
		return cardapio.getProduto(codigo);
	}
	
	public void cadastrarProduto(Prato novo){
		cardapio.addNewProduto(novo);
	}
	public void cadastrarProduto(Industrializado novo){
		cardapio.addNewProduto(novo);
	}
	
	public void removeProdutoCadastrado(int codigoDoProduto) throws CodigoInvalidoException{
		cardapio.removerProduto(codigoDoProduto);
	}
	
	public List<Produto> getAllProdutos() throws CardapioVazioException{
		return cardapio.getAllProdutos();		
	}

	public List<Produto> getSomentePratos() throws SemPratosException{
		return cardapio.getSomentePratos();
	}
	public List<Produto> getSomenteIndustrializados() throws SemIndustrializadoException{
		return cardapio.getSomenteIndustrializados();		
	}
	
	public List<Mesa> getMesas() {
		return mesas;
	}

	public List<Comanda> getAllComandas() throws NenhumaComandaException{
		return comandas.getListaDeComandas();
		
	}

}
