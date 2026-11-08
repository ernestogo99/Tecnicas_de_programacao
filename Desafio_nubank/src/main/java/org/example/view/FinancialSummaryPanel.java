package org.example.view;

import org.example.model.FinancialSummary;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class FinancialSummaryPanel extends JPanel {

    private final JLabel quantityValueLabel;
    private final JLabel averageCostValueLabel;
    private final JLabel accumulatedLossValueLabel;
    private final JLabel totalTaxValueLabel;

    private final NumberFormat currencyFormat;

    public FinancialSummaryPanel() {
        this.quantityValueLabel = new JLabel("0");
        this.averageCostValueLabel = new JLabel("R$ 0,00");
        this.accumulatedLossValueLabel = new JLabel("R$ 0,00");
        this.totalTaxValueLabel = new JLabel("R$ 0,00");

        this.currencyFormat = NumberFormat.getCurrencyInstance(
                Locale.forLanguageTag("pt-BR")
        );

        configurePanel();
        initializeComponents();
    }

    private void configurePanel() {
        setLayout(new BorderLayout(10, 10));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                        "Resumo financeiro",
                        TitledBorder.LEFT,
                        TitledBorder.TOP
                ),
                new EmptyBorder(15, 15, 15, 15)
        ));
    }

    private void initializeComponents() {
        JPanel summaryGrid = new JPanel(new GridLayout(2, 2, 20, 15));

        summaryGrid.add(createSummaryItem(
                "Quantidade em carteira",
                quantityValueLabel
        ));

        summaryGrid.add(createSummaryItem(
                "Preço médio",
                averageCostValueLabel
        ));

        summaryGrid.add(createSummaryItem(
                "Prejuízo acumulado",
                accumulatedLossValueLabel
        ));

        summaryGrid.add(createSummaryItem(
                "Imposto total",
                totalTaxValueLabel
        ));

        add(summaryGrid, BorderLayout.CENTER);
    }

    private JPanel createSummaryItem(String title, JLabel valueLabel) {
        JPanel item = new JPanel();
        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        valueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        item.add(titleLabel);
        item.add(Box.createVerticalStrut(5));
        item.add(valueLabel);

        return item;
    }

    public void updateSummary(FinancialSummary summary) {
        Objects.requireNonNull(
                summary,
                "O resumo financeiro deve ser informado."
        );

        quantityValueLabel.setText(
                String.valueOf(summary.quantity())
        );

        averageCostValueLabel.setText(
                formatCurrency(summary.averageCost())
        );

        accumulatedLossValueLabel.setText(
                formatCurrency(summary.accumulatedLoss())
        );

        totalTaxValueLabel.setText(
                formatCurrency(summary.totalTax())
        );
    }

    public void clearSummary() {
        quantityValueLabel.setText("0");
        averageCostValueLabel.setText(formatCurrency(BigDecimal.ZERO));
        accumulatedLossValueLabel.setText(formatCurrency(BigDecimal.ZERO));
        totalTaxValueLabel.setText(formatCurrency(BigDecimal.ZERO));
    }

    private String formatCurrency(BigDecimal value) {
        return currencyFormat.format(value);
    }
}
