package org.example.services;

import org.example.models.Medicamento;
import org.example.models.Receita;

import java.util.ArrayList;
import java.util.List;

public class ReceitaService {
    private final MedicamentoService medicamentoService;
    private List<Receita> receitas=new ArrayList<Receita>();
    private int nextId=1;


    public ReceitaService(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }


    public void addReceita(Receita receita){
        if(receita==null){
            throw new IllegalArgumentException("Os dados não pode ser nulos");
        }
        receita.setId(nextId++);
        receitas.add(receita);
    }

    public Receita getReceita(int id){
        for(Receita receita:receitas){
            if(receita.getId()==id){
                return receita;
            }
        }
        throw  new RuntimeException("Receita não encontrada");
    }

    public void deleteReceita(int id){
        Receita receita=getReceita(id);
        receitas.remove(receita);
    }

    public List<Receita> getReceitas() {
        if(receitas.isEmpty()){
            throw  new RuntimeException("Nenhuma receita cadastrada");
        }
        return receitas;
    }

    public void addMedicamento(int id, String nomeMedicamento){
        Medicamento medicamento= medicamentoService.searchMedicamento(nomeMedicamento);
        Receita receita=this.getReceita(id);
        receita.getMedicamentos().add(medicamento);
    }

    public void removeMedicamento(int id,String nomeMedicamento){
        Medicamento medicamento= medicamentoService.searchMedicamento(nomeMedicamento);
        Receita receita=this.getReceita(id);
        receita.getMedicamentos().remove(medicamento);
    }

    public void gerarImpresssao(int id) {
        Receita receita = getReceita(id);


        receita.gerarCabecalho();
        System.out.println("\nMedicamentos:");

        for (Medicamento medicamento : receita.getMedicamentos()) {
            System.out.println("- " + medicamento.gerarLinhaReceita());
        }

        System.out.println("======================");
    }

}
