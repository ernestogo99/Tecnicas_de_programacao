package org.example.services;

import org.example.interfaces.IStatisticsService;
import org.example.models.Medicamento;
import org.example.models.Paciente;
import org.example.models.Receita;
import org.example.models.User;



public class StatisticsService implements IStatisticsService {
    private  final UserService userService;
    private final  ReceitaService receitaService;

    public StatisticsService(UserService userService, ReceitaService receitaService){
        this.userService=userService;
        this.receitaService=receitaService;
    }



    @Override
    public int totalPacientes() {
        int count=0;
        for(User user: userService.getAllUsers()){
            if(user instanceof Paciente){
                count++;
            }
        }
        return count;
    }

    @Override
    public int totalReceitas() {
        return this.receitaService.getReceitas().size();
    }

    @Override
    public int totalReceitasMedicamento(Medicamento m) {
        int count=0;
        for(Receita receita: receitaService.getReceitas()){
            if(receita.contemMedicamento(m)){
                count++;
            }
        }

        return  count;


    }
}
