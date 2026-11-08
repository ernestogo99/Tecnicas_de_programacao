package org.example.model;

import org.example.enums.FinancialResultType;
import org.example.exceptions.InvalidOperationException;

import java.math.BigDecimal;
import java.util.Objects;

public record OperationResult(
        int operationNumber,
        Operation operation,
        FinancialResultType resultType,
        BigDecimal financialResult,
        BigDecimal tax
) {

    public OperationResult {
        if (operationNumber <= 0) {
            throw new InvalidOperationException();
        }

        Objects.requireNonNull(operation, "A operação é obrigatória.");
        Objects.requireNonNull(resultType, "O tipo de resultado é obrigatório.");
        Objects.requireNonNull(financialResult, "O resultado financeiro é obrigatório.");
        Objects.requireNonNull(tax, "O imposto é obrigatório.");
    }
}
