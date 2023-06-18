package app;

import java.time.format.DateTimeFormatter;
import java.util.List;
//import java.util.ArrayList;
import java.util.Scanner;

import facade.Atendimento;
import repositorio.produto.CardapioVazioException;
import repositorio.produto.SemIndustrializadoException;
import repositorio.produto.SemPratosException;

import servico.Comanda;

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
			List<Produto> cardapioTemporario = facade.getAllProdutos();
			
			System.out.println("Pratos");
			try {
				List<Produto> pratoTemporario = facade.getSomentePratos();
				System.out.println("*******************************");
				System.out.println("Cod Nome                    Disponivel Preço");
				System.out.println("=== ======================= ========== =======");
				for(int i=0; i<pratoTemporario.size(); i++) {
					System.out.printf("%s", pratoTemporario.get(i));
				}
				System.out.println("*******************************");
			}catch(SemPratosException ex) {
				System.err.println(ex.getMessage());
			}
			System.out.println("");
			System.out.println("Industrializados");
			try {
				List<Produto> indutrialTemporario = facade.getSomenteIndustrializados();
				System.out.println("*******************************");
				System.out.println("Cod Nome                    Disponivel Preço");
				System.out.println("=== ======================= ========== =======");
				for(int i=0; i<indutrialTemporario.size(); i++) {
					System.out.printf("%s", indutrialTemporario.get(i));
				}
				System.out.println("*******************************");
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
			System.out.println("<2> Fazer Comanda");
			System.out.println("<3> Fechar Comanda");
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
				fazerComanda();
				break;
			case 3:
				fecharComanda();
				break;
			}
		}while(opcao!=0);
	}

	public static void listarComandas(){
		

		limpaTela();
		
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
		
		//Padrão após a lista
		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		

	}
	public static void fazerComanda() {
		
	}
	public static void fecharComanda() {
		
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
			}
		}while(opcao!=0);
	}
	public static void cadastrarProduto() {
		
		limpaTela();
		System.out.println("Cadastro de produto");
		System.out.println("===================");
		
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

	}
	public static void removerProduto() {
		
	}
	

}
