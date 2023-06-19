package servico;

public class MesaIndisponivelException extends ServicoException{
	public MesaIndisponivelException() {
		super("Mesa indisponivel, comanda previamente aberta nela.");
	}
}
