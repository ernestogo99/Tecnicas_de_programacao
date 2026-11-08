package org.example.model;

import org.example.enums.OperationType;
import java.math.BigDecimal;

public record Operation(
        OperationType type,
        BigDecimal unitCost,
        int quantity
) {
}
