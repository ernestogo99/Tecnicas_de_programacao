
# ADR-001 — Adoção de interface gráfica com Java Swing

**Status:** Aceita

## Contexto

O desafio original solicita uma aplicação CLI, na qual as operações são recebidas em formato JSON por meio da entrada padrão (`stdin`) e os resultados são retornados por meio da saída padrão (`stdout`).

Entretanto, o projeto adotará uma interface gráfica desktop, permitindo que o usuário informe operações e visualize os resultados por meio de componentes visuais.

É necessário escolher uma tecnologia que permita desenvolver essa interface sem adicionar frameworks ou infraestrutura desnecessários.

## Decisão

Utilizar **Java Swing** para construir a interface gráfica da aplicação.

A interface será responsável exclusivamente pela interação com o usuário e pela apresentação dos resultados.

O processamento das operações e os cálculos financeiros permanecerão independentes da interface.

## Alternativas consideradas

| Alternativa | Justificativa para não adoção |
|---|---|
| CLI | É a proposta original do desafio, mas não atende à adaptação desejada para o projeto. |
| JavaFX | Oferece recursos visuais modernos, mas adiciona uma dependência e uma configuração desnecessárias para o escopo atual. |
| Swing | Escolhido por ser integrado ao JDK, não exigir dependência externa e atender às necessidades da aplicação. |

## Consequências positivas

- Interface desktop sem dependências adicionais de interface gráfica.
- Menor complexidade de configuração.
- Separação entre apresentação e regras de negócio.
- Possibilidade de adicionar outros meios de interação futuramente.

## Consequências negativas

- A aplicação não atenderá diretamente ao formato de execução CLI solicitado originalmente.
- A aparência visual do Swing pode exigir trabalho adicional de estilização.
- A interface gráfica não será adequada para uma avaliação automatizada que espere exclusivamente comunicação por `stdin` e `stdout`.

## Observação arquitetural

A lógica de negócio não deverá depender de componentes Swing.

Dessa maneira, uma futura implementação de CLI poderá reutilizar os mesmos serviços de cálculo, sem duplicar regras de negócio.

---

