package org.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Labell {
    private JFrame frame;
    private JPanel panel;

    public Labell(){
        initialize();
    }

    public void initialize(){
        frame=new JFrame();
        frame.setTitle("JLabel Demo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,500);
        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        frame.add(panel,BorderLayout.NORTH);

        frame.setLocationRelativeTo(null);

        JLabel label=new JLabel("text");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Sans-serif",Font.BOLD,36));
        ImageIcon facebookIcon=new ImageIcon("face.png");
        label.setIcon(facebookIcon);
        JButton button=new JButton("Update text");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("<html>My text<br>  is so cool </html>");
            }
        });
        panel.add(label);
        frame.add(button,BorderLayout.SOUTH);
    }

    public void show(){
        frame.setVisible(true);
    }
}
