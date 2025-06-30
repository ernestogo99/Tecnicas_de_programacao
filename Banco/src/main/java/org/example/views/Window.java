package org.example.views;

import javax.swing.*;

public class Window extends JFrame {
    public Window(String titulo){
        super(titulo);
        JPanel pane= new JPanel();
        JButton button=new JButton("Cadastrar");
        pane.add(button);
        setContentPane(pane);
        pack();
        setVisible(true);
    }
}
