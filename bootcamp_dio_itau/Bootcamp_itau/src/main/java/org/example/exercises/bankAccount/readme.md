# Exercício de programação 1: Conta de banco

Escreva um código onde temos uma conta bancaria que possa realizar as seguintes operações:
Consultar saldo
consultar cheque especial
Depositar dinheiro;
Sacar dinheiro;
Pagar um boleto.
Verificar se a conta está usando cheque especial.
Siga as seguintes regras para implementar

A conta bancária deve ter um limite de cheque especial somado ao saldo da conta;
O o valor do cheque especial é definido no momento da criação da conta, de acordo com o valor depositado na conta em sua criação;
Se o valor depositado na criação da conta for de R$500,00 ou menos o cheque especial deve ser de R$50,00
Para valores acima de R$500,00 o cheque especial deve ser de 50% do valor depositado;
Caso o limite de cheque especial seja usado, assim que possível a conta deve cobrar uma taxa de 20% do valor usado do cheque especial.
# Estrutura de projeto

```
org.example/
├── controller/       ← Coordena a lógica entre view e model
├── model/            ← Entidades de domínio (Conta, Transação, etc.)
├── view/             ← Telas Swing, listeners e componentes visuais
└── Main.java         ← Ponto de entrada da aplicação
```

## Destaques do projeto
- Gerenciamento completo da conta bancária: funcionalidades para consultar saldo, consultar cheque especial, depositar, sacar e pagar boletos.
- Aplicação de princípios de orientação a objetos: uso de encapsulamento, composição e organização por camadas.
- Arquitetura baseada em MVC: separação clara de responsabilidades entre controle, modelo e visualização.
- Definição de regras financeiras para operações permitidas e bloqueadas.
- Controle de limite do cheque especial e utilização do saldo negativo.
- Visualização dos dados financeiros em uma interface simples e funcional.
- Organização da lógica por métodos específicos e clareza no fluxo de execução.

## Tecnologias utilizadas

- Java: Linguagem principal utilizada no desenvolvimento do sistema.
- Swing: toolkit gráfico para construção da interface de usuário.
- Maven: ferramenta de gerenciamento de dependências e automação de builds.
