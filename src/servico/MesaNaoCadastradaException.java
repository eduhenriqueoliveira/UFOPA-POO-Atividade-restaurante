package servico;

public class MesaNaoCadastradaException extends ServicoException{
	public MesaNaoCadastradaException() {
		super("Mesa não cadastrada");
	}
}
