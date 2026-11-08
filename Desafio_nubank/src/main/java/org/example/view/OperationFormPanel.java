package org.example.view;


import org.example.enums.OperationType;
import org.example.model.Operation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.util.function.Consumer;

public class OperationFormPanel extends JPanel {

    private final JComboBox<OperationType> operationTypeComboBox;
    private final JTextField unitCostField;
    private final JTextField quantityField;

    private final Consumer<Operation> onAddOperation;

    public OperationFormPanel(Consumer<Operation> onAddOperation) {
        this.onAddOperation = onAddOperation;

        this.operationTypeComboBox = new JComboBox<>(OperationType.values());
        this.unitCostField = new JTextField(12);
        this.quantityField = new JTextField(12);

        configurePanel();
        initializeComponents();
    }

    private void configurePanel() {
        setLayout(new BorderLayout(10, 15));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                        "Cadastro de operações",
                        TitledBorder.LEFT,
                        TitledBorder.TOP
                ),
                new EmptyBorder(15, 15, 15, 15)
        ));
    }

    private void initializeComponents() {
        JPanel fieldsPanel = new JPanel(new GridLayout(2, 3, 12, 6));

        fieldsPanel.add(new JLabel("Tipo de operação"));
        fieldsPanel.add(new JLabel("Preço unitário"));
        fieldsPanel.add(new JLabel("Quantidade"));

        fieldsPanel.add(operationTypeComboBox);
        fieldsPanel.add(unitCostField);
        fieldsPanel.add(quantityField);

        JButton addButton = new JButton("Adicionar operação");
        addButton.addActionListener(event -> addOperation());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);

        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addOperation() {
        try {
            OperationType type = (OperationType) operationTypeComboBox.getSelectedItem();

            BigDecimal unitCost = parseUnitCost();
            int quantity = parseQuantity();

            Operation operation = new Operation(
                    type,
                    unitCost,
                    quantity
            );

            onAddOperation.accept(operation);

            clearFields();

        } catch (NumberFormatException exception) {
            showError("Informe um preço e uma quantidade válidos.");
        } catch (IllegalArgumentException exception) {
            showError(exception.getMessage());
        }
    }

    private BigDecimal parseUnitCost() {
        String value = unitCostField.getText().trim();

        if (value.isEmpty()) {
            throw new IllegalArgumentException(
                    "O preço unitário deve ser informado."
            );
        }

        BigDecimal unitCost = new BigDecimal(value.replace(",", "."));

        if (unitCost.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O preço unitário deve ser maior que zero."
            );
        }

        return unitCost;
    }

    private int parseQuantity() {
        String value = quantityField.getText().trim();

        if (value.isEmpty()) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser informada."
            );
        }

        int quantity = Integer.parseInt(value);

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero."
            );
        }

        return quantity;
    }

    private void clearFields() {
        unitCostField.setText("");
        quantityField.setText("");
        unitCostField.requestFocusInWindow();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Dados inválidos",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
