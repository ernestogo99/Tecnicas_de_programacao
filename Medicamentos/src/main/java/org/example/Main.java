package org.example;

import org.example.controllers.StatisticsController;
import org.example.models.*;
import org.example.services.ReceitaService;
import org.example.services.MedicamentoService;
import org.example.services.StatisticsService;
import org.example.services.UserService;

public class Main {
    public static void main(String[] args) {
        MedicamentoService medicamentoService = new MedicamentoService();
        ReceitaService receitaService = new ReceitaService(medicamentoService);
        UserService userService=new UserService();
        StatisticsService statisticsService=new StatisticsService(userService,receitaService);
        StatisticsController statisticsController=new StatisticsController(statisticsService);
        Medico medico=new Medico("Dr. joão");
        Paciente paciente=new Paciente("Maria da Silva");

        userService.addUser(medico);
        userService.addUser(paciente);

       for(User user: userService.getAllUsers()){
           System.out.println(user.getNome());
       }



        Comprimido comp1 = new Comprimido("Paracetamol", 500, 3, "mg",2);
        Xarope xarope1 = new Xarope("Melagrião", 10, 2, "ml",3);


        medicamentoService.addMedicamento(comp1);
        medicamentoService.addMedicamento(xarope1);


        Receita receita = new Receita(medico.getNome(), paciente.getNome());
        Receita receita2 = new Receita(medico.getNome(), paciente.getNome());
        Receita receita3 = new Receita(medico.getNome(), paciente.getNome());

        receitaService.addReceita(receita);
        receitaService.addReceita(receita2);
        receitaService.addReceita(receita3);
        System.out.println(receita.getId());
        System.out.println(receita2.getId());
        System.out.println(receita3.getId());


        receitaService.addMedicamento(receita.getId(), comp1.getNome());
        receitaService.addMedicamento(receita.getId(), xarope1.getNome());


        receitaService.gerarImpresssao(receita.getId());

        statisticsController.mostrarTotalPacientes();
        statisticsController.mostrarTotalReceitasPorMedicamento(comp1);
        statisticsController.mostrarTotalReceitas();
    }
}
