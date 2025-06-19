package org.example.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeuOuvinte implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        Object fonte= event.getSource();
        System.out.println();
    }
}
