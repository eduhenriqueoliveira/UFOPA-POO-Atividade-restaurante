package repositorio.comanda;
import servico.Comanda;

import java.util.ArrayList;
import java.util.List;

import repositorio.produto.CodigoInvalidoException;


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
	public boolean closeComanda(Comanda comdandaParaFechar, double pagamento) {
		comdandaParaFechar.fecharComanda(pagamento);
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
	
	@Override
	public Comanda getComanda(int codigo) throws CodigoInvalidoException{
		int n = this.listaDeComandas.size();
		for(int i=0; i<n; i++) {
			if (this.listaDeComandas.get(i).getCodigoDeComanda() == codigo) {
				return this.listaDeComandas.get(i);
			}
		}
		throw new CodigoInvalidoException();
	}
	
}
