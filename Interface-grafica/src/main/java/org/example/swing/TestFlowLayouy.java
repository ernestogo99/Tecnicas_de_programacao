package org.example.swing;

import javax.swing.*;
import java.awt.*;

public class TestFlowLayouy {
    private JFrame frame;
    private JPanel panel;

    public TestFlowLayouy(){
        initialize();
    }

    public void initialize(){
        frame=new JFrame();
        frame.setTitle("FlowLayout Demo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,500);




        panel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        panel.setBackground(Color.PINK);

        for(int i=0;i<5;i++){
            JButton button=new JButton("button"+ Integer.toString(i));
            panel.add(button);
        }

        frame.add(panel,BorderLayout.CENTER);



        frame.setLocationRelativeTo(null);

    }

    public void show(){
        frame.setVisible(true);
    }
}
