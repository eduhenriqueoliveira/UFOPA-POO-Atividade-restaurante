package repositorio.comanda;
import servico.Comanda;

import java.util.List;


public class RepositorioDeComandasLista implements RepositorioDeComandas{
	
	private List<Comanda> listaDeComandas;
	
	@Override
	public boolean addComanda(Comanda novaComanda) {
		return true;
	}

	@Override
	public List<Comanda> getListaDeComandas() {
		return listaDeComandas;
	}

	@Override
	public void setListaDeComandas(List<Comanda> listaDeComandas) {
		this.listaDeComandas = listaDeComandas;
	}
	
}
