package org.example.service;

import org.example.model.Operation;
import org.example.model.Portifolio;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxService {

    private static final BigDecimal TAX_EXEMPTION_LIMIT =
            new BigDecimal("20000.00");

    private static final BigDecimal TAX_RATE =
            new BigDecimal("0.20");

    private static final int MONEY_SCALE = 2;

    public BigDecimal calculateTax(
            Operation operation,
            BigDecimal financialResult,
            Portifolio portfolio
    ) {
        if (financialResult.compareTo(BigDecimal.ZERO) < 0) {
            accumulateLoss(financialResult, portfolio);
            return BigDecimal.ZERO;
        }

        if (financialResult.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal grossValue = calculateGrossValue(operation);

        if (isTaxExempt(grossValue)) {
            return BigDecimal.ZERO;
        }

        BigDecimal taxableProfit = compensateLoss(
                financialResult,
                portfolio
        );

        return taxableProfit.multiply(TAX_RATE)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateGrossValue(Operation operation) {
        return operation.unitCost()
                .multiply(BigDecimal.valueOf(operation.quantity()));
    }

    private boolean isTaxExempt(BigDecimal grossValue) {
        return grossValue.compareTo(TAX_EXEMPTION_LIMIT) <= 0;
    }

    private void accumulateLoss(
            BigDecimal financialResult,
            Portifolio portfolio
    ) {
        BigDecimal loss = financialResult.abs();

        BigDecimal accumulatedLoss = portfolio.getAccumulatedLoss()
                .add(loss);

        portfolio.setAccumulatedLoss(accumulatedLoss);
    }

    private BigDecimal compensateLoss(
            BigDecimal financialResult,
            Portifolio portfolio
    ) {
        BigDecimal accumulatedLoss = portfolio.getAccumulatedLoss();

        BigDecimal compensatedLoss = financialResult.min(accumulatedLoss);

        BigDecimal taxableProfit = financialResult.subtract(compensatedLoss);

        BigDecimal remainingLoss = accumulatedLoss.subtract(compensatedLoss);

        portfolio.setAccumulatedLoss(remainingLoss);

        return taxableProfit;
    }
}
