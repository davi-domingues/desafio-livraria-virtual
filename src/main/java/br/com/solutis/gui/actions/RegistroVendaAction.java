package br.com.solutis.gui.actions;

import br.com.solutis.domain.controller.VendaController;
import br.com.solutis.domain.dto.venda.VendaRequest;
import br.com.solutis.domain.dto.venda.VendaResponse;
import br.com.solutis.gui.panels.RegistroVendaPanel;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RegistroVendaAction implements ActionListener {

    public static final String COMMAND_REGISTRAR = "REGISTRAR";
    public static final String COMMAND_LIMPAR = "LIMPAR";

    private final RegistroVendaPanel panel;
    private final VendaController vendaController;

    public RegistroVendaAction(RegistroVendaPanel panel, VendaController vendaController) {
        this.panel = panel;
        this.vendaController = vendaController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (COMMAND_LIMPAR.equals(command)) {
            panel.clearForm();
            return;
        }
        if (COMMAND_REGISTRAR.equals(command)) {
            registrarVenda();
        }
    }

    private void registrarVenda() {
        String cliente = required(panel.getCliente(), "Cliente");
        Double valor = parseDouble(panel.getValor(), "Valor");
        List<Integer> ids = parseIds(panel.getIdsLivros());

        if (cliente == null || valor == null || ids == null) {
            return;
        }

        VendaRequest request = new VendaRequest(cliente, valor, ids);
        VendaResponse response = vendaController.realizarVenda(request);
        showMessage("Venda registrada com sucesso.\n" + response);
    }

    private List<Integer> parseIds(String value) {
        String required = required(value, "Ids dos livros");
        if (required == null) {
            return null;
        }
        String[] parts = required.split(",");
        List<Integer> ids = new ArrayList<>();
        for (String part : parts) {
            String trimmed = part.trim();
            if (trimmed.isEmpty()) {
                showMessage("Ids invalidos.");
                return null;
            }
            try {
                ids.add(Integer.parseInt(trimmed));
            } catch (NumberFormatException e) {
                showMessage("Ids invalidos.");
                return null;
            }
        }
        if (ids.isEmpty()) {
            showMessage("Ids invalidos.");
            return null;
        }
        return ids;
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

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(panel, message);
    }
}
