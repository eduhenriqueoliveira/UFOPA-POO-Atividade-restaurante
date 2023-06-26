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
import servico.MesaIndisponivelException;
import servico.MesaJaCadastradaException;
import servico.MesaNaoCadastradaException;
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
		for(int i=1; i<=10; i++) {
			mesas.add(new Mesa(i));
		}
	}
	
	public boolean abrirComanda(Mesa naMesa, List<Produto> pedidos) {
		int qnt;
		for(Produto pedido : pedidos) {
			qnt = pedido.getQuantidadeDisponivel();
			pedido.setQuantidadeDisponivel(qnt-1);
		}
		int codigo;
		try {
			codigo = comandas.getListaDeComandas().size() + 1;
		} catch (NenhumaComandaException e) {
			codigo = 1;
		}
		comandas.addComanda(new Comanda(naMesa, codigo, pedidos));
		return true;
	}
	
	public boolean fecharComanda(Comanda comandaParaFechar, double pagamento) {
		return comandas.closeComanda(comandaParaFechar, pagamento);
	}
	
	public boolean checarDisponibilidadeDeMesa(Mesa mesa) throws MesaIndisponivelException{
		List<Comanda> listaComandas;
		try {
			listaComandas = comandas.getListaDeComandas();
		} catch(NenhumaComandaException ex) {
			return true;
		}
		for(int i=0; i<listaComandas.size(); i++) 
			if(mesa.getCodigoDeMesa()==listaComandas.get(i).getMesa().getCodigoDeMesa() && listaComandas.get(i).isStatus())
				throw new MesaIndisponivelException();
		return true;
	}
	
	public List<Mesa> getMesas() {
		return mesas;
	}

	public Produto getProduto(int codigo) throws CodigoInvalidoException{
		return cardapio.getProduto(codigo);
	}
	
	public Comanda getComanda(int codigo) throws CodigoInvalidoException{
		return comandas.getComanda(codigo);
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
	
	public Mesa getMesa(int codigo) throws MesaNaoCadastradaException{
		int tamanho = mesas.size();
		for(int i=0; i<tamanho; i++)
			if(mesas.get(i).getCodigoDeMesa()==codigo)
				return mesas.get(i);
		throw new MesaNaoCadastradaException();
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

	public List<Comanda> getAllComandas() throws NenhumaComandaException{
		return comandas.getListaDeComandas();
	}
	public void adicionarPedidos(Comanda comanda, List<Produto> novosPedidos) {
		double valorNovo = 0;
		for(Produto pedido:novosPedidos)
			valorNovo+=pedido.getPreco();
		comanda.adicionarPedidos(novosPedidos, valorNovo);
	}
	public void removeMesa(int codigo) {
		int idx = 0;
		for (Mesa mesa:mesas) {
			if(mesa.getCodigoDeMesa()==codigo) {
				mesas.remove(idx);
				return;
			}
			idx++;
		}

	}
	public void addMesa(int codigo) throws MesaJaCadastradaException{
		for(Mesa mesa:mesas) {
			if(mesa.getCodigoDeMesa()==codigo)
				throw new MesaJaCadastradaException();
		}
		mesas.add(new Mesa(codigo));
	}
}
