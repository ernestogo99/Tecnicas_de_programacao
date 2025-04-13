package Controllers;
import Models.Pessoa;
import Services.PessoaService;

import java.util.List;

public class PessoaController {
    private final PessoaService service=new PessoaService();

    public Pessoa create(Pessoa pessoa){
        try{
            return service.create(pessoa);
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
            return null;

        }
    }

    public Pessoa update(int id, Pessoa pessoa){
        try{
            return service.update(id, pessoa);
        }catch (RuntimeException e ){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Pessoa getById(int id){
        try{
            return service.getById(id);
        }catch (RuntimeException e ){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public boolean delete(int id){
        try{
            return service.delete(id);
        }catch (RuntimeException e ){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public List<Pessoa> getAll(){
        try{
            return service.getPessoas();
        }catch (RuntimeException e ){
            System.err.println(e.getMessage());
            return null;
        }
    }

}
