package org.example.swing;

import javax.swing.*;
import java.awt.*;

public class TestWindow {
    private JFrame window;

    public TestWindow(){
        window=new JFrame();
        window.setTitle("Hello world");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(900,500);
        window.setLocationRelativeTo(null);

        JPanel panel=new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        panel.setBackground(Color.black);

        panel.setPreferredSize(new Dimension(250,100));

        Button button=new Button("Button");

        window.add(panel, BorderLayout.CENTER);
        panel.add(button);
    }


    public void show(){
        window.setVisible(true);
    }
}
