package org.example.view;



import org.example.model.SimulationResult;
import org.example.view.ResultsTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class ResultsPanel extends JPanel {

    private final ResultsTableModel tableModel;
    private final JTable table;

    public ResultsPanel() {
        this.tableModel = new ResultsTableModel();
        this.table = new JTable(tableModel);

        configurePanel();
        configureTable();
        initializeComponents();
    }

    private void configurePanel() {
        setLayout(new BorderLayout(10, 10));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                        "Resultados da simulação",
                        TitledBorder.LEFT,
                        TitledBorder.TOP
                ),
                new EmptyBorder(15, 15, 15, 15)
        ));
    }

    private void configureTable() {
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(28);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        DefaultTableCellRenderer currencyRenderer =
                new DefaultTableCellRenderer() {

                    private final NumberFormat currencyFormat =
                            NumberFormat.getCurrencyInstance(
                                    Locale.forLanguageTag("pt-BR")
                            );

                    @Override
                    protected void setValue(Object value) {
                        if (value instanceof BigDecimal amount) {
                            setText(currencyFormat.format(amount));
                        } else {
                            super.setValue(value);
                        }
                    }
                };

        table.getColumnModel().getColumn(2)
                .setCellRenderer(currencyRenderer);

        table.getColumnModel().getColumn(4)
                .setCellRenderer(currencyRenderer);

        table.getColumnModel().getColumn(6)
                .setCellRenderer(currencyRenderer);
    }

    private void initializeComponents() {
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateResults(SimulationResult result) {
        Objects.requireNonNull(result, "O resultado da simulação é obrigatório.");

        tableModel.setResults(result.operationResults());
    }

    public void clearResults() {
        tableModel.clearResults();
    }
}
