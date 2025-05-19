package org.example.models;


import java.util.HashSet;
import java.util.Set;

public class Receita {
    private int id;
    private String nomeMedico;
    private String nomePaciente;
    private Set<Medicamento> medicamentos=new HashSet<Medicamento>();

    public Receita(String nomeMedico,String nomePaciente){
        this.nomeMedico=nomeMedico;
        this.nomePaciente=nomePaciente;
    }


  

    public void gerarCabecalho(){
        System.out.println("=== Receita Médica ===");
        System.out.println("Para :" + this.nomePaciente);
        System.out.println("Médico :" + this.nomeMedico);
    }








    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public Set<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(Set<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
