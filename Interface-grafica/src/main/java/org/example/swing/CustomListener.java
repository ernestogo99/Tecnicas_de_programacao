package org.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomListener implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;

    public CustomListener(){
        initialize();
    }

    public void initialize(){
        frame=new JFrame();
        frame.setTitle("Listener demo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,500);
        frame.setLayout(new BorderLayout(10,10));
        frame.setLocationRelativeTo(null);

        panel=new JPanel();
        label= new JLabel("Enter your name");
        textField=new JTextField(20);
        button=new JButton("Save");

        /*button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"Button clicked");
            }
        });*/

        button.addActionListener(this);

        panel.add(label);
        panel.add(textField);
        panel.add(button);

        frame.add(panel,BorderLayout.CENTER);

    }

    public void show(){
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(frame,"Button clicked");
    }
}
