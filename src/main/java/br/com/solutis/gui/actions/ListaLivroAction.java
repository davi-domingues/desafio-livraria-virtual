package br.com.solutis.gui.actions;

import br.com.solutis.domain.controller.EletronicoController;
import br.com.solutis.domain.controller.ImpressoController;
import br.com.solutis.domain.dto.livro.eletronico.EletronicoResponse;
import br.com.solutis.domain.dto.livro.impresso.ImpressoResponse;
import br.com.solutis.gui.panels.ListaLivroPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListaLivroAction implements ActionListener {

    public static final String COMMAND_TODOS = "TODOS";
    public static final String COMMAND_IMPRESSOS = "IMPRESSOS";
    public static final String COMMAND_ELETRONICOS = "ELETRONICOS";
    public static final String COMMAND_ATUALIZAR = "ATUALIZAR";

    private final ListaLivroPanel panel;
    private final ImpressoController impressoController;
    private final EletronicoController eletronicoController;

    public ListaLivroAction(ListaLivroPanel panel,
                            ImpressoController impressoController,
                            EletronicoController eletronicoController) {
        this.panel = panel;
        this.impressoController = impressoController;
        this.eletronicoController = eletronicoController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (COMMAND_IMPRESSOS.equals(command)) {
            listarImpressos();
            return;
        }
        if (COMMAND_ELETRONICOS.equals(command)) {
            listarEletronicos();
            return;
        }
        if (COMMAND_TODOS.equals(command) || COMMAND_ATUALIZAR.equals(command)) {
            listarTodos();
        }
    }

    public void listarTodos() {
        List<Object[]> rows = new ArrayList<>();
        rows.addAll(mapImpressos(impressoController.listar()));
        rows.addAll(mapEletronicos(eletronicoController.listar()));
        panel.setRows(rows);
    }

    public void listarImpressos() {
        panel.setRows(mapImpressos(impressoController.listar()));
    }

    public void listarEletronicos() {
        panel.setRows(mapEletronicos(eletronicoController.listar()));
    }

    private List<Object[]> mapImpressos(List<ImpressoResponse> responses) {
        List<Object[]> rows = new ArrayList<>();
        if (responses == null) {
            return rows;
        }
        for (ImpressoResponse response : responses) {
            rows.add(new Object[]{response.getId(), response.getTitulo(), "Impresso", response.getPreco()});
        }
        return rows;
    }

    private List<Object[]> mapEletronicos(List<EletronicoResponse> responses) {
        List<Object[]> rows = new ArrayList<>();
        if (responses == null) {
            return rows;
        }
        for (EletronicoResponse response : responses) {
            rows.add(new Object[]{response.getId(), response.getTitulo(), "Eletronico", response.getPreco()});
        }
        return rows;
    }
}
