package org.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Texfieldd {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;

    public Texfieldd(){
        initialize();
    }

    public void initialize(){
        frame=new JFrame();
        frame.setTitle("JtextField Demo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,500);
        frame.setLayout(new BorderLayout(10,10));
        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        frame.setLocationRelativeTo(null);


        //textfield

        textField= createJTextField();
        panel.add(textField);
        label = new JLabel("field");
        label.setFont(new Font("Arial",Font.BOLD,24));


        panel.add(label);

        frame.add(panel,BorderLayout.CENTER);

    }

    public void show(){
        frame.setVisible(true);
    }

    public JTextField createJTextField(){
       JTextField textField=new JTextField(10);

       textField.setFont(new Font("Arial",Font.BOLD,24));
       textField.setForeground(Color.blue);
       textField.setBackground(Color.YELLOW);
       textField.setToolTipText("Enter some text");
       textField.setMargin(new Insets(5,10,5,10));

       textField.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               label.setText(textField.getText());
           }
       });

       return  textField;
    }

}
