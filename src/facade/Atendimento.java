package facade;
import java.util.List;

import repositorio.RepositorioDeComandas;
import repositorio.RepositorioDeProdutos;
import servico.Mesa;

import java.util.ArrayList;

public class Atendimento {
	private RepositorioDeComandas comandas;
	private RepositorioDeProdutos cardapio;
	private List<Mesa> mesas;
	
	public Atendimento() {
		this.comandas = new RepositorioDeComandas();
		this.cardapio = new RepositorioDeProdutos();
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

	
	public RepositorioDeComandas getComandas() {
		return comandas;
	}

	public void setComandas(RepositorioDeComandas comandas) {
		this.comandas = comandas;
	}

	public RepositorioDeProdutos getCardapio() {
		return cardapio;
	}

	public void setCardapio(RepositorioDeProdutos cardapio) {
		this.cardapio = cardapio;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}
}
