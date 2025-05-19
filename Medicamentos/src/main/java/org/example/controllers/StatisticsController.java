package org.example.controllers;

import org.example.models.Medicamento;
import org.example.services.StatisticsService;

public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public void mostrarTotalPacientes() {
        int total = statisticsService.totalPacientes();
        if (total == 0) {
            System.err.println("Nenhum paciente cadastrado.");
        } else {
            System.out.println("Total de pacientes: " + total);
        }
    }

    public void mostrarTotalReceitas() {
        int total = statisticsService.totalReceitas();
        if (total == 0) {
            System.err.println("Nenhuma receita cadastrada.");
        } else {
            System.out.println("Total de receitas: " + total);
        }
    }

    public void mostrarTotalReceitasPorMedicamento(Medicamento m) {
        int total = statisticsService.totalReceitasMedicamento(m);
        if (total == 0) {
            System.err.println("O medicamento não está presente em nenhuma receita.");
        } else {
            System.out.println("O medicamento está presente em " + total + " receita(s).");
        }
    }
}
