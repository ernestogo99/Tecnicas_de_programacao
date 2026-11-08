package org.example.service;

import org.example.exceptions.InvalidOperationException;
import org.example.exceptions.InvalidPriceException;
import org.example.exceptions.InvalidQuantityException;
import org.example.model.Operation;

import java.math.BigDecimal;
import java.util.List;

public final class OperationValidator {

    private OperationValidator() {
    }

    public static void validate(List<Operation> operations) {
        if (operations == null || operations.isEmpty()) {
            throw new InvalidOperationException(
                    "A simulação deve possuir pelo menos uma operação."
            );
        }

        for (Operation operation : operations) {
            validateOperation(operation);
        }
    }

    private static void validateOperation(Operation operation) {
        if (operation == null || operation.type() == null) {
            throw new InvalidOperationException(
                    "A operação e seu tipo devem ser informados."
            );
        }

        validatePrice(operation.unitCost());
        validateQuantity(operation.quantity());
    }

    private static void validatePrice(BigDecimal unitCost) {
        if (unitCost == null || unitCost.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPriceException(
                    "O preço unitário deve ser maior que zero."
            );
        }
    }

    private static void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException(
                    "A quantidade deve ser maior que zero."
            );
        }
    }
}