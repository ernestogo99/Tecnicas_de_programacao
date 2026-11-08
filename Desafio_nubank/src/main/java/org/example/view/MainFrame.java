package org.example.view;


import org.example.controller.SimulationController;
import org.example.exceptions.CapitalGainsException;
import org.example.model.Operation;
import org.example.model.SimulationResult;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class MainFrame extends JFrame {
    private static final Logger LOGGER =
            Logger.getLogger(MainFrame.class.getName());
    private final OperationsTablePanel operationsTablePanel;
    private final ResultsPanel resultsPanel;
    private final FinancialSummaryPanel financialSummaryPanel;
    private final SimulationController controller;

    private static final int WINDOW_WIDTH = 1100;
    private static final int WINDOW_HEIGHT = 750;

    public MainFrame(SimulationController controller) {
        this.controller = Objects.requireNonNull(controller);

        this.resultsPanel = new ResultsPanel();
        this.financialSummaryPanel = new FinancialSummaryPanel();

        this.operationsTablePanel = new OperationsTablePanel(
                this::clearSimulationResults
        );

        configureWindow();
        initializeComponents();
    }

    private JPanel createSimulationActions() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton simulateButton = new JButton("Calcular simulação");
        simulateButton.addActionListener(event -> simulate());

        panel.add(simulateButton);

        return panel;
    }

    private void simulate() {
        try {
            List<Operation> operations = operationsTablePanel.getOperations();

            SimulationResult result = controller.simulate(operations);

            resultsPanel.updateResults(result);
            financialSummaryPanel.updateSummary(result.financialSummary());

        } catch (CapitalGainsException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Não foi possível realizar a simulação",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (RuntimeException exception) {
            LOGGER.log(Level.SEVERE, "Erro inesperado durante a simulação.", exception);

            JOptionPane.showMessageDialog(
                    this,
                    "Ocorreu um erro inesperado ao realizar a simulação.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }



    private void clearSimulationResults() {
        resultsPanel.clearResults();
        financialSummaryPanel.clearSummary();
    }
    private void configureWindow() {
        setTitle("Capital Gains Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setMinimumSize(new Dimension(900, 650));
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
    }

    private void initializeComponents() {
        add(createHeader(), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(createContent());
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(20, 24, 20, 24));

        JLabel title = new JLabel("Capital Gains Simulator");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));

        JLabel subtitle = new JLabel(
                "Gerenciamento e simulação de ganhos de capital"
        );
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(title);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(subtitle);

        header.add(textPanel, BorderLayout.WEST);

        return header;
    }

    private JPanel createContent() {
        JPanel content = new JPanel();

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(10, 24, 10, 24));

        OperationFormPanel formPanel = new OperationFormPanel(
                operationsTablePanel::addOperation
        );

        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        operationsTablePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        financialSummaryPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel simulationActions = createSimulationActions();
        simulationActions.setAlignmentX(Component.LEFT_ALIGNMENT);

        content.add(formPanel);
        content.add(Box.createVerticalStrut(15));

        content.add(operationsTablePanel);
        content.add(Box.createVerticalStrut(10));

        content.add(simulationActions);
        content.add(Box.createVerticalStrut(15));

        content.add(resultsPanel);
        content.add(Box.createVerticalStrut(15));

        content.add(financialSummaryPanel);

        return content;
    }

    private JPanel createPlaceholder(String title, String description) {
        JPanel panel = new JPanel(new BorderLayout(5, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(descriptionLabel, BorderLayout.CENTER);

        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        return panel;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBorder(new EmptyBorder(10, 24, 15, 24));

        JLabel version = new JLabel("Capital Gains Simulator");
        version.setFont(new Font("SansSerif", Font.PLAIN, 11));

        footer.add(version);

        return footer;
    }
}