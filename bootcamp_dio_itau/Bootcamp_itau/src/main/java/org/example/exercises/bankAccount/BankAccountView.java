package org.example.exercises.bankAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class BankAccountView extends JFrame {
    private final BankAccountController controller;
    private final JLabel balanceLabel;
    private final JLabel specialLimitLabel;
    private final JLabel usingSpecialLabel;
    private final JTextArea logArea;
    private final JTextField amountField;

    public BankAccountView(BankAccountController controller) {
        super("Sistema Bancário");
        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        topPanel.add(new JLabel("Saldo atual:"));
        balanceLabel = new JLabel("R$ 0,00");
        topPanel.add(balanceLabel);

        topPanel.add(new JLabel("Limite do cheque especial:"));
        specialLimitLabel = new JLabel("R$ 0,00");
        topPanel.add(specialLimitLabel);

        topPanel.add(new JLabel("Usando cheque especial?"));
        usingSpecialLabel = new JLabel("Não");
        topPanel.add(usingSpecialLabel);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 10, 15));

        JLabel amountLabel = new JLabel("Valor:");
        amountField = new JTextField();
        centerPanel.add(amountLabel, BorderLayout.WEST);
        centerPanel.add(amountField, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        buttonsPanel.add(createButton("Consultar saldo", e -> controller.consultarSaldo()));
        buttonsPanel.add(createButton("Consultar cheque especial", e -> controller.consultarChequeEspecial()));
        buttonsPanel.add(createButton("Depositar", e -> controller.depositarDinheiro()));
        buttonsPanel.add(createButton("Sacar", e -> controller.sacarDinheiro()));
        buttonsPanel.add(createButton("Pagar boleto", e -> controller.pagarBoleto()));
        buttonsPanel.add(createButton("Verificar cheque", e -> controller.verificarUsoChequeEspecial()));

        add(buttonsPanel, BorderLayout.CENTER);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Log da conta"));
        add(scrollPane, BorderLayout.SOUTH);

        showMessage("Bem-vindo ao sistema bancário!");
    }

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    public BigDecimal readAmount(String message) {
        while (true) {
            String value = JOptionPane.showInputDialog(this, message, "Valor", JOptionPane.PLAIN_MESSAGE);
            if (value == null || value.trim().isEmpty()) {
                showMessage("Valor inválido. Digite um número válido.");
                continue;
            }

            try {
                return new BigDecimal(value.replace(",", ".").trim());
            } catch (NumberFormatException e) {
                showMessage("Valor inválido. Digite um número válido.");
            }
        }
    }

    public void setAccount(BankAccount account) {
        if (account == null) {
            return;
        }

        balanceLabel.setText(String.format("R$ %.2f", account.getBalance()));
        specialLimitLabel.setText(String.format("R$ %.2f", account.getSpecialLimit()));
        usingSpecialLabel.setText(account.isUsingSpecialCheck() ? "Sim" : "Não");
    }

    public void showMessage(String message) {
        logArea.append(message + "\n");
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void showWelcome() {
        JOptionPane.showMessageDialog(this, "Bem-vindo ao sistema bancário!", "Sistema Bancário", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getAmountFieldText() {
        return amountField.getText();
    }

    public void clearAmountField() {
        amountField.setText("");
    }
}
