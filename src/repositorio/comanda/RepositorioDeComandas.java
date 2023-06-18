package repositorio.comanda;

import java.util.List;

import servico.Comanda;

public interface RepositorioDeComandas {
	
	public boolean addComanda(Comanda novaComanda);
	public List<Comanda> getListaDeComandas();
	public void setListaDeComandas(List<Comanda> listaDeComandas);
	
	
}
