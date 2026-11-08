package org.example.exercises.heranca.User;

public class Gerente extends User {

    public Gerente(String nome, String email, String senha) {
        super(nome, email, senha, true);
    }

    public void gerarRelatorioFinanceiro(double totalVendido) {
        System.out.println("=== RELATÓRIO FINANCEIRO ===");
        System.out.printf("Total vendido: R$ %.2f%n", totalVendido);
    }

    public void consultarVendas(Vendedor vendedor) {
        System.out.println("=== CONSULTA DE VENDAS ===");
        System.out.println("Vendedor: " + vendedor.getNome());
        System.out.println(
                "Quantidade de vendas: " + vendedor.getQuantidadeVendas()
        );
    }
}
