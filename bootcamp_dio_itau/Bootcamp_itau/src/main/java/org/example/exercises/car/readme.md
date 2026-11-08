# Exercicio 2: Carro

Escreva um código onde controlamos as funções de um carro, ele deve ter as seguintes funções:
Ligar o carro;
Desligar o carro;
Acelerar;
diminuir velocidade;
virar para esquerda/direita
verificar velocidade;
trocar a marcha
Siga as seguintes regras na implementação

Quando o carro for criado ele deve começar desligado, em ponto morto e com sua velocidade em 0
O carro desligado não pode realizar nenhuma função;
Quando o carro for acelerado ele deve incrementar 1km em sua velocidade (pode chegar no máximo a 120km);
Quando diminuir a velocidade do carro ele deve decrementar 1 km de sua velocidade (pode chegar no minimo a 0km);
o carro deve possuir 6 marchas, não deve ser permitido pular uma marcha no carro;
A velocidade do carro deve respeitar os seguintes limites para cada velocidade
se o carro estiver na marcha 0 (ponto morto) ele não pode acelerar
se estiver na 1ª marcha sua velocidade pode estar entre 0km e 20km
se estiver na 2ª marcha sua velocidade pode estar entre 21km e 40km
se estiver na 3ª marcha sua velocidade pode estar entre 41km e 60km
se estiver na 4ª marcha sua velocidade pode estar entre 61km e 80km
se estiver na 5ª marcha sua velocidade pode estar entre 81km e 100km
se estiver na 6ª marcha sua velocidade pode estar entre 101km e 120km
O carro podera ser desligado se estiver em ponto morto (marcha 0) e sua velocidade em 0 km
O carro só pode virar para esquerda/direita se sua velocidade for de no mínimi 1km e no máximo 40km;

# Estrutura de projeto

```
org.example/
├── controller/       ← Coordena a lógica entre view e model
├── model/            ← Entidade de domínio (Carro)
├── view/             ← Telas Swing, botões e componentes visuais
└── Main.java         ← Ponto de entrada da aplicação
```

## Destaques do projeto
- Modelagem correta do comportamento de um carro com estado e regras de negócio bem definidas;
- Aplicação de princípios de orientação a objetos, como encapsulamento e organização por responsabilidades;
- Arquitetura baseada em MVC, com separação clara entre modelo, visão e controle;
- Validação de regras críticas, como limite de velocidade por marcha e restrições de operação;
- Interface gráfica em Swing para interagir com o veículo de forma visual e intuitiva;
- Organização da lógica por métodos específicos, deixando a manutenção e a leitura do código mais simples;
- Controle de direção, aceleração e marcha de maneira segura e coerente com o comportamento real do veículo.

## Tecnologias utilizadas

- Java: linguagem principal utilizada no desenvolvimento do sistema;
- Swing: toolkit gráfico para construção da interface de usuário;
- Maven: ferramenta de gerenciamento de dependências e automação de builds.
