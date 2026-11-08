package org.example.view;



import org.example.model.OperationResult;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ResultsTableModel extends AbstractTableModel {

    private final String[] columns = {
            "Nº",
            "Operação",
            "Preço unitário",
            "Quantidade",
            "Resultado financeiro",
            "Classificação",
            "Imposto"
    };

    private List<OperationResult> results = new ArrayList<>();

    @Override
    public int getRowCount() {
        return results.size();
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
        OperationResult result = results.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> result.operationNumber();
            case 1 -> result.operation().type().getDescription();
            case 2 -> result.operation().unitCost();
            case 3 -> result.operation().quantity();
            case 4 -> result.financialResult();
            case 5 -> result.resultType().getDescription();
            case 6 -> result.tax();
            default -> throw new IndexOutOfBoundsException(
                    "Coluna inválida: " + columnIndex
            );
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 3 -> Integer.class;
            case 2, 4, 6 -> java.math.BigDecimal.class;
            default -> String.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setResults(List<OperationResult> results) {
        this.results = new ArrayList<>(results);
        fireTableDataChanged();
    }

    public void clearResults() {
        if (results.isEmpty()) {
            return;
        }

        results.clear();
        fireTableDataChanged();
    }

    public List<OperationResult> getResults() {
        return List.copyOf(results);
    }
}
