package org.example.exercises.heranca.User;



public class Vendedor extends User {
    private int quantidadeVendas;

    public Vendedor(String nome, String email, String senha) {
        super(nome, email, senha, false);
        this.quantidadeVendas = 0;
    }

    public int getQuantidadeVendas() {
        return quantidadeVendas;
    }

    public void setQuantidadeVendas(int quantidadeVendas) {
        if (quantidadeVendas >= 0) {
            this.quantidadeVendas = quantidadeVendas;
        }
    }

    public void realizarVenda() {
        quantidadeVendas++;
        System.out.println("Venda realizada com sucesso.");
    }

    public void consultarVendas() {
        System.out.println("Quantidade de vendas: " + quantidadeVendas);
    }
}


