package org.example.swing;

import javax.swing.*;

public class JframeOne extends JFrame {

    public JframeOne(){
        initialize();
    }


    public void initialize(){
        setTitle("JframeOne");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
