package org.example.view;
import javax.swing.*;

public class MinhaJanela extends  JFrame {
   public MinhaJanela(String titulo){
        super(titulo);
        JPanel pane= new JPanel();
        JButton button=new JButton("Texto botão");
        pane.add(button);
        setContentPane(pane);
        pack();
        setVisible(true);
    }
}
