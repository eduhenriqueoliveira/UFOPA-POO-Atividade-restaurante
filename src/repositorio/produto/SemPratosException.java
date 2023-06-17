package repositorio.produto;
import repositorio.RepositorioException;
public class SemPratosException extends RepositorioException{
	public SemPratosException() {
		super("Sem prato cadastrado no sistema.");
	}
}
