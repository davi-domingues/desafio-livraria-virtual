import application.ApplicationContext;
import controller.EletronicoController;
import controller.ImpressoController;
import controller.VendaController;
import dto.livro.LivroResponse;
import dto.livro.eletronico.EletronicoRequest;
import dto.livro.eletronico.EletronicoResponse;
import dto.livro.impresso.ImpressoRequest;
import dto.livro.impresso.ImpressoResponse;
import dto.venda.VendaRequest;
import dto.venda.VendaResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LivrariaVirtual {

    private final Integer MAX_IMPRESSOS = 10;
    private final Integer MAX_ELETRONICOS = 20;
    private final Integer MAX_VENDAS = 50;
    private final EletronicoController eletronicoController;
    private final ImpressoController impressoController;
    private final VendaController vendaController;

    public LivrariaVirtual() {
        ApplicationContext context = new ApplicationContext();
        this.eletronicoController = new EletronicoController(context.getEletronicoService());
        this.impressoController = new ImpressoController(context.getImpressoService());
        this.vendaController = new VendaController(context.getVendaService());
    }
//    // private Integer numImpresso;
//    private Integer getNumImpresso() {};
//
//    // private Integer numEletronico;
//    private Integer getNumEletronico() {};
//
//    // private Integer numVendas;
//    private Integer getNumVendas() {};


    @Override
    public String toString() {
        return "LivrariaVirtual{" +
                "MAX_IMPRESSOS=" + MAX_IMPRESSOS +
                ", MAX_ELETRONICOS=" + MAX_ELETRONICOS +
                ", MAX_VENDAS=" + MAX_VENDAS +
                ", eletronicoController=" + eletronicoController +
                ", impressoController=" + impressoController +
                ", vendaController=" + vendaController +
                '}';
    }

    public void cadastrarLivro() {
        Scanner scanner = new Scanner(System.in);
        String titulo = readNonEmptyString(scanner, "Titulo: ");
        String autores = readNonEmptyString(scanner, "Autores: ");
        String editora = readNonEmptyString(scanner, "Editora: ");
        Double preco = readDouble(scanner, "Preco: ");
        String tipo = readTipo(scanner, "Tipo (impresso/eletronico): ");

        LivroResponse response = null;
        if (tipo.equals("impresso")) {
            Double frete = readDouble(scanner, "Frete: ");
            Integer estoque = readInt(scanner, "Estoque: ");
            ImpressoRequest request = new ImpressoRequest(null, titulo, autores, editora, preco, frete, estoque);
            response = impressoController.cadastrar(request);
        } else if (tipo.equals("eletronico")) {
            Integer tamanho = readInt(scanner, "Tamanho (MB): ");
            EletronicoRequest request = new EletronicoRequest(null, titulo, autores, editora, preco, tamanho);
            response = eletronicoController.cadastrar(request);
        }
        System.out.println("Livro cadastrado com sucesso!\n" + response + "\n");
    }

    public void realizarVenda() {
        Scanner scanner = new Scanner(System.in);
        String cliente = readNonEmptyString(scanner, "Cliente: ");
        Double valor = readDouble(scanner, "Valor: ");
        List<Integer> livrosIds = readIds(scanner, "Ids dos livros (separados por virgula): ");
        VendaRequest request = new VendaRequest(cliente, valor, livrosIds);
        VendaResponse response = vendaController.realizarVenda(request);
        System.out.println("Venda registrada com sucesso!\n" + response + "\n");
    }

    public void listarLivros() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Listar todos");
        System.out.println("2. Listar impressos");
        System.out.println("3. Listar eletronicos");
        int option = readInt(scanner, "Escolha uma opcao: ");

        if (option == 1) {
            listarLivrosImpressos();
            listarLivrosEletronicos();
            return;
        }
        if (option == 2) {
            listarLivrosImpressos();
            return;
        }
        if (option == 3) {
            listarLivrosEletronicos();
            return;
        }

        System.out.println("Opcao invalida. Digite um numero entre 1 e 3.\n");
    }

    public void listarLivrosImpressos() {
        List<ImpressoResponse> responses = impressoController.listar();
        if (responses == null || responses.isEmpty()) {
            System.out.println("Nenhum livro impresso encontrado.\n");
            return;
        }
        System.out.println("=== Livros Impressos ===");
        for (ImpressoResponse response : responses) {
            System.out.println(response + ";");
        }
    }

    public void listarLivrosEletronicos() {
        List<EletronicoResponse> responses = eletronicoController.listar();
        if (responses == null || responses.isEmpty()) {
            System.out.println("Nenhum livro eletronico encontrado.\n");
            return;
        }
        System.out.println("=== Livros Eletronicos ===");
        for (EletronicoResponse response : responses) {
            System.out.println(response + ";");
        }
    }

    public void listarVendas() {
        System.out.println("Opção 4 selecionada: Listar vendas\n");
    }

    private String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Valor invalido. Tente novamente.");
        }
    }

    private Double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido. Tente novamente.");
            }
        }
    }

    private Integer readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido. Tente novamente.");
            }
        }
    }

    private String readTipo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim().toLowerCase();
            if (value.equals("impresso") || value.equals("eletronico")) {
                return value;
            }
            System.out.println("Tipo invalido. Use 'impresso' ou 'eletronico'.");
        }
    }

    private List<Integer> readIds(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("Valor invalido. Tente novamente.");
                continue;
            }
            String[] parts = value.split(",");
            List<Integer> ids = new ArrayList<>();
            boolean valid = true;
            for (String part : parts) {
                String trimmed = part.trim();
                if (trimmed.isEmpty()) {
                    valid = false;
                    break;
                }
                try {
                    ids.add(Integer.parseInt(trimmed));
                } catch (NumberFormatException e) {
                    valid = false;
                    break;
                }
            }
            if (valid && !ids.isEmpty()) {
                return ids;
            }
            System.out.println("Ids invalidos. Informe numeros separados por virgula.");
        }
    }
}
