package org.example.exercises.bankAccount;

import javax.swing.*;
import java.math.BigDecimal;

public class BankAccountController {
    private final BankAccountView view;
    private BankAccount account;

    public BankAccountController() {
        this.view = new BankAccountView(this);
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
            try {
                BigDecimal initialDeposit = view.readAmount("Digite o valor do depósito inicial da conta: R$ ");
                this.account = new BankAccount(initialDeposit);
                view.setAccount(account);
                view.showMessage("Conta criada com sucesso.");
                view.setVisible(true);
            } catch (IllegalArgumentException e) {
                view.showError(e.getMessage());
            }
        });
    }

    public void consultarSaldo() {
        if (account == null) {
            view.showError("Crie uma conta antes de consultar o saldo.");
            return;
        }

        String message = String.format("Saldo atual: R$ %.2f", account.getBalance());
        view.showMessage(message);
        view.setAccount(account);
    }

    public void consultarChequeEspecial() {
        if (account == null) {
            view.showError("Crie uma conta antes de consultar o cheque especial.");
            return;
        }

        String message = String.format(
                "Limite do cheque especial: R$ %.2f\nValor usado no cheque especial: R$ %.2f",
                account.getSpecialLimit(),
                account.getUsedSpecialLimit()
        );
        view.showMessage(message);
        view.setAccount(account);
    }

    public void depositarDinheiro() {
        if (account == null) {
            view.showError("Crie uma conta antes de realizar o depósito.");
            return;
        }

        try {
            BigDecimal value = view.readAmount("Informe o valor do depósito: R$ ");
            account.deposit(value);
            view.showMessage("Depósito realizado com sucesso.");
            view.setAccount(account);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    public void sacarDinheiro() {
        if (account == null) {
            view.showError("Crie uma conta antes de realizar o saque.");
            return;
        }

        try {
            BigDecimal value = view.readAmount("Informe o valor do saque: R$ ");
            account.withdraw(value);
            view.showMessage("Saque realizado com sucesso.");
            view.setAccount(account);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    public void pagarBoleto() {
        if (account == null) {
            view.showError("Crie uma conta antes de pagar o boleto.");
            return;
        }

        try {
            BigDecimal value = view.readAmount("Informe o valor do boleto: R$ ");
            account.payBill(value);
            view.showMessage("Boleto pago com sucesso.");
            view.setAccount(account);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    public void verificarUsoChequeEspecial() {
        if (account == null) {
            view.showError("Crie uma conta antes de verificar o cheque especial.");
            return;
        }

        String status = account.isUsingSpecialCheck() ? "Sim" : "Não";
        view.showMessage("A conta está usando cheque especial? " + status);
        view.setAccount(account);
    }

    public static void main(String[] args) {
        new BankAccountController().run();
    }
}
