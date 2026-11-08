package org.example.interfaces;

import org.example.model.Operation;
import org.example.model.SimulationResult;

import java.util.List;

public interface CapitalGainsCalculator {
    SimulationResult calculate(List<Operation> operations);
}
