package org.example.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListernerTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ActionListener Exemplo");
        JButton botao = new JButton("Clique aqui");


        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Botão clicado!");
            }
        });

        frame.add(botao);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
