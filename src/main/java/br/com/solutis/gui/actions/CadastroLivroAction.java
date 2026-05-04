package br.com.solutis.gui.actions;

import br.com.solutis.domain.controller.EletronicoController;
import br.com.solutis.domain.controller.ImpressoController;
import br.com.solutis.domain.dto.livro.eletronico.EletronicoRequest;
import br.com.solutis.domain.dto.livro.eletronico.EletronicoResponse;
import br.com.solutis.domain.dto.livro.impresso.ImpressoRequest;
import br.com.solutis.domain.dto.livro.impresso.ImpressoResponse;
import br.com.solutis.gui.panels.CadastroLivroPanel;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroLivroAction implements ActionListener {

    public static final String COMMAND_SALVAR = "SALVAR";
    public static final String COMMAND_LIMPAR = "LIMPAR";

    private final CadastroLivroPanel panel;
    private final ImpressoController impressoController;
    private final EletronicoController eletronicoController;

    public CadastroLivroAction(CadastroLivroPanel panel,
                              ImpressoController impressoController,
                              EletronicoController eletronicoController) {
        this.panel = panel;
        this.impressoController = impressoController;
        this.eletronicoController = eletronicoController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (COMMAND_LIMPAR.equals(command)) {
            panel.clearForm();
            return;
        }
        if (COMMAND_SALVAR.equals(command)) {
            salvarLivro();
        }
    }

    private void salvarLivro() {
        String titulo = required(panel.getTitulo(), "Titulo");
        String autores = required(panel.getAutores(), "Autores");
        String editora = required(panel.getEditora(), "Editora");
        Double preco = parseDouble(panel.getPreco(), "Preco");
        String tipo = panel.getTipo();

        if (titulo == null || autores == null || editora == null || preco == null) {
            return;
        }

        if ("Ambos".equalsIgnoreCase(tipo)) {
            Double frete = parseDouble(panel.getFrete(), "Frete");
            Integer estoque = parseInt(panel.getEstoque(), "Estoque");
            Integer tamanho = parseInt(panel.getTamanho(), "Tamanho (MB)");
            if (frete == null || estoque == null || tamanho == null) {
                return;
            }
            ImpressoRequest impressoRequest = new ImpressoRequest(titulo, autores, editora, preco, frete, estoque);
            EletronicoRequest eletronicoRequest = new EletronicoRequest(titulo, autores, editora, preco, tamanho);
            ImpressoResponse impressoResponse = impressoController.cadastrar(impressoRequest);
            EletronicoResponse eletronicoResponse = eletronicoController.cadastrar(eletronicoRequest);
            showMessage("Livros cadastrados com sucesso.\n" + impressoResponse + "\n" + eletronicoResponse);
            return;
        }

        if ("Impresso".equalsIgnoreCase(tipo)) {
            Double frete = parseDouble(panel.getFrete(), "Frete");
            Integer estoque = parseInt(panel.getEstoque(), "Estoque");
            if (frete == null || estoque == null) {
                return;
            }
            ImpressoRequest request = new ImpressoRequest(titulo, autores, editora, preco, frete, estoque);
            ImpressoResponse response = impressoController.cadastrar(request);
            showMessage("Livro impresso cadastrado com sucesso.\n" + response);
            return;
        }

        if ("Eletronico".equalsIgnoreCase(tipo)) {
            Integer tamanho = parseInt(panel.getTamanho(), "Tamanho (MB)");
            if (tamanho == null) {
                return;
            }
            EletronicoRequest request = new EletronicoRequest(titulo, autores, editora, preco, tamanho);
            EletronicoResponse response = eletronicoController.cadastrar(request);
            showMessage("Livro eletronico cadastrado com sucesso.\n" + response);
            return;
        }

        showMessage("Tipo invalido. Selecione Impresso, Eletronico ou Ambos.");
    }

    private String required(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            showMessage(fieldName + " obrigatorio.");
            return null;
        }
        return value.trim();
    }

    private Double parseDouble(String value, String fieldName) {
        String required = required(value, fieldName);
        if (required == null) {
            return null;
        }
        try {
            return Double.parseDouble(required.replace(',', '.'));
        } catch (NumberFormatException e) {
            showMessage(fieldName + " invalido.");
            return null;
        }
    }

    private Integer parseInt(String value, String fieldName) {
        String required = required(value, fieldName);
        if (required == null) {
            return null;
        }
        try {
            return Integer.parseInt(required);
        } catch (NumberFormatException e) {
            showMessage(fieldName + " invalido.");
            return null;
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(panel, message);
    }
}
