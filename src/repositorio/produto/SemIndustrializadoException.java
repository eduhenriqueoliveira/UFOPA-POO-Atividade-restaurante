package repositorio.produto;

import repositorio.RepositorioException;

public class SemIndustrializadoException extends RepositorioException{
	public SemIndustrializadoException() {
		super("Sem produto industrializado cadastrado no sistema.");
	}
}

