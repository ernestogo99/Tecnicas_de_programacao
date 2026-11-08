package org.example.controller;

import org.example.interfaces.CapitalGainsCalculator;
import org.example.model.Operation;
import org.example.model.SimulationResult;

import java.util.List;
import java.util.Objects;

public class SimulationController {

    private final CapitalGainsCalculator calculator;

    public SimulationController(CapitalGainsCalculator calculator) {
        this.calculator = Objects.requireNonNull(
                calculator,
                "O calculador deve ser informado."
        );
    }

    public SimulationResult simulate(List<Operation> operations) {
        return calculator.calculate(operations);
    }
}
