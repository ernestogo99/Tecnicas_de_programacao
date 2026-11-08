package org.example.exercises.bankAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankAccount {
    private static final BigDecimal LIMIT_THRESHOLD = new BigDecimal("500.00");
    private static final BigDecimal SPECIAL_TAX = new BigDecimal("0.20");

    private BigDecimal balance;
    private final BigDecimal specialLimit;

    public BankAccount(BigDecimal initialDeposit) {
        if (initialDeposit == null || initialDeposit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O depósito inicial deve ser maior que zero.");
        }

        this.balance = initialDeposit.setScale(2, RoundingMode.HALF_UP);
        this.specialLimit = initialDeposit.compareTo(LIMIT_THRESHOLD) <= 0
                ? new BigDecimal("50.00")
                : initialDeposit.multiply(new BigDecimal("0.50")).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getBalance() {
        return balance.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSpecialLimit() {
        return specialLimit.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getUsedSpecialLimit() {
        return isUsingSpecialCheck() ? balance.abs().setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    public boolean isUsingSpecialCheck() {
        return balance.compareTo(BigDecimal.ZERO) < 0;
    }

    public void deposit(BigDecimal value) {
        validateAmount(value);
        balance = balance.add(value).setScale(2, RoundingMode.HALF_UP);
    }

    public void withdraw(BigDecimal value) {
        validateAmount(value);

        BigDecimal availableBalance = balance.add(specialLimit);
        if (value.compareTo(availableBalance) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente para essa operação.");
        }

        BigDecimal updatedBalance = balance.subtract(value);
        if (updatedBalance.compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal usedValue = updatedBalance.abs();
            BigDecimal fee = usedValue.multiply(SPECIAL_TAX).setScale(2, RoundingMode.HALF_UP);
            balance = updatedBalance.subtract(fee).setScale(2, RoundingMode.HALF_UP);
            return;
        }

        balance = updatedBalance.setScale(2, RoundingMode.HALF_UP);
    }

    public void payBill(BigDecimal value) {
        withdraw(value);
    }

    private void validateAmount(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }
}
