package br.com.solutis.gui;

import br.com.solutis.application.ApplicationContext;
import br.com.solutis.domain.controller.EletronicoController;
import br.com.solutis.domain.controller.ImpressoController;
import br.com.solutis.domain.controller.VendaController;
import br.com.solutis.gui.actions.CadastroLivroAction;
import br.com.solutis.gui.actions.ListaLivroAction;
import br.com.solutis.gui.actions.ListaVendaAction;
import br.com.solutis.gui.actions.RegistroVendaAction;
import br.com.solutis.gui.panels.CadastroLivroPanel;
import br.com.solutis.gui.panels.ListaLivroPanel;
import br.com.solutis.gui.panels.ListaVendaPanel;
import br.com.solutis.gui.panels.RegistroVendaPanel;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private static final String CARD_CADASTRO = "cadastro";
    private static final String CARD_VENDA = "venda";
    private static final String CARD_LIVROS = "livros";
    private static final String CARD_VENDAS = "vendas";

    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final CadastroLivroPanel cadastroLivroPanel;
    private final RegistroVendaPanel registroVendaPanel;
    private final ListaLivroPanel listaLivroPanel;
    private final ListaVendaPanel listaVendaPanel;
    private final ListaLivroAction listaLivroAction;
    private final ListaVendaAction listaVendaAction;

    public MainFrame() {
        super("Livraria Digital");
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);

        ApplicationContext context = new ApplicationContext();
        ImpressoController impressoController = new ImpressoController(context.getImpressoService());
        EletronicoController eletronicoController = new EletronicoController(context.getEletronicoService());
        VendaController vendaController = new VendaController(context.getVendaService());

        this.cadastroLivroPanel = new CadastroLivroPanel();
        this.registroVendaPanel = new RegistroVendaPanel();
        this.listaLivroPanel = new ListaLivroPanel();
        this.listaVendaPanel = new ListaVendaPanel();

        CadastroLivroAction cadastroAction = new CadastroLivroAction(cadastroLivroPanel, impressoController, eletronicoController);
        cadastroLivroPanel.setAction(cadastroAction);

        RegistroVendaAction vendaAction = new RegistroVendaAction(registroVendaPanel, vendaController);
        registroVendaPanel.setAction(vendaAction);

        listaLivroAction = new ListaLivroAction(listaLivroPanel, impressoController, eletronicoController);
        listaLivroPanel.setAction(listaLivroAction);

        listaVendaAction = new ListaVendaAction(listaVendaPanel, vendaController);
        listaVendaPanel.setAction(listaVendaAction);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setJMenuBar(createMenuBar());

        registerPanels();
        add(cardPanel, BorderLayout.CENTER);

        setSize(900, 600);
        setLocationRelativeTo(null);
        showCard(CARD_CADASTRO);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem cadastroItem = new JMenuItem("Cadastrar livro");
        cadastroItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCard(CARD_CADASTRO);
            }
        });

        JMenuItem vendaItem = new JMenuItem("Realizar uma venda");
        vendaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCard(CARD_VENDA);
            }
        });

        JMenuItem listarLivrosItem = new JMenuItem("Listar livros");
        listarLivrosItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCard(CARD_LIVROS);
            }
        });

        JMenuItem listarVendasItem = new JMenuItem("Listar vendas");
        listarVendasItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCard(CARD_VENDAS);
            }
        });

        JMenuItem sairItem = new JMenuItem("Sair");
        sairItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });

        menu.add(cadastroItem);
        menu.add(vendaItem);
        menu.add(listarLivrosItem);
        menu.add(listarVendasItem);
        menu.addSeparator();
        menu.add(sairItem);

        menuBar.add(menu);
        return menuBar;
    }

    private void registerPanels() {
        cardPanel.add(cadastroLivroPanel, CARD_CADASTRO);
        cardPanel.add(registroVendaPanel, CARD_VENDA);
        cardPanel.add(listaLivroPanel, CARD_LIVROS);
        cardPanel.add(listaVendaPanel, CARD_VENDAS);
    }

    private void showCard(String name) {
        cardLayout.show(cardPanel, name);
        if (CARD_LIVROS.equals(name)) {
            listaLivroAction.listarTodos();
        }
        if (CARD_VENDAS.equals(name)) {
            listaVendaAction.listar();
        }
    }
}
