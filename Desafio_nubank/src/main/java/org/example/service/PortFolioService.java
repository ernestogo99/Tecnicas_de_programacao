package org.example.service;

import org.example.exceptions.InsufficientSharesException;
import org.example.model.Operation;
import org.example.model.Portifolio;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PortFolioService {
    private static final int MONEY_SCALE = 2;

    public PortFolioService(){

    }




    public void processBuy(Operation operation, Portifolio portfolio) {

        int currentQuantity = portfolio.getQuantity();
        int purchasedQuantity = operation.quantity();

        BigDecimal currentAverageCost = portfolio.getAverageCost();
        BigDecimal purchaseCost = operation.unitCost();

        BigDecimal currentTotalCost = currentAverageCost.multiply(
                BigDecimal.valueOf(currentQuantity)
        );

        BigDecimal purchaseTotalCost = purchaseCost.multiply(
                BigDecimal.valueOf(purchasedQuantity)
        );

        int newQuantity = currentQuantity + purchasedQuantity;

        BigDecimal newAverageCost = currentTotalCost
                .add(purchaseTotalCost)
                .divide(
                        BigDecimal.valueOf(newQuantity),
                        MONEY_SCALE,
                        RoundingMode.HALF_UP
                );

        portfolio.setQuantity(newQuantity);
        portfolio.setAverageCost(newAverageCost);
    }

    public BigDecimal processSell(
            Operation operation,
            Portifolio portfolio
    ) {
        validateAvailableShares(operation, portfolio);

        BigDecimal financialResult = operation.unitCost()
                .subtract(portfolio.getAverageCost())
                .multiply(BigDecimal.valueOf(operation.quantity()));

        int remainingQuantity = portfolio.getQuantity()
                - operation.quantity();

        portfolio.setQuantity(remainingQuantity);

        if (remainingQuantity == 0) {
            portfolio.setAverageCost(BigDecimal.ZERO);
        }

        return financialResult.setScale(
                MONEY_SCALE,
                RoundingMode.HALF_UP
        );
    }

    private void validateAvailableShares(
            Operation operation,
            Portifolio portfolio
    ) {
        if (operation.quantity() > portfolio.getQuantity()) {
            throw new InsufficientSharesException(
                    "A quantidade de ações disponíveis é insuficiente para realizar a venda."
            );
        }
    }
}
