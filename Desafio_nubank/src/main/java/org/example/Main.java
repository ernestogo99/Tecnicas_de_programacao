package org.example;


import org.example.controller.SimulationController;
import org.example.interfaces.CapitalGainsCalculator;
import org.example.service.CapitalGainsService;
import org.example.service.PortFolioService;
import org.example.service.TaxService;
import org.example.view.MainFrame;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PortFolioService portfolioService = new PortFolioService();
            TaxService taxService = new TaxService();

            CapitalGainsCalculator calculator =
                    new CapitalGainsService(
                            portfolioService,
                            taxService
                    );

            SimulationController controller =
                    new SimulationController(calculator);

            MainFrame mainFrame = new MainFrame(controller);
            mainFrame.setVisible(true);
        });
    }
}