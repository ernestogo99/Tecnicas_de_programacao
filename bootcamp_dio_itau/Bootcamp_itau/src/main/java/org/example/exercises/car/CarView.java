package org.example.exercises.car;

import javax.swing.*;
import java.awt.*;

public class CarView extends JFrame {
    private final CarController controller;
    private final JLabel engineLabel;
    private final JLabel speedLabel;
    private final JLabel gearLabel;
    private final JLabel directionLabel;
    private final JTextArea logArea;

    public CarView(CarController controller) {
        super("Controle do Carro");
        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel statusPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        statusPanel.add(new JLabel("Carro ligado:"));
        engineLabel = new JLabel("Não");
        statusPanel.add(engineLabel);

        statusPanel.add(new JLabel("Velocidade:"));
        speedLabel = new JLabel("0 km/h");
        statusPanel.add(speedLabel);

        statusPanel.add(new JLabel("Marcha:"));
        gearLabel = new JLabel("0");
        statusPanel.add(gearLabel);

        statusPanel.add(new JLabel("Direção:"));
        directionLabel = new JLabel("Reto");
        statusPanel.add(directionLabel);

        add(statusPanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 2, 12, 12));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        buttonsPanel.add(createButton("Ligar", e -> controller.ligarCarro()));
        buttonsPanel.add(createButton("Desligar", e -> controller.desligarCarro()));
        buttonsPanel.add(createButton("Acelerar", e -> controller.acelerar()));
        buttonsPanel.add(createButton("Diminuir velocidade", e -> controller.diminuirVelocidade()));
        buttonsPanel.add(createButton("Virar esquerda", e -> controller.virarEsquerda()));
        buttonsPanel.add(createButton("Virar direita", e -> controller.virarDireita()));
        buttonsPanel.add(createButton("Trocar marcha", e -> controller.trocarMarcha()));
        buttonsPanel.add(createButton("Verificar velocidade", e -> controller.verificarVelocidade()));

        add(buttonsPanel, BorderLayout.CENTER);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registro do veículo"));
        scrollPane.setPreferredSize(new Dimension(0, 150));
        add(scrollPane, BorderLayout.SOUTH);

        updateStatus(false, 0, 0, "Reto");
        showMessage("Carro criado. O veículo está desligado, em ponto morto e com velocidade zero.");
    }

    private JButton createButton(String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    public void updateStatus(boolean engineOn, int speed, int gear, String direction) {
        engineLabel.setText(engineOn ? "Sim" : "Não");
        speedLabel.setText(speed + " km/h");
        gearLabel.setText(String.valueOf(gear));
        directionLabel.setText(direction);
    }

    public void showMessage(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public int readGear() {
        String value = JOptionPane.showInputDialog(this, "Digite a marcha desejada (0 a 6):", "Trocar marcha", JOptionPane.PLAIN_MESSAGE);

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Você deve informar uma marcha válida.");
        }

        try {
            int gear = Integer.parseInt(value.trim());
            if (gear < 0 || gear > 6) {
                throw new IllegalArgumentException("A marcha deve estar entre 0 e 6.");
            }
            return gear;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Informe um número válido para a marcha.");
        }
    }
}
