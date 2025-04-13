package Services;
import java.util.ArrayList;
import java.util.List;
import Models.Pessoa;

public class PessoaService {
    List<Pessoa> pessoas=new ArrayList<>();
    private int proximoId=1;

    public Pessoa create(Pessoa data) {
            if (data == null) {
                throw new IllegalArgumentException("Dados da pessoa não podem ser nulos.");

            }
            Pessoa novaPessoa = new Pessoa(
                    data.getNome(),
                    data.getIdade(),
                    data.getEmail(),
                    data.getTelefone()
            );
            novaPessoa.setId(proximoId++);

            pessoas.add(novaPessoa);
            return novaPessoa;
    }

    public Pessoa update(int id, Pessoa data) {
            Pessoa pessoa = getById(id);
            pessoa.setNome(data.getNome());
            pessoa.setEmail(data.getEmail());
            pessoa.setIdade(data.getIdade());
            pessoa.setTelefone(data.getTelefone());

            return pessoa;
    }

    public Pessoa getById(int id){
            for (Pessoa pessoa:pessoas){
                if(pessoa.getId()==id){
                    return pessoa;
                }
            }
        throw new RuntimeException("Pessoa com ID " + id + " não encontrada.");

    }

    public boolean delete(int id){
            Pessoa pessoa=getById(id);
            if(pessoa==null){
                return false;
            }
            pessoas.remove(pessoa);
            return true;
    }

    public List<Pessoa> getPessoas() {
        if(pessoas.isEmpty()){
            throw new RuntimeException("Nenhuma pessoa cadastrada.");
        }
        return pessoas;
    }
}
