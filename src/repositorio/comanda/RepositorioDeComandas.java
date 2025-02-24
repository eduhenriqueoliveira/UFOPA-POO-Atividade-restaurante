package repositorio.comanda;

import java.util.List;

import repositorio.produto.CodigoInvalidoException;
import servico.Comanda;

public interface RepositorioDeComandas {
	
	public boolean addComanda(Comanda novaComanda);
	
	public boolean closeComanda(Comanda comdandaParaFechar, double pagamento);
	
	public List<Comanda> getListaDeComandas() throws NenhumaComandaException;
	
	public void setListaDeComandas(List<Comanda> listaDeComandas);
	
	public Comanda getComanda(int codigo) throws CodigoInvalidoException;
}
