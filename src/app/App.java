package app;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import facade.Atendimento;
import repositorio.comanda.NenhumaComandaException;
import repositorio.produto.CardapioVazioException;
import repositorio.produto.CodigoInvalidoException;
import repositorio.produto.SemIndustrializadoException;
import repositorio.produto.SemPratosException;
import servico.Comanda;
import servico.Mesa;
import servico.MesaIndisponivelException;
import servico.MesaJaCadastradaException;
import servico.MesaNaoCadastradaException;
import servico.produto.Industrializado;
import servico.produto.Prato;
import servico.produto.Produto;

public class App {
	static Atendimento facade = new Atendimento();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int opcao = 0;
		do {
			limpaTela();
			System.out.println("MENU PRINCIPAL");
			System.out.println("==== =========");
			System.out.println();
			System.out.println("<1> Exibir cardápio");
			System.out.println("<2> Abrir menu de comandas");
			System.out.println("<3> Abrir menu de produtos");
			System.out.println("<4> Abrir menu de mesas");
			System.out.println("<0> Sair");
			System.out.println();
			System.out.print("Escolha uma opção: ");
			try {
				opcao = Integer.valueOf(scanner.nextLine());
			} catch (Exception e) {
				opcao = 0;
			}
			
			switch (opcao) {
			case 0:
				limpaTela();
				break;
			case 1:
				exibeCardapio();
				break;
			case 2:
				menuComandas();
				break;
			case 3:
				menuProdutos();
				break;
			case 4:
				menuMesas();
				break;
			}
			
		} while(opcao!=0);
		
	}
	private static void limpaTela() {
		for (int i = 0; i < 25; i++) {
			System.out.println();
		}
	}
	
	public static void exibeCardapio() {
		limpaTela();
		try {			
			facade.getAllProdutos();
			
			System.out.println("Pratos");
			try {
				List<Produto> pratoTemporario = facade.getSomentePratos();
				System.out.println("********************************************");
				System.out.println("Cod Nome                    Disponivel Preço");
				System.out.println("=== ======================= ========== =======");
				for(int i=0; i<pratoTemporario.size(); i++) {
					System.out.printf("%s", pratoTemporario.get(i));
				}
				System.out.println("********************************************");
			}catch(SemPratosException ex) {
				System.err.println(ex.getMessage());
			}
			System.out.println("");
			System.out.println("Industrializados");
			try {
				List<Produto> indutrialTemporario = facade.getSomenteIndustrializados();
				System.out.println("********************************************");
				System.out.println("Cod Nome                    Disponivel Preço");
				System.out.println("=== ======================= ========== =======");
				for(int i=0; i<indutrialTemporario.size(); i++) {
					System.out.printf("%s", indutrialTemporario.get(i));
				}
				System.out.println("********************************************");
			}catch(SemIndustrializadoException ex) {
				System.err.println(ex.getMessage());
			}
			
			
		}catch (CardapioVazioException ex) {
			System.err.println(ex.getMessage());
		}
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		limpaTela();
		
	}
	public static void menuComandas() {
		int opcao = 0;
		limpaTela();
		do {
			System.out.println("MENU COMANDAS");
			System.out.println("==== ========");
			System.out.println();
			System.out.println("<1> Listar Comandas");
			System.out.println("<2> Dados de uma comanda");
			System.out.println("<3> Fazer Comanda");
			System.out.println("<4> Alterar Comanda");
			System.out.println("<0> Sair");
			System.out.println();
			System.out.print("Escolha uma opção: ");
			try {
				opcao = Integer.valueOf(scanner.nextLine());
			} catch (Exception e) {
				opcao = 0;
			}
			
			switch (opcao) {
			case 0:
				limpaTela();
				break;
			case 1:
				listarComandas();
				break;
			case 2:
				exibirComanda();
				break;
			case 3:
				fazerComanda();
				break;
			case 4:
				alterarComanda();
				break;
			}
		}while(opcao!=0);
	}

	public static void listarComandas(){
		limpaTela();
		try {
			List<Comanda> comandas = facade.getAllComandas();
			
			//Cabeçalho da tabela
			System.out.printf("Codigo\tMesa\tStatus\tValor\tInicio\tFim\t\n");
			System.out.printf("======\t====\t======\t=====\t======\t===\t\n");
			
			//Impressao de todas as comandas
			for(Comanda comanda : comandas) {
				System.out.printf("%d\t%d\t",comanda.getCodigoDeComanda(), comanda.getMesa().getCodigoDeMesa());
				
				if(comanda.isStatus()) {
					System.out.printf("Aberto\t%.2f\t%s", comanda.getTotalAPagar(), 
							comanda.getDataDeAbertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")).toString());
				}else {
					System.out.printf("Fechado\t%.2f\t%s", comanda.getTotalAPagar(), 
							comanda.getDataDeAbertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")).toString(), 
							comanda.getDataDeFechamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")).toString());
				}
				
				System.out.println(); //Imprime uma nova linha ao fim de cada comanda
			}
		}catch (NenhumaComandaException ex) {
			System.err.println(ex.getMessage());
		}
		
		//Padrão após a lista
		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		limpaTela();
	}
	
	public static void exibirComanda() {
		limpaTela();
		System.out.println("Digite o código da comanda desejado:");
		int cod = scanner.nextInt();
		try {
			Comanda comanda = facade.getComanda(cod);
			
			Mesa mesa = comanda.getMesa();
			List<Produto> pedidos = comanda.getPedidos();
			String status = (comanda.isStatus())? "Aberta":"Fechada";
			double valorAPagar = comanda.getTotalAPagar();

			LocalDateTime abertura = comanda.getDataDeAbertura();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			
			String dataDeAbertura = abertura.format(formatter);
			
		
			
			System.out.println("********************");
			System.out.printf("Código de mesa atribuido: %d\n", mesa.getCodigoDeMesa());
			System.out.println("______________");
			System.out.printf("Status da comanda: %s\n", status);
			for(Produto pedido:pedidos) {
				System.out.println(pedido);
			}
			System.out.println("______________");
			System.out.printf("Total a pagar: %f\n",valorAPagar);
			if(!comanda.isStatus()) {
				System.out.printf("Valor pago: %f\n", comanda.getValorPago());
			}
			System.out.println("______________");
			System.out.println("Data de abertura: "+dataDeAbertura);
			
			if(!comanda.isStatus()) {
				LocalDateTime fechamento = comanda.getDataDeFechamento();
				String dataDeFechamento = fechamento.format(formatter);
				System.out.println("Data de fechamento: "+dataDeFechamento);
			}
			
			System.out.println("********************");
			
			
		} catch (CodigoInvalidoException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		scanner.nextLine();
		limpaTela();
	}
	
	public static void fazerComanda() {
		limpaTela();
		System.out.println("Digite o código da mesa: ");
		int codigo = scanner.nextInt();
		Mesa mesa;
		try {
			mesa = facade.getMesa(codigo);
			facade.checarDisponibilidadeDeMesa(mesa);
			facade.getAllProdutos();
			List<Produto> pedidosFeitos = new ArrayList<Produto>();
			Produto produtoPedido;
			int opcao = 1;
			int quantidadeDoPedido;
			do {
				System.out.println("Digite o código do produto, digite 0 para sair: ");
				opcao = scanner.nextInt();
				if(opcao != 0) {
					try {
						produtoPedido = facade.getProduto(opcao);
						quantidadeDoPedido = produtoPedido.getQuantidadeDisponivel();
						if(quantidadeDoPedido>0) {
							pedidosFeitos.add(produtoPedido);
							produtoPedido.setQuantidadeDisponivel(quantidadeDoPedido-1);
							System.out.println("Produto adicionado.");
						} else {
							System.err.println("Produto indisponivel");
						}
					} catch(CodigoInvalidoException ex) {
						System.err.println(ex.getMessage());
						System.err.println("Tente novamente se desejado.");
					}
				}
			}while (opcao != 0);
			facade.abrirComanda(mesa, pedidosFeitos);
		} catch(MesaNaoCadastradaException ex1) {
			System.err.println(ex1.getMessage());
		} catch(MesaIndisponivelException ex2) {
			System.err.println(ex2.getMessage());
		} catch(CardapioVazioException ex3) {
			System.err.println(ex3.getMessage());
		}
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		scanner.nextLine();
		limpaTela();
	}
	
	public static void alterarComanda() {
		System.out.println("Digite o código da comanda");
		int cod = scanner.nextInt();
		scanner.nextLine();
		try {
			Comanda comanda = facade.getComanda(cod);
			int opcao = 0;
			limpaTela();
			do {
				System.out.println("ALTERAÇÃO DE COMANDA");
				System.out.println("========= == =======");
				System.out.println();
				System.out.println("<1> adicionar produtos");
				System.out.println("<2> Fechar comanda");
				System.out.println("<0> Sair");
				System.out.println();
				System.out.print("Escolha uma opção: ");
				try {
					opcao = Integer.valueOf(scanner.nextLine());
				} catch (Exception e) {
					opcao = 0;
				}
				
				switch (opcao) {
				case 0:
					limpaTela();
					break;
				case 1:
					adicionarPedidos(comanda);
					break;
				case 2:
					fecharComanda(comanda);
					break;
				}
			}while(opcao!=0);
		} catch (CodigoInvalidoException e1) {
			System.err.println(e1.getMessage());
		}
	}
	
	public static void adicionarPedidos(Comanda comanda) {
		List<Produto> novosPedidos = new ArrayList<Produto>();
		Produto produtoPedido;
		int opcao, quantidadeDoPedido;
		do {
			System.out.println("Digite o código do produto, digite 0 para sair: ");
			opcao = scanner.nextInt();
			if(opcao != 0) {
				try {
					produtoPedido = facade.getProduto(opcao);
					quantidadeDoPedido = produtoPedido.getQuantidadeDisponivel();
					if(quantidadeDoPedido>0) {
						novosPedidos.add(produtoPedido);
						produtoPedido.setQuantidadeDisponivel(quantidadeDoPedido-1);
						System.out.println("Produto adicionado.");
					} else {
						System.err.println("Produto indisponivel");
					}
				} catch(CodigoInvalidoException ex) {
					System.err.println(ex.getMessage());
					System.err.println("Tente novamente se desejado.");
				}
			}
		}while (opcao != 0);
		facade.adicionarPedidos(comanda, novosPedidos);
	}
	
	public static void fecharComanda(Comanda comandaParaFechar) {
		limpaTela();


		System.out.println("Valor a pagar: " + comandaParaFechar.getTotalAPagar());
		System.out.println("Valor que está pagando: ");
		double valorPago = scanner.nextDouble();
		facade.fecharComanda(comandaParaFechar, valorPago);

		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		scanner.nextLine();
		limpaTela();
	}

	public static void menuProdutos() {
		int opcao = 0;
		limpaTela();
		do {
			System.out.println("MENU PRODUTOS");
			System.out.println("==== ========");
			System.out.println();
			System.out.println("<1> Mostrar cardápio");
			System.out.println("<2> Cadastrar um produto no sistema");
			System.out.println("<3> Remover um produto do sistema");
			System.out.println("<4> Modificar disponibilidade de um produto");
			System.out.println("<0> Sair");
			System.out.println();
			System.out.print("Escolha uma opção: ");
			try {
				opcao = Integer.valueOf(scanner.nextLine());
			} catch (Exception e) {
				opcao = 0;
			}
			
			switch (opcao) {
			case 0:
				limpaTela();
				break;
			case 1:
				exibeCardapio();
				break;
			case 2:
				cadastrarProduto();
				break;
			case 3:
				removerProduto();
				break;
			case 4:
				alterarDisponibilidade();
				break;
			}
		}while(opcao!=0);
	}
	
	public static void cadastrarProduto() {
		
		limpaTela();
		System.out.println("Cadastro de produto");
		System.out.println("======== == =======");
		
		System.out.println("Digite o tipo de produto: ");
		System.out.println("<1> Prato <2> Industrializado");
		int opcao = scanner.nextInt();
		if(opcao>2 || 1>opcao) {
			System.err.println("Tipo de produto invalido");
		}else {
			System.out.println("Nome do produto: ");
			scanner.nextLine();
			String nomeDoProduto = scanner.nextLine();
			
			System.out.println("Preço que ele custará");
			double preco = scanner.nextDouble();
			System.out.println("Quanto custa compra-lo ou faze-lo?");
			
			double custo = scanner.nextDouble();
			int codigoDoProduto;
			try {
				codigoDoProduto = facade.getAllProdutos().size() + 1;
			}catch(CardapioVazioException ex) {
				codigoDoProduto = 1;
			}
			switch(opcao) {
				case 1:
					facade.cadastrarProduto(new Prato(nomeDoProduto, preco, codigoDoProduto, custo));
					break;
				case 2: 
					facade.cadastrarProduto(new Industrializado(nomeDoProduto, preco, codigoDoProduto, custo));
					break;
			}
		}
		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		scanner.nextLine();
		limpaTela();

	}
	public static void removerProduto() {
		limpaTela();
		try {
			facade.getAllProdutos();
			System.out.println("Digite o código do produto a ser removido");
			int codigo = scanner.nextInt();
			try {				
				facade.removeProdutoCadastrado(codigo);
				System.out.println("Produto removido com sucesso");
			}catch(CodigoInvalidoException ex) {
				System.err.println(ex.getMessage());
			}
		}catch (CardapioVazioException ex){
			System.err.println(ex.getMessage());
		}
		
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		limpaTela();
	}
	public static void alterarDisponibilidade() {
		limpaTela();
		System.out.println("Digite o código do produto");
		int codigo = scanner.nextInt();
		Produto produtoASerAlterado; 
		try {
			produtoASerAlterado = facade.getProduto(codigo);
			System.out.println(" ");
			System.out.println("Quantidade atual: " + produtoASerAlterado.getQuantidadeDisponivel());
			System.out.println("Digite a quantidade nova: ");
			int quantidadeNova = scanner.nextInt();
			produtoASerAlterado.setQuantidadeDisponivel(quantidadeNova);
		} catch (CodigoInvalidoException e) {
			limpaTela();
			System.err.println(e.getMessage());
		}
		
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		scanner.nextLine();
		limpaTela();
		
	}
	public static void menuMesas() {
		int opcao = 0;
		limpaTela();
		do {
			System.out.println("MENU MESAS");
			System.out.println("==== =====");
			System.out.println();
			System.out.println("<1> Listar mesas");
			System.out.println("<2> Remover mesas");
			System.out.println("<3> Adicionar mesas");
			System.out.println("<0> Sair");
			System.out.println();
			System.out.print("Escolha uma opção: ");
			try {
				opcao = Integer.valueOf(scanner.nextLine());
			} catch (Exception e) {
				opcao = 0;
			}
			
			switch (opcao) {
			case 0:
				limpaTela();
				break;
			case 1:
				listarMesas();
				break;
			case 2:
				removerMesas();
				break;
			case 3:
				adicionarMesas();
				break;
			}
		}while(opcao!=0);
	}
	public static void listarMesas() {
		limpaTela();
		List<Mesa> mesas = facade.getMesas();
		System.out.println("Mesas cadastradas: ");
		for(Mesa mesa : mesas) {
			System.out.println("***********");
			System.out.printf("Mesa de código: %d\n", mesa.getCodigoDeMesa());
			System.out.println("***********");
		}
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}
	
	public static void removerMesas() {
		limpaTela();
		int cod;
		System.out.println("Digite o código da mesa a ser removida: ");
		cod = scanner.nextInt();
		facade.removeMesa(cod);
		
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}

	public static void adicionarMesas() {
		limpaTela();
		System.out.println("Qual o código da mesa a ser adicionada?");
		int cod = scanner.nextInt();
		try {
			facade.addMesa(cod);
		} catch (MesaJaCadastradaException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}
	
}
