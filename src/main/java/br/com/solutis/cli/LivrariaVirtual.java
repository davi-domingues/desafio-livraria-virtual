package br.com.solutis.cli;

import br.com.solutis.application.ApplicationContext;
import br.com.solutis.domain.controller.EletronicoController;
import br.com.solutis.domain.controller.ImpressoController;
import br.com.solutis.domain.controller.VendaController;
import br.com.solutis.domain.dto.livro.LivroResponse;
import br.com.solutis.domain.dto.livro.eletronico.EletronicoRequest;
import br.com.solutis.domain.dto.livro.eletronico.EletronicoResponse;
import br.com.solutis.domain.dto.livro.impresso.ImpressoRequest;
import br.com.solutis.domain.dto.livro.impresso.ImpressoResponse;
import br.com.solutis.domain.dto.venda.VendaRequest;
import br.com.solutis.domain.dto.venda.VendaResponse;

import java.util.List;

public class LivrariaVirtual {

    private final Integer MAX_IMPRESSOS = 10;
    private final Integer MAX_ELETRONICOS = 20;
    private final Integer MAX_VENDAS = 50;
    private final EletronicoController eletronicoController;
    private final ImpressoController impressoController;
    private final VendaController vendaController;
    private final ScanManager scanner;

    public LivrariaVirtual() {
        ApplicationContext context = new ApplicationContext();
        this.eletronicoController = new EletronicoController(context.getEletronicoService());
        this.impressoController = new ImpressoController(context.getImpressoService());
        this.vendaController = new VendaController(context.getVendaService());
        this.scanner = new ScanManager();
    }

    private Integer getNumImpresso() {
        return impressoController.getNumImpressos();
    }

    private Integer getNumEletronico() {
        return eletronicoController.getNumEletronicos();
    }

    private Integer getNumVendas() {
        return vendaController.getNumVendas();
    }

    @Override
    public String toString() {
        return "cli.LivrariaVirtual{" +
                "MAX_IMPRESSOS=" + MAX_IMPRESSOS +
                ", MAX_ELETRONICOS=" + MAX_ELETRONICOS +
                ", MAX_VENDAS=" + MAX_VENDAS +
                ", eletronicoController=" + eletronicoController +
                ", impressoController=" + impressoController +
                ", vendaController=" + vendaController +
                '}';
    }

    public void cadastrarLivro() {
        if (getNumImpresso() >= MAX_IMPRESSOS && getNumEletronico() >= MAX_ELETRONICOS) {
            System.out.println("Limite de livros cadastrados atingido.\n");
            return;
        }

        System.out.println("1. Impresso");
        System.out.println("2. Eletronico");
        System.out.println("3. Ambos");
        int option = scanner.readInt("Escolha uma opcao: ");

        if (option != 1 && option != 2 && option != 3) {
            System.out.println("Opção inválida. Digite um numero entre 1 e 3.\n");
            return;
        }

        String titulo = scanner.readNonEmptyString("Titulo: ");
        String autores = scanner.readNonEmptyString("Autores: ");
        String editora = scanner.readNonEmptyString("Editora: ");
        Double preco = scanner.readDouble("Preco: ");

        LivroResponse response;
        if (option == 1 || option == 3) {
            if (getNumImpresso() >= MAX_IMPRESSOS) {
                System.out.println("Limite de livros impressos atingido.\n");
                return;
            }
            Double frete = scanner.readDouble("Frete: ");
            Integer estoque = scanner.readInt("Estoque: ");
            ImpressoRequest request = new ImpressoRequest(titulo, autores, editora, preco, frete, estoque);
            response = impressoController.cadastrar(request);
            System.out.println("Livro impresso cadastrado com sucesso!\n" + response + "\n");
        }

        if (option == 2 || option == 3) {
            if (getNumEletronico() >= MAX_ELETRONICOS) {
                System.out.println("Limite de livros eletronicos atingido.\n");
                return;
            }
            Integer tamanho = scanner.readInt("Tamanho (MB): ");
            EletronicoRequest request = new EletronicoRequest(titulo, autores, editora, preco, tamanho);
            response = eletronicoController.cadastrar(request);
            System.out.println("Livro eletronico cadastrado com sucesso!\n" + response + "\n");
        }
    }

    public void realizarVenda() {
        if (getNumVendas() >= MAX_VENDAS) {
            System.out.println("Limite de vendas atingido.\n");
            return;
        }
        String cliente = scanner.readNonEmptyString("Cliente: ");
        Double valor = scanner.readDouble("Valor: ");
        List<Integer> livrosIds = scanner.readIds("Ids dos livros (separados por virgula): ");
        VendaRequest request = new VendaRequest(cliente, valor, livrosIds);
        VendaResponse response = vendaController.realizarVenda(request);
        System.out.println("Venda registrada com sucesso!\n" + response + "\n");
    }

    public void listarLivros() {
        System.out.println("1. Listar todos");
        System.out.println("2. Listar impressos");
        System.out.println("3. Listar eletronicos");
        int option = scanner.readInt("Escolha uma opcao: ");

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
        List<VendaResponse> responses = vendaController.listar();
        if (responses == null || responses.isEmpty()) {
            System.out.println("Nenhuma venda encontrada.\n");
            return;
        }
        System.out.println("=== Vendas ===");
        for (VendaResponse response : responses) {
            System.out.println(response + "\n");
        }
    }
}
