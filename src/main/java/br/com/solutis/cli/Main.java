package br.com.solutis.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final LivrariaVirtual app = new LivrariaVirtual();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.print("Pressione ENTER para iniciar...");
        scanner.nextLine();
        while (true) {
            System.out.println("""
                === Livraria Digital ===
                1. Cadastrar livro
                2. Realizar uma venda
                3. Listar livros
                4. Listar vendas
                5. Sair do programa
                """);
            System.out.print("Escolha uma opção: ");

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Opção inválida. Digite um número entre 1 e 5.\n");
                waitForEnter(scanner);
                continue;
            }

            scanner.nextLine();

            switch (option) {
                case 1:
                    app.cadastrarLivro();
                    break;
                case 2:
                    app.realizarVenda();
                    break;
                case 3:
                    app.listarLivros();
                    break;
                case 4:
                    app.listarVendas();
                    break;
                case 5:
                    System.out.println("Saindo do programa. Até logo!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Digite um número entre 1 e 5.\n");
                    break;
            }

            waitForEnter(scanner);
        }
    }

    private static void waitForEnter(Scanner scanner) {
        System.out.print("Pressione ENTER para voltar ao MENU...");
        scanner.nextLine();
    }

}
