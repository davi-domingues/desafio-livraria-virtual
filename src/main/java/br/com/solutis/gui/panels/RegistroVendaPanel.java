package br.com.solutis.gui.panels;

import br.com.solutis.gui.actions.RegistroVendaAction;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class RegistroVendaPanel extends JPanel {

    private final JTextField clienteField;
    private final JTextField valorField;
    private final JTextField idsField;
    private final JButton registrarButton;
    private final JButton limparButton;

    public RegistroVendaPanel() {
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel title = new JLabel("Realizar Venda");
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        formPanel.add(new JLabel("Cliente:"));
        clienteField = new JTextField(18);
        formPanel.add(clienteField);

        formPanel.add(new JLabel("Valor:"));
        valorField = new JTextField(10);
        formPanel.add(valorField);

        formPanel.add(new JLabel("Ids dos livros:"));
        idsField = new JTextField(18);
        formPanel.add(idsField);

        add(formPanel, BorderLayout.CENTER);

        JPanel actions = new JPanel(new GridLayout(1, 2, 8, 8));
        registrarButton = new JButton("Registrar");
        limparButton = new JButton("Limpar");
        actions.add(registrarButton);
        actions.add(limparButton);

        JTextArea hint = new JTextArea("Informe os dados da venda. Nenhuma acao sera executada nesta tela.");
        hint.setEditable(false);
        hint.setLineWrap(true);
        hint.setWrapStyleWord(true);
        hint.setOpaque(false);

        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.add(actions);
        footer.add(hint);
        add(footer, BorderLayout.SOUTH);
    }

    public void setAction(RegistroVendaAction action) {
        registrarButton.setActionCommand(RegistroVendaAction.COMMAND_REGISTRAR);
        limparButton.setActionCommand(RegistroVendaAction.COMMAND_LIMPAR);
        registrarButton.addActionListener(action);
        limparButton.addActionListener(action);
    }

    public String getCliente() {
        return clienteField.getText();
    }

    public String getValor() {
        return valorField.getText();
    }

    public String getIdsLivros() {
        return idsField.getText();
    }

    public void clearForm() {
        clienteField.setText("");
        valorField.setText("");
        idsField.setText("");
    }
}
