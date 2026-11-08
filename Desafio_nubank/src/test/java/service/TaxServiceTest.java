package service;

import org.example.enums.OperationType;
import org.example.model.Operation;
import org.example.model.Portifolio;
import org.example.service.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxServiceTest {

    private TaxService taxService;
    private Portifolio portfolio;

    @BeforeEach
    void setUp() {
        taxService = new TaxService();
        portfolio = new Portifolio();
    }

    @Test
    void shouldAccumulateLossAndReturnZeroTax() {
        BigDecimal tax = taxService.calculateTax(
                createSale("8.00", 100),
                new BigDecimal("-200.00"),
                portfolio
        );

        assertMoneyEquals("0.00", tax);
        assertMoneyEquals("200.00", portfolio.getAccumulatedLoss());
    }

    @Test
    void shouldNotConsumeAccumulatedLossOnExemptSale() {
        portfolio.setAccumulatedLoss(new BigDecimal("2000.00"));

        BigDecimal tax = taxService.calculateTax(
                createSale("150.00", 100),
                new BigDecimal("3000.00"),
                portfolio
        );

        assertMoneyEquals("0.00", tax);
        assertMoneyEquals("2000.00", portfolio.getAccumulatedLoss());
    }

    @Test
    void shouldCompensateAccumulatedLossBeforeCalculatingTax() {
        portfolio.setAccumulatedLoss(new BigDecimal("2000.00"));

        BigDecimal tax = taxService.calculateTax(
                createSale("300.00", 100),
                new BigDecimal("5000.00"),
                portfolio
        );

        assertMoneyEquals("600.00", tax);
        assertMoneyEquals("0.00", portfolio.getAccumulatedLoss());
    }

    @Test
    void shouldPreserveRemainingLossAfterFullCompensation() {
        portfolio.setAccumulatedLoss(new BigDecimal("5000.00"));

        BigDecimal tax = taxService.calculateTax(
                createSale("300.00", 100),
                new BigDecimal("2000.00"),
                portfolio
        );

        assertMoneyEquals("0.00", tax);
        assertMoneyEquals("3000.00", portfolio.getAccumulatedLoss());
    }

    @Test
    void shouldCalculateTaxWithoutAccumulatedLoss() {
        BigDecimal tax = taxService.calculateTax(
                createSale("300.00", 100),
                new BigDecimal("5000.00"),
                portfolio
        );

        assertMoneyEquals("1000.00", tax);
    }

    @Test
    void shouldNotCalculateTaxWhenFinancialResultIsZero() {
        portfolio.setAccumulatedLoss(new BigDecimal("1000.00"));

        BigDecimal tax = taxService.calculateTax(
                createSale("100.00", 100),
                BigDecimal.ZERO,
                portfolio
        );

        assertMoneyEquals("0.00", tax);
        assertMoneyEquals("1000.00", portfolio.getAccumulatedLoss());
    }

    private Operation createSale(String unitCost, int quantity) {
        return new Operation(
                OperationType.SELL,
                new BigDecimal(unitCost),
                quantity
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
