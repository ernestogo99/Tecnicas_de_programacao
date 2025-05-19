package org.example;

import org.example.models.*;
import org.example.services.ReceitaService;
import org.example.services.MedicamentoService;
import org.example.services.UserService;

public class Main {
    public static void main(String[] args) {
        MedicamentoService medicamentoService = new MedicamentoService();
        ReceitaService receitaService = new ReceitaService(medicamentoService);
        UserService userService=new UserService();
        Medico medico=new Medico("Dr. joão");
        Paciente paciente=new Paciente("Maria da Silva");

        userService.addUser(medico);
        userService.addUser(paciente);

       for(User user: userService.getAllUsers()){
           System.out.println(user.getNome());
       }



        Comprimido comp1 = new Comprimido("Paracetamol", 500, 3, "mg");
        Xarope xarope1 = new Xarope("Melagrião", 10, 2, "ml");


        medicamentoService.addMedicamento(comp1);
        medicamentoService.addMedicamento(xarope1);


        Receita receita = new Receita(medico.getNome(), paciente.getNome());
        receitaService.addReceita(receita);


        receitaService.addMedicamento(receita.getId(), comp1.getNome());
        receitaService.addMedicamento(receita.getId(), xarope1.getNome());


        receitaService.gerarImpresssao(receita.getId());
    }
}
