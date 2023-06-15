package facade;
import java.util.List;

import repositorio.comanda.RepositorioDeComandasLista;
import repositorio.produto.CardapioVazioException;
import repositorio.produto.RepositorioDeProdutosLista;
import servico.Mesa;
import servico.produto.*;

import java.util.ArrayList;

public class Atendimento {
	private RepositorioDeComandasLista comandas;
	private RepositorioDeProdutosLista cardapio;
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
	
	public boolean abrirCardapio(){
		return true;
	}
	
	public boolean checarDisponibilidadeDeMesa(Mesa mesa) {
		return true;
	}

	public List<Produto> getAllProdutos() throws CardapioVazioException{
		return	cardapio.getAllProdutos();		
	}
	
	public RepositorioDeComandasLista getComandas() {
		return comandas;
	}

	public void setComandas(RepositorioDeComandasLista comandas) {
		this.comandas = comandas;
	}

	public RepositorioDeProdutosLista getCardapio() {
		return cardapio;
	}

	public void setCardapio(RepositorioDeProdutosLista cardapio) {
		this.cardapio = cardapio;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}
}
