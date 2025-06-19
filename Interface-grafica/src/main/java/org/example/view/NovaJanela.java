package org.example.view;

import javax.swing.*;
import java.awt.*;

public class NovaJanela extends JFrame {
    public NovaJanela(String titulo, boolean setVisible){
        super(titulo);
        JPanel pane=new JPanel();
        setContentPane(pane);
        TextArea text=new TextArea("deu bom");
        setVisible(setVisible);
    }
}
