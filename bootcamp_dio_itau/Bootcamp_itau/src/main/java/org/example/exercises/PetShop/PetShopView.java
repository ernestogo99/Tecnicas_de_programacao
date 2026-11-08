package org.example.exercises.PetShop;

import javax.swing.*;
import java.awt.*;

public class PetShopView extends JFrame {
    private final PetShopController controller;
    private final JLabel waterLabel;
    private final JLabel shampooLabel;
    private final JLabel petStatusLabel;
    private final JLabel cleaningStatusLabel;
    private final JTextArea logArea;

    public PetShopView(PetShopController controller) {
        super("Pet Shop - Máquina de Banho");
        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel statusPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        statusPanel.add(new JLabel("Nível de água:"));
        waterLabel = new JLabel("0 litros");
        statusPanel.add(waterLabel);

        statusPanel.add(new JLabel("Nível de shampoo:"));
        shampooLabel = new JLabel("0 litros");
        statusPanel.add(shampooLabel);

        statusPanel.add(new JLabel("Pet na máquina:"));
        petStatusLabel = new JLabel("Não");
        statusPanel.add(petStatusLabel);

        statusPanel.add(new JLabel("Máquina precisa limpar?"));
        cleaningStatusLabel = new JLabel("Não");
        statusPanel.add(cleaningStatusLabel);

        add(statusPanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 2, 12, 12));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        buttonsPanel.add(createButton("Colocar pet", e -> controller.colocarPet()));
        buttonsPanel.add(createButton("Retirar pet", e -> controller.retirarPet()));
        buttonsPanel.add(createButton("Dar banho", e -> controller.darBanho()));
        buttonsPanel.add(createButton("Abastecer água", e -> controller.abastecerAgua()));
        buttonsPanel.add(createButton("Abastecer shampoo", e -> controller.abastecerShampoo()));
        buttonsPanel.add(createButton("Verificar água", e -> controller.verificarAgua()));
        buttonsPanel.add(createButton("Verificar shampoo", e -> controller.verificarShampoo()));
        buttonsPanel.add(createButton("Verificar pet", e -> controller.verificarPetNaMaquina()));
        buttonsPanel.add(createButton("Limpar máquina", e -> controller.limparMaquina()));

        add(buttonsPanel, BorderLayout.CENTER);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Log da máquina"));
        scrollPane.setPreferredSize(new Dimension(0, 150));
        add(scrollPane, BorderLayout.SOUTH);

        showMessage("Máquina de banho pronta para uso.");
    }

    private JButton createButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    public void updateStatus(int waterLevel, int shampooLevel, boolean petInMachine, boolean machineNeedsCleaning) {
        waterLabel.setText(waterLevel + " litros");
        shampooLabel.setText(shampooLevel + " litros");
        petStatusLabel.setText(petInMachine ? "Sim" : "Não");
        cleaningStatusLabel.setText(machineNeedsCleaning ? "Sim" : "Não");
    }

    public void showMessage(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public String readPetName() {
        String value = JOptionPane.showInputDialog(this, "Digite o nome do pet:", "Pet", JOptionPane.PLAIN_MESSAGE);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do pet é obrigatório.");
        }
        return value.trim();
    }
}
