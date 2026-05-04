package br.com.solutis.gui.actions;

import br.com.solutis.domain.controller.VendaController;
import br.com.solutis.domain.dto.venda.VendaResponse;
import br.com.solutis.gui.panels.ListaVendaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListaVendaAction implements ActionListener {

    public static final String COMMAND_ATUALIZAR = "ATUALIZAR";

    private final ListaVendaPanel panel;
    private final VendaController vendaController;

    public ListaVendaAction(ListaVendaPanel panel, VendaController vendaController) {
        this.panel = panel;
        this.vendaController = vendaController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (COMMAND_ATUALIZAR.equals(e.getActionCommand())) {
            listar();
        }
    }

    public void listar() {
        List<VendaResponse> responses = vendaController.listar();
        panel.setRows(mapVendas(responses));
    }

    private List<Object[]> mapVendas(List<VendaResponse> responses) {
        List<Object[]> rows = new ArrayList<>();
        if (responses == null) {
            return rows;
        }
        for (VendaResponse response : responses) {
            int itens = response.getLivros() == null ? 0 : response.getLivros().size();
            rows.add(new Object[]{response.getId(), response.getCliente(), response.getValor(), itens});
        }
        return rows;
    }
}
