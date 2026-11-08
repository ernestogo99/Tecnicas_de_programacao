package org.example.view;



import org.example.model.Operation;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OperationsTablePanel extends JPanel {

    private final OperationTableModel tableModel;
    private final JTable table;
    private final Runnable onOperationsChanged;

    public OperationsTablePanel(Runnable onOperationsChanged) {
        this.onOperationsChanged = onOperationsChanged;

        this.tableModel = new OperationTableModel();
        this.table = new JTable(tableModel);

        configurePanel();
        configureTable();
        initializeComponents();
    }

    public OperationsTablePanel() {
        this(() -> {});
    }




    private void configurePanel() {
        setLayout(new BorderLayout(10, 10));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                        "Operações cadastradas",
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
                        if (value instanceof Number number) {
                            setText(currencyFormat.format(number));
                        } else {
                            super.setValue(value);
                        }
                    }
                };

        table.getColumnModel()
                .getColumn(1)
                .setCellRenderer(currencyRenderer);
    }

    private void initializeComponents() {
        JScrollPane scrollPane = new JScrollPane(table);

        JButton removeButton = new JButton("Remover selecionada");
        JButton clearButton = new JButton("Limpar operações");

        removeButton.addActionListener(event -> removeSelectedOperation());
        clearButton.addActionListener(event -> clearOperations());

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT)
        );

        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addOperation(Operation operation) {
        tableModel.addOperation(operation);
        onOperationsChanged.run();
    }

    private void removeSelectedOperation() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        int modelRow = table.convertRowIndexToModel(selectedRow);

        tableModel.removeOperation(modelRow);
        onOperationsChanged.run();
    }

    public void clearOperations() {
        if (tableModel.getRowCount() == 0) {
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente remover todas as operações?",
                "Limpar operações",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            tableModel.clearOperations();
            onOperationsChanged.run();
        }
    }

    public List<Operation> getOperations() {
        return tableModel.getOperations();
    }
}