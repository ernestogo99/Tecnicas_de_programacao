# Projeto PetShop

Escreva um código onde temos o controle de banho de um petshop, a maquina de banhos dos pets deve ter as seguintes operações:
Dar banho no pet;
Abastecer com água;
Abastecer com shampoo;
verificar nivel de água;
verificar nivel de shampoo;
verificar se tem pet no banho;
colocar pet na maquina;
retirar pet da máquina;
limpar maquina.
Siga as seguintes regras para implementação

A maquina de banho deve permitir somente 1 pet por vez;
Cada banho realizado irá consumir 10 litros de água e 2 litros de shampoo;
A máquina tem capacidade máxima de 30 litros de água e 10 litros de shampoo;
Se o pet for retirado da maquina sem estar limpo será necessário limpar a máquina para permitir a entrada de outro pet;
A limpeza da máquina ira consumir 3 litros de água e 1 litro de shampoo;
O abastecimento de água e shampoo deve permitir 2 litros por vez que for acionado;
# Estrutura de projeto

```
org.example/
├── controller/       ← Coordena a lógica entre view e model
├── model/            ← Entidades de domínio (Pet, PetMachine)
├── view/             ← Telas Swing, botões e componentes visuais
└── Main.java         ← Ponto de entrada da aplicação
```

## Destaques do projeto
- Modelagem correta do funcionamento de uma máquina de banho com controle de estoque e estado atual;
- Aplicação de princípios de orientação a objetos, como encapsulamento, organização por responsabilidades e clareza de fluxo;
- Arquitetura baseada em MVC, com separação clara entre modelo, visão e controle;
- Validação de regras críticas, como capacidade de estoque, quantidade por operação e restrições de uso da máquina;
- Interface gráfica em Swing para interagir com a máquina de banho de forma simples e intuitiva;
- Organização da lógica por métodos específicos, facilitando a manutenção e o entendimento do código;
- Controle do ciclo de vida do pet na máquina, incluindo limpeza, retirada e reuso da estrutura.

## Tecnologias utilizadas

- Java: linguagem principal utilizada no desenvolvimento do sistema;
- Swing: toolkit gráfico para construção da interface de usuário;
- Maven: ferramenta de gerenciamento de dependências e automação de builds.
