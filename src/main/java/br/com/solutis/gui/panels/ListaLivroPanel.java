package br.com.solutis.gui.panels;

import br.com.solutis.gui.actions.ListaLivroAction;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class ListaLivroPanel extends JPanel {

    private final DefaultTableModel tableModel;
    private final JButton todosButton;
    private final JButton impressosButton;
    private final JButton eletronicosButton;
    private final JButton atualizarButton;

    public ListaLivroPanel() {
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel title = new JLabel("Lista de Livros");
        add(title, BorderLayout.NORTH);

        JPanel actions = new JPanel();
        todosButton = new JButton("Todos");
        impressosButton = new JButton("Impressos");
        eletronicosButton = new JButton("Eletronicos");
        atualizarButton = new JButton("Atualizar");
        actions.add(todosButton);
        actions.add(impressosButton);
        actions.add(eletronicosButton);
        actions.add(atualizarButton);
        add(actions, BorderLayout.SOUTH);

        String[] columns = {"Id", "Titulo", "Tipo", "Preco"};
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

    public void setAction(ListaLivroAction action) {
        todosButton.setActionCommand(ListaLivroAction.COMMAND_TODOS);
        impressosButton.setActionCommand(ListaLivroAction.COMMAND_IMPRESSOS);
        eletronicosButton.setActionCommand(ListaLivroAction.COMMAND_ELETRONICOS);
        atualizarButton.setActionCommand(ListaLivroAction.COMMAND_ATUALIZAR);
        todosButton.addActionListener(action);
        impressosButton.addActionListener(action);
        eletronicosButton.addActionListener(action);
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
