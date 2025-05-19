package org.example.controllers;

import org.example.models.Medicamento;
import org.example.models.Receita;
import org.example.services.ReceitaService;

import java.util.List;

public class ReceitaController {
    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    public void adicionarReceita(Receita receita) {
        try {
            receitaService.addReceita(receita);
            System.out.println("Receita adicionada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar receita: " + e.getMessage());
        }
    }

    public void buscarReceita(int id) {
        try {
            Receita receita = receitaService.getReceita(id);
            System.out.println("Receita encontrada: ID " + receita.getId());
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarReceitas() {
        try {
            List<Receita> receitas = receitaService.getReceitas();
            System.out.println("Lista de receitas:");
            for (Receita r : receitas) {
                System.out.println("- Receita ID: " + r.getId());
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void removerReceita(int id) {
        try {
            receitaService.deleteReceita(id);
            System.out.println("Receita removida com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void adicionarMedicamentoNaReceita(int id, String nomeMedicamento) {
        try {
            receitaService.addMedicamento(id, nomeMedicamento);
            System.out.println("Medicamento adicionado à receita com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void removerMedicamentoDaReceita(int id, String nomeMedicamento) {
        try {
            receitaService.removeMedicamento(id, nomeMedicamento);
            System.out.println("Medicamento removido da receita com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void imprimirReceita(int id) {
        try {
            receitaService.gerarImpresssao(id);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
