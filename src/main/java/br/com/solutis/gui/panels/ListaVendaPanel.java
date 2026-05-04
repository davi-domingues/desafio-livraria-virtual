package br.com.solutis.gui.panels;

import br.com.solutis.gui.actions.ListaVendaAction;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class ListaVendaPanel extends JPanel {

    private final DefaultTableModel tableModel;
    private final JButton atualizarButton;

    public ListaVendaPanel() {
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel title = new JLabel("Lista de Vendas");
        add(title, BorderLayout.NORTH);

        JPanel actions = new JPanel();
        atualizarButton = new JButton("Atualizar");
        actions.add(atualizarButton);
        add(actions, BorderLayout.SOUTH);

        String[] columns = {"Id", "Cliente", "Valor", "Itens"};
        Object[][] data = {};

        tableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setAction(ListaVendaAction action) {
        atualizarButton.setActionCommand(ListaVendaAction.COMMAND_ATUALIZAR);
        atualizarButton.addActionListener(action);
    }

    public void setRows(java.util.List<Object[]> rows) {
        tableModel.setRowCount(0);
        if (rows == null) {
            return;
        }
        for (Object[] row : rows) {
            tableModel.addRow(row);
        }
    }
}
