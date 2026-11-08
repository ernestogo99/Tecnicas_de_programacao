package service;

import org.example.enums.FinancialResultType;
import org.example.enums.OperationType;
import org.example.exceptions.InvalidOperationException;
import org.example.exceptions.InvalidPriceException;
import org.example.exceptions.InvalidQuantityException;
import org.example.model.Operation;
import org.example.service.CapitalGainsService;
import org.example.service.PortFolioService;
import org.example.service.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CapitalGainsServiceTest {

    private CapitalGainsService service;

    @BeforeEach
    void setUp() {
        service = new CapitalGainsService(
                new PortFolioService(),
                new TaxService()
        );
    }

    @Test
    void shouldProcessBuyAndSellOperations() {
        List<Operation> operations = List.of(
                new Operation(
                        OperationType.BUY,
                        new BigDecimal("10.00"),
                        100
                ),
                new Operation(
                        OperationType.SELL,
                        new BigDecimal("15.00"),
                        50
                )
        );

        var result = service.calculate(operations);

        assertEquals(2, result.operationResults().size());

        assertEquals(
                FinancialResultType.NONE,
                result.operationResults().get(0).resultType()
        );

        assertEquals(
                FinancialResultType.PROFIT,
                result.operationResults().get(1).resultType()
        );

        assertEquals(
                0,
                new BigDecimal("250.00").compareTo(
                        result.operationResults().get(1).financialResult()
                )
        );

        assertEquals(50, result.financialSummary().quantity());

        assertEquals(
                0,
                new BigDecimal("10.00").compareTo(
                        result.financialSummary().averageCost()
                )
        );
    }

    @Test
    void shouldCalculateTaxForTaxableProfit() {
        List<Operation> operations = List.of(
                new Operation(
                        OperationType.BUY,
                        new BigDecimal("10.00"),
                        1000
                ),
                new Operation(
                        OperationType.SELL,
                        new BigDecimal("25.00"),
                        1000
                )
        );

        var result = service.calculate(operations);

        assertEquals(
                0,
                new BigDecimal("3000.00").compareTo(
                        result.financialSummary().totalTax()
                )
        );

        assertEquals(0, result.financialSummary().quantity());
    }

    @Test
    void shouldUseAccumulatedLossToCompensateFutureProfit() {
        List<Operation> operations = List.of(
                new Operation(
                        OperationType.BUY,
                        new BigDecimal("20.00"),
                        1000
                ),
                new Operation(
                        OperationType.SELL,
                        new BigDecimal("15.00"),
                        1000
                ),
                new Operation(
                        OperationType.BUY,
                        new BigDecimal("10.00"),
                        1000
                ),
                new Operation(
                        OperationType.SELL,
                        new BigDecimal("25.00"),
                        1000
                )
        );

        var result = service.calculate(operations);

        // Prejuízo anterior: R$ 5.000,00
        // Lucro posterior: R$ 15.000,00
        // Lucro tributável: R$ 10.000,00
        // Imposto: R$ 2.000,00

        assertEquals(
                0,
                new BigDecimal("2000.00").compareTo(
                        result.financialSummary().totalTax()
                )
        );

        assertEquals(
                0,
                BigDecimal.ZERO.compareTo(
                        result.financialSummary().accumulatedLoss()
                )
        );
    }
}