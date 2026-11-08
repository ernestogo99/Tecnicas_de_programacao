package org.example.model;

import org.example.exceptions.InvalidQuantityException;

import java.math.BigDecimal;
import java.util.Objects;

public record FinancialSummary(
        int quantity,
        BigDecimal averageCost,
        BigDecimal accumulatedLoss,
        BigDecimal totalTax
) {

    public FinancialSummary {
        if (quantity < 0) {
            throw new InvalidQuantityException();
        }

        Objects.requireNonNull(averageCost, "O preço médio é obrigatório.");
        Objects.requireNonNull(accumulatedLoss, "O prejuízo acumulado é obrigatório.");
        Objects.requireNonNull(totalTax, "O imposto total é obrigatório.");
    }
}
