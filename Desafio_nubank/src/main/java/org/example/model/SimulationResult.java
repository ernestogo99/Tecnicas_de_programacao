package org.example.model;

import java.util.List;
import java.util.Objects;

public record SimulationResult(
        List<OperationResult> operationResults,
        FinancialSummary financialSummary
) {

    public SimulationResult {
        Objects.requireNonNull(
                operationResults,
                "Os resultados das operações são obrigatórios."
        );

        Objects.requireNonNull(
                financialSummary,
                "O resumo financeiro é obrigatório."
        );

        operationResults = List.copyOf(operationResults);
    }
}
