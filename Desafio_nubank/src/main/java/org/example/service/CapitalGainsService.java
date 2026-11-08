package org.example.service;

import org.example.enums.FinancialResultType;
import org.example.enums.OperationType;
import org.example.exceptions.InsufficientSharesException;
import org.example.exceptions.InvalidOperationException;
import org.example.exceptions.InvalidPriceException;
import org.example.exceptions.InvalidQuantityException;
import org.example.interfaces.CapitalGainsCalculator;
import org.example.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CapitalGainsService implements CapitalGainsCalculator {

    private final PortFolioService portfolioService;
    private final TaxService taxService;

    public CapitalGainsService(
            PortFolioService portfolioService,
            TaxService taxService
    ) {
        this.portfolioService = portfolioService;
        this.taxService = taxService;
    }

    @Override
    public SimulationResult calculate(List<Operation> operations) {
        OperationValidator.validate(operations);

        Portifolio portfolio = new Portifolio();
        List<OperationResult> operationResults = new ArrayList<>();

        BigDecimal totalTax = BigDecimal.ZERO;

        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);

            BigDecimal financialResult = BigDecimal.ZERO;
            BigDecimal tax = BigDecimal.ZERO;
            FinancialResultType resultType = FinancialResultType.NONE;

            if (operation.type() == OperationType.BUY) {
                portfolioService.processBuy(operation, portfolio);
            } else if (operation.type() == OperationType.SELL) {
                financialResult = portfolioService.processSell(operation, portfolio);

                resultType = classifyFinancialResult(financialResult);

                tax = taxService.calculateTax(
                        operation,
                        financialResult,
                        portfolio
                );
            }

            totalTax = totalTax.add(tax);

            operationResults.add(
                    new OperationResult(
                            i + 1,
                            operation,
                            resultType,
                            financialResult,
                            tax
                    )
            );
        }

        FinancialSummary financialSummary = new FinancialSummary(
                portfolio.getQuantity(),
                portfolio.getAverageCost(),
                portfolio.getAccumulatedLoss(),
                totalTax
        );

        return new SimulationResult(operationResults, financialSummary);
    }

    private FinancialResultType classifyFinancialResult(BigDecimal financialResult) {
        if (financialResult.compareTo(BigDecimal.ZERO) > 0) {
            return FinancialResultType.PROFIT;
        }

        if (financialResult.compareTo(BigDecimal.ZERO) < 0) {
            return FinancialResultType.LOSS;
        }

        return FinancialResultType.NONE;
    }
}
