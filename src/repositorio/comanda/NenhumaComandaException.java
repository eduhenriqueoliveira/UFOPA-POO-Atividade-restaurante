package repositorio.comanda;

import repositorio.RepositorioException;

public class NenhumaComandaException extends RepositorioException{
	public NenhumaComandaException() {
		super("Nenhuma comanda cadastrada no sistema");
	}
}
