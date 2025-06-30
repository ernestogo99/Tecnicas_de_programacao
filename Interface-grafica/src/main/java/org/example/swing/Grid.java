package org.example.swing;

import javax.swing.*;
import java.awt.*;

public class Grid {
    private JFrame frame;
    private JPanel panel;

    public Grid(){
        initialize();
    }

    public void initialize(){
        frame=new JFrame();
        frame.setTitle("GridLayout Demo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,500);




        panel = new JPanel(new GridLayout(4,5,10,10));


        for(int i=1;i<=20;i++){
            JButton button=new JButton("button"+ Integer.toString(i));
            panel.add(button);
        }

        frame.add(panel);


        frame.pack();
        frame.setLocationRelativeTo(null);

    }

    public void show(){
        frame.setVisible(true);
    }
}
