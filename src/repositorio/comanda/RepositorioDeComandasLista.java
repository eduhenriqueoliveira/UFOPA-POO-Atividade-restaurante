package repositorio.comanda;
import servico.Comanda;

import java.util.ArrayList;
import java.util.List;


public class RepositorioDeComandasLista implements RepositorioDeComandas{
	
	private List<Comanda> listaDeComandas;
	public RepositorioDeComandasLista() {
		this.listaDeComandas = new ArrayList<Comanda>();
	}
	
	@Override
	public boolean addComanda(Comanda novaComanda) {
		return true;
	}

	@Override
	public List<Comanda> getListaDeComandas() throws NenhumaComandaException{
		if (listaDeComandas.size() ==0)
			throw new NenhumaComandaException();
		return listaDeComandas;
	}

	@Override
	public void setListaDeComandas(List<Comanda> listaDeComandas) {
		this.listaDeComandas = listaDeComandas;
	}
	
}
