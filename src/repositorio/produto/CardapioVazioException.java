package repositorio.produto;
import repositorio.RepositorioException;

public class CardapioVazioException extends RepositorioException{
	public CardapioVazioException() {
		super("Cardápio vazio");
	}
}
