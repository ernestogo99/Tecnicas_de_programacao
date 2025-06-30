package org.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustonButton {
    private JFrame frame;
    private JPanel panel;
    private JButton button;

    public CustonButton(){
        initialize();
    }

    public void initialize(){
        frame=new JFrame();
        frame.setTitle("GridLayout Demo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,500);




        panel = new JPanel();

        button=new JButton("button");
        button.setFocusable(false);
        ImageIcon facebookIcon=new ImageIcon("face.png");
        button.setIcon(facebookIcon);
        button.setIconTextGap(10);
        button.setToolTipText("Some awesome test");
        button.setFont(new Font("Arial",Font.PLAIN,16));
        button.setMargin(new Insets(10,10,10,10));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button has been clicked");
            }
        });
        //button.setEnabled(false);

        button.doClick();
        button.setPreferredSize(new Dimension(200,70));
        frame.add(panel);
        panel.add(button);



        frame.setLocationRelativeTo(null);

    }

    public void show(){
        frame.setVisible(true);
    }
}
