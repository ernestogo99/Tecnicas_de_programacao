package org.example.views;

import javax.swing.*;

public class Cadastrar extends JFrame {
    public Cadastrar(String titulo){
        super(titulo);
        JPanel panel= new JPanel();
        JLabel number= new JLabel("número");
        JTextField numberField=new JTextField();
        JLabel name=new JLabel("nome");
        JTextField nameField=new JTextField();
        JButton button=new JButton("ok");

        panel.add(number);
        panel.add(numberField);
        panel.add(name);
        panel.add(nameField);
        panel.add(button);
        setContentPane(panel);
        pack();
        setVisible(true);

    }
}
