package controller;


import org.example.controller.SimulationController;
import org.example.interfaces.CapitalGainsCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimulationControllerTest {

    private SimulationController controller;

    @BeforeEach
    void setUp() {
        CapitalGainsCalculator fakeCalculator = operations -> null;
        controller = new SimulationController(fakeCalculator);
    }

    @Test
    void shouldDelegateSimulationToCalculator() {
        boolean[] called = {false};

        CapitalGainsCalculator fakeCalculator = operations -> {
            called[0] = true;
            return null;
        };

        SimulationController testController =
                new SimulationController(fakeCalculator);

        testController.simulate(List.of());

        assertTrue(called[0]);
    }



    @Test
    void shouldPropagateExceptionFromCalculator() {
        CapitalGainsCalculator failingCalculator = operations -> {
            throw new IllegalArgumentException("Erro de simulação");
        };

        SimulationController failingController =
                new SimulationController(failingCalculator);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> failingController.simulate(List.of())
        );

        assertTrue(exception.getMessage().contains("Erro de simulação"));
    }
}
