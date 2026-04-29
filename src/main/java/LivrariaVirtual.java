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
import utils.ScanManager;

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
        String titulo = scanner.readNonEmptyString("Titulo: ");
        String autores = scanner.readNonEmptyString("Autores: ");
        String editora = scanner.readNonEmptyString("Editora: ");
        Double preco = scanner.readDouble("Preco: ");
        String tipo = scanner.readTipo("Tipo (impresso/eletronico): ");

        LivroResponse response = null;
        if (tipo.equals("impresso")) {
            Double frete = scanner.readDouble("Frete: ");
            Integer estoque = scanner.readInt("Estoque: ");
            ImpressoRequest request = new ImpressoRequest(null, titulo, autores, editora, preco, frete, estoque);
            response = impressoController.cadastrar(request);
        } else if (tipo.equals("eletronico")) {
            Integer tamanho = scanner.readInt("Tamanho (MB): ");
            EletronicoRequest request = new EletronicoRequest(null, titulo, autores, editora, preco, tamanho);
            response = eletronicoController.cadastrar(request);
        }
        System.out.println("Livro cadastrado com sucesso!\n" + response + "\n");
    }

    public void realizarVenda() {
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
