package org.example.services;

import org.example.models.Medicamento;


import java.util.HashSet;
import java.util.Set;

public class MedicamentoService {
    private final Set<Medicamento>  medicamentos= new HashSet<Medicamento>();


    public void addMedicamento(Medicamento medicamento){
        if(medicamento ==null){
            throw new IllegalArgumentException(" Os dados não podem ser nulos");
        }
        medicamentos.add(medicamento);

    }

    public Medicamento searchMedicamento(String nome){
        for(Medicamento medicamento:medicamentos){
            if(medicamento.getNome().equals(nome)){
                return  medicamento;
            }
        }
        throw  new RuntimeException("Medicamento não encontrado");

    }


    public void deleteMedicamento(String nome){
        Medicamento medicamento=searchMedicamento(nome);
        medicamentos.remove(medicamento);
    }

    public Medicamento editarMedicamento(String nome,Medicamento medicamento){
        Medicamento medEncontrado= searchMedicamento(nome);
        medEncontrado.setNome(medicamento.getNome());
        medEncontrado.setFrequencia(medicamento.getFrequencia());
        medEncontrado.setQuantidade(medicamento.getQuantidade());
        medEncontrado.setUnidadeDemedida(medicamento.getUnidadeDemedida());
        return  medEncontrado;
    }

    public Set<Medicamento> getAll(){
        if(this.medicamentos.isEmpty()){
            throw  new RuntimeException("Não há medicamentos");
        }
        return this.medicamentos;
    }


}
