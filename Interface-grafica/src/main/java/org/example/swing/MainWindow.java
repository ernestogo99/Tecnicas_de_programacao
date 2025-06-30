package org.example.swing;

import javax.swing.*;

public class MainWindow {
    public static  void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CustomListener main=new CustomListener();
                main.show();
            }
        });
    }
}
