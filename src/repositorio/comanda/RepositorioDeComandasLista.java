package repositorio.comanda;
import servico.Comanda;
import java.util.List;
import java.util.ArrayList;

public class RepositorioDeComandasLista {
	private List<Comanda> listaDeComandas;
	
	public void exibirComandas() {
		
	}
	
	public boolean addComanda(Comanda novaComanda) {
		return true;
	}

	public List<Comanda> getListaDeComandas() {
		return listaDeComandas;
	}

	public void setListaDeComandas(List<Comanda> listaDeComandas) {
		this.listaDeComandas = listaDeComandas;
	}
}
