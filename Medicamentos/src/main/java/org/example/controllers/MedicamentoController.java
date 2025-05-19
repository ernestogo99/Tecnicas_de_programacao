package org.example.controllers;

import org.example.models.Medicamento;
import org.example.services.MedicamentoService;

import java.util.List;
import java.util.Set;

public class MedicamentoController {
    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    public void adicionarMedicamento(Medicamento medicamento) {
        try {
            medicamentoService.addMedicamento(medicamento);
            System.out.println("Medicamento adicionado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar medicamento: " + e.getMessage());
        }
    }

    public Medicamento buscarMedicamento(String nome) {
        try {
            Medicamento medicamento = medicamentoService.searchMedicamento(nome);
            System.out.println("Medicamento encontrado: " + medicamento.getNome());
            return medicamento;
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return  null;
        }
    }

    public Set<Medicamento> listarMedicamentos() {
        try {
            Set<Medicamento> medicamentos = medicamentoService.getAll();
            System.out.println("Lista de medicamentos:");
            for (Medicamento m : medicamentos) {
                System.out.println("- " + m.getNome());
            }
            return medicamentos;
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public void removerMedicamento(String nome) {
        try {
            medicamentoService.deleteMedicamento(nome);
            System.out.println("Medicamento removido com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void editarMedicamento(String nome, Medicamento novoMedicamento) {
        try {
            Medicamento atualizado = medicamentoService.editarMedicamento(nome, novoMedicamento);
            System.out.println("Medicamento atualizado: " + atualizado.getNome());
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
