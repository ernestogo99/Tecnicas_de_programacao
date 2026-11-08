package org.example.model;

import java.math.BigDecimal;

public class Portifolio {
    private int quantity;
    private BigDecimal averageCost;
    private BigDecimal accumulatedLoss;

    public Portifolio() {
        this.quantity = 0;
        this.averageCost = BigDecimal.ZERO;
        this.accumulatedLoss = BigDecimal.ZERO;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getAverageCost() {
        return averageCost;
    }

    public BigDecimal getAccumulatedLoss() {
        return accumulatedLoss;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAverageCost(BigDecimal averageCost) {
        this.averageCost = averageCost;
    }

    public void setAccumulatedLoss(BigDecimal accumulatedLoss) {
        this.accumulatedLoss = accumulatedLoss;
    }
}
