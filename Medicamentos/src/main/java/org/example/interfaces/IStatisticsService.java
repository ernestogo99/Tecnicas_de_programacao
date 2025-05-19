package org.example.interfaces;

import org.example.models.Medicamento;

public interface IStatisticsService {
    int totalPacientes();
    int totalReceitas();
    int totalReceitasMedicamento(Medicamento m);
}
