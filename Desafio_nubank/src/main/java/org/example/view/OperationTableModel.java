package org.example.view;


import org.example.model.Operation;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class OperationTableModel extends AbstractTableModel {

    private final String[] columns = {
            "Tipo de operação",
            "Preço unitário",
            "Quantidade"
    };

    private final List<Operation> operations = new ArrayList<>();

    @Override
    public int getRowCount() {
        return operations.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Operation operation = operations.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> operation.type().getDescription();
            case 1 -> operation.unitCost();
            case 2 -> operation.quantity();
            default -> throw new IndexOutOfBoundsException(
                    "Coluna inválida: " + columnIndex
            );
        };
    }

    public void addOperation(Operation operation) {
        int rowIndex = operations.size();

        operations.add(operation);
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    public void removeOperation(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= operations.size()) {
            return;
        }

        operations.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void clearOperations() {
        if (operations.isEmpty()) {
            return;
        }

        int lastRow = operations.size() - 1;

        operations.clear();
        fireTableRowsDeleted(0, lastRow);
    }

    public Operation getOperation(int rowIndex) {
        return operations.get(rowIndex);
    }

    public List<Operation> getOperations() {
        return List.copyOf(operations);
    }
}
