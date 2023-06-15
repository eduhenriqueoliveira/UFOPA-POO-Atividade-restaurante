package app;

import java.util.List;
//import java.util.ArrayList;
import java.util.Scanner;

import facade.Atendimento;
import repositorio.produto.CardapioVazioException;
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
			int n = cardapioTemporario.size();			
			for(int i=0; i<n; i++) {
				System.out.printf("%s\n", cardapioTemporario.get(i));
			}
		}catch (CardapioVazioException ex) {
			System.err.println(ex.getMessage());
		}
		
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		limpaTela();
		
	}
	public static void menuComandas() {
		
	}
	
	public static void menuProdutos() {
		
	}
	

}
