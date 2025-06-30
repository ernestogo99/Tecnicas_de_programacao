package org.example.swing;

import javax.swing.*;
import java.awt.*;

public class BorderLayouttt {
    private JFrame frame;

    public BorderLayouttt(){
        initialize();
    }

    public void initialize(){
        frame=new JFrame();
        frame.setTitle("BorderPane Demo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,500);
        frame.setLayout(new BorderLayout(10,10));

        frame.add(new JButton("North button"),BorderLayout.NORTH);
        frame.add(new JButton("West button"),BorderLayout.WEST);
        frame.add(new JButton("East button"),BorderLayout.EAST);
        frame.add(new JButton("South button"),BorderLayout.SOUTH);
        frame.add(new JButton("Center button"),BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);

    }

    public void show(){
        frame.setVisible(true);
    }
}
