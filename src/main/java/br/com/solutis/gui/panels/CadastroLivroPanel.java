package br.com.solutis.gui.panels;

import br.com.solutis.gui.actions.CadastroLivroAction;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.SwingUtilities;

public class CadastroLivroPanel extends JPanel {

    private final JTextField tituloField;
    private final JTextField autoresField;
    private final JTextField editoraField;
    private final JTextField precoField;
    private final JComboBox<String> tipoCombo;
    private JTextField freteField;
    private JTextField estoqueField;
    private JTextField tamanhoField;
    private final JButton salvarButton;
    private final JButton limparButton;
    private final JLabel freteLabel;
    private final JLabel estoqueLabel;
    private final JLabel tamanhoLabel;
    private final JPanel dynamicFieldsPanel;

    public CadastroLivroPanel() {
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel title = new JLabel("Cadastro de Livro");
        add(title, BorderLayout.NORTH);

        JPanel formContainer = new JPanel(new GridLayout(1, 2, 12, 12));

        JPanel fixedPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        fixedPanel.add(new JLabel("Titulo:"));
        tituloField = new JTextField();
        fixedPanel.add(tituloField);

        fixedPanel.add(new JLabel("Autores:"));
        autoresField = new JTextField();
        fixedPanel.add(autoresField);

        fixedPanel.add(new JLabel("Editora:"));
        editoraField = new JTextField();
        fixedPanel.add(editoraField);

        fixedPanel.add(new JLabel("Preco:"));
        precoField = new JTextField();
        fixedPanel.add(precoField);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JPanel tipoPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        tipoPanel.add(new JLabel("Tipo:"));
        tipoCombo = new JComboBox<>(new String[]{"Impresso", "Eletronico", "Ambos"});
        tipoPanel.add(tipoCombo);
        rightPanel.add(tipoPanel);

        freteField = new JTextField();
        estoqueField = new JTextField();
        tamanhoField = new JTextField();
        freteLabel = new JLabel("Frete:");
        estoqueLabel = new JLabel("Estoque:");
        tamanhoLabel = new JLabel("Tamanho (MB):");

        dynamicFieldsPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        dynamicFieldsPanel.add(freteLabel);
        dynamicFieldsPanel.add(freteField);
        dynamicFieldsPanel.add(estoqueLabel);
        dynamicFieldsPanel.add(estoqueField);
        dynamicFieldsPanel.add(tamanhoLabel);
        dynamicFieldsPanel.add(tamanhoField);
        rightPanel.add(dynamicFieldsPanel);

        formContainer.add(fixedPanel);
        formContainer.add(rightPanel);

        add(formContainer, BorderLayout.CENTER);

        JPanel actions = new JPanel(new GridLayout(1, 2, 8, 8));
        salvarButton = new JButton("Salvar");
        limparButton = new JButton("Limpar");
        actions.add(salvarButton);
        actions.add(limparButton);

        JTextArea hint = new JTextArea("Preencha os dados do livro. Nenhuma acao sera executada nesta tela.");
        hint.setEditable(false);
        hint.setLineWrap(true);
        hint.setWrapStyleWord(true);
        hint.setOpaque(false);

        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.add(actions);
        footer.add(hint);
        add(footer, BorderLayout.SOUTH);

        tipoCombo.addActionListener(e -> updateDynamicFields());
        SwingUtilities.invokeLater(this::updateDynamicFields);
    }

    private void updateDynamicFields() {
        String tipo = getTipo();
        boolean impresso = "Impresso".equalsIgnoreCase(tipo) || "Ambos".equalsIgnoreCase(tipo);
        boolean eletronico = "Eletronico".equalsIgnoreCase(tipo) || "Ambos".equalsIgnoreCase(tipo);

        freteLabel.setVisible(impresso);
        freteField.setVisible(impresso);
        estoqueLabel.setVisible(impresso);
        estoqueField.setVisible(impresso);
        tamanhoLabel.setVisible(eletronico);
        tamanhoField.setVisible(eletronico);

        dynamicFieldsPanel.revalidate();
        dynamicFieldsPanel.repaint();
    }

    public void setAction(CadastroLivroAction action) {
        salvarButton.setActionCommand(CadastroLivroAction.COMMAND_SALVAR);
        limparButton.setActionCommand(CadastroLivroAction.COMMAND_LIMPAR);
        salvarButton.addActionListener(action);
        limparButton.addActionListener(action);
    }

    public String getTitulo() {
        return tituloField.getText();
    }

    public String getAutores() {
        return autoresField.getText();
    }

    public String getEditora() {
        return editoraField.getText();
    }

    public String getPreco() {
        return precoField.getText();
    }

    public String getTipo() {
        Object selected = tipoCombo.getSelectedItem();
        return selected == null ? null : selected.toString();
    }

    public String getFrete() {
        return freteField.getText();
    }

    public String getEstoque() {
        return estoqueField.getText();
    }

    public String getTamanho() {
        return tamanhoField.getText();
    }

    public void clearForm() {
        tituloField.setText("");
        autoresField.setText("");
        editoraField.setText("");
        precoField.setText("");
        freteField.setText("");
        estoqueField.setText("");
        tamanhoField.setText("");
        tipoCombo.setSelectedIndex(0);
        updateDynamicFields();
    }
}
