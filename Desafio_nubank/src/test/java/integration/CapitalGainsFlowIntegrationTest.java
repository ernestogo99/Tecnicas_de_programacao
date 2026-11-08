
package integration;

import org.example.controller.SimulationController;
import org.example.enums.FinancialResultType;
import org.example.enums.OperationType;
import org.example.model.Operation;
import org.example.model.OperationResult;
import org.example.model.SimulationResult;
import org.example.service.CapitalGainsService;

import org.example.service.PortFolioService;
import org.example.service.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CapitalGainsFlowIntegrationTest {

    private SimulationController controller;

    @BeforeEach
    void setUp() {
        PortFolioService portfolioService = new PortFolioService();
        TaxService taxService = new TaxService();

        CapitalGainsService capitalGainsService =
                new CapitalGainsService(portfolioService, taxService);

        controller = new SimulationController(capitalGainsService);
    }

    @Test
    @DisplayName("Deve processar compra e venda com incidência de imposto")
    void shouldCalculateTaxableProfitThroughCompleteFlow() {
        List<Operation> operations = List.of(
                new Operation(OperationType.BUY, new BigDecimal("10.00"), 1000),
                new Operation(OperationType.SELL, new BigDecimal("30.00"), 1000)
        );

        SimulationResult result = controller.simulate(operations);

        assertEquals(2, result.operationResults().size());

        OperationResult buyResult = result.operationResults().get(0);
        OperationResult sellResult = result.operationResults().get(1);

        assertEquals(BigDecimal.ZERO, buyResult.tax());

        assertEquals(FinancialResultType.PROFIT, sellResult.resultType());
        assertMoneyEquals("20000.00", sellResult.financialResult());
        assertMoneyEquals("4000.00", sellResult.tax());

        assertEquals(0, result.financialSummary().quantity());
        assertMoneyEquals("0.00", result.financialSummary().averageCost());
        assertMoneyEquals("0.00", result.financialSummary().accumulatedLoss());
        assertMoneyEquals("4000.00", result.financialSummary().totalTax());
    }

    @Test
    @DisplayName("Deve compensar prejuízo acumulado em lucro tributável futuro")
    void shouldCompensateAccumulatedLossThroughCompleteFlow() {
        List<Operation> operations = List.of(
                new Operation(OperationType.BUY, new BigDecimal("20.00"), 1000),
                new Operation(OperationType.SELL, new BigDecimal("15.00"), 1000),

                new Operation(OperationType.BUY, new BigDecimal("10.00"), 1000),
                new Operation(OperationType.SELL, new BigDecimal("25.00"), 1000)
        );

        SimulationResult result = controller.simulate(operations);

        assertEquals(4, result.operationResults().size());

        OperationResult lossResult = result.operationResults().get(1);
        OperationResult profitResult = result.operationResults().get(3);

        assertEquals(FinancialResultType.LOSS, lossResult.resultType());
        assertMoneyEquals("5000.00", lossResult.financialResult());
        assertMoneyEquals("0.00", lossResult.tax());

        assertEquals(FinancialResultType.PROFIT, profitResult.resultType());
        assertMoneyEquals("15000.00", profitResult.financialResult());
        assertMoneyEquals("2000.00", profitResult.tax());

        assertMoneyEquals("0.00", result.financialSummary().accumulatedLoss());
        assertMoneyEquals("2000.00", result.financialSummary().totalTax());
    }

    @Test
    @DisplayName("Deve rejeitar venda superior à quantidade disponível")
    void shouldRejectSaleWithInsufficientShares() {
        List<Operation> operations = List.of(
                new Operation(OperationType.BUY, new BigDecimal("10.00"), 100),
                new Operation(OperationType.SELL, new BigDecimal("20.00"), 200)
        );

        assertThrows(
                RuntimeException.class,
                () -> controller.simulate(operations)
        );
    }

    private void assertMoneyEquals(String expected, BigDecimal actual) {
        assertEquals(
                0,
                new BigDecimal(expected).compareTo(actual),
                "Os valores monetários devem ser iguais."
        );
    }
}
