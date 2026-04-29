import javax.swing.*;

public class Tst {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Exemplo GUI");
        JButton botao = new JButton("Clique aqui");

        // Adiciona ação ao botão
        botao.addActionListener(e -> JOptionPane.showMessageDialog(null, "Olá Mundo!"));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(botao);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
