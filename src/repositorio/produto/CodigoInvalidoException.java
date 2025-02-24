package repositorio.produto;

import repositorio.RepositorioException;

public class CodigoInvalidoException extends RepositorioException{
	public CodigoInvalidoException() {
		super("Código de produto invalido");
	}
}
