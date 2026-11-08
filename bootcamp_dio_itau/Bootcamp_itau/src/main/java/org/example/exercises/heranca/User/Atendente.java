package org.example.exercises.heranca.User;



public class Atendente extends User {
    private double valorEmCaixa;

    public Atendente(String nome, String email, String senha) {
        super(nome, email, senha, false);
        this.valorEmCaixa = 0.0;
    }

    public double getValorEmCaixa() {
        return valorEmCaixa;
    }

    public void setValorEmCaixa(double valorEmCaixa) {
        if (valorEmCaixa >= 0) {
            this.valorEmCaixa = valorEmCaixa;
        }
    }

    public void receberPagamentos(double valor) {
        if (valor > 0) {
            valorEmCaixa += valor;
            System.out.printf(
                    "Pagamento recebido: R$ %.2f%n", valor
            );
        } else {
            System.out.println("O valor do pagamento deve ser positivo.");
        }
    }

    public void fecharCaixa() {
        System.out.println("=== FECHAMENTO DE CAIXA ===");
        System.out.printf("Total em caixa: R$ %.2f%n", valorEmCaixa);

        valorEmCaixa = 0.0;

        System.out.println("Caixa fechado com sucesso.");
    }
}


