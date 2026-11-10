# Organizando o GitHub Copilot para desenvolvimento

Uma boa configuração do Copilot não é uma coleção grande de arquivos: é um conjunto pequeno de orientações claras, aplicado no escopo certo e validado com tarefas reais. No VS Code, os arquivos abaixo ajudam a compartilhar padrões de desenvolvimento com a equipe.

> **Importante:** o suporte depende do ambiente (ou *harness*) selecionado no chat. Os exemplos deste guia focam no Copilot no VS Code. Confira o suporte de cada arquivo no ambiente e na versão que sua equipe utiliza.

## Estrutura recomendada

```text
meu-projeto/
├── .github/
│   ├── copilot-instructions.md       # Regras gerais do repositório
│   ├── instructions/                 # Regras específicas por linguagem ou pasta
│   │   ├── backend.instructions.md
│   │   └── frontend.instructions.md
│   ├── agents/                       # Papéis especializados selecionáveis no chat
│   │   └── reviewer.agent.md
│   ├── prompts/                      # Tarefas reutilizáveis acionadas manualmente
│   │   └── add-tests.prompt.md
│   └── skills/                       # Capacidades/workflows carregados quando relevantes
│       └── run-tests/
│           └── SKILL.md
├── .vscode/
│   └── mcp.json                      # Integrações MCP do workspace, se necessárias
└── src/
```

Crie apenas os diretórios necessários. Para muitos projetos, começar por `.github/copilot-instructions.md` e uma ou duas instruções específicas já é suficiente.

## O que é cada arquivo?

| Recurso | Para que serve | Quando usar |
|---|---|---|
| `.github/copilot-instructions.md` | Instruções gerais compartilhadas automaticamente com o Copilot no repositório. | Convenções que valem para quase todo o projeto: arquitetura, comandos de teste e padrões de código. |
| `*.instructions.md` | Instruções adicionais delimitadas por arquivos ou pastas. Podem usar `applyTo` para definir o escopo. | Regras de uma linguagem, módulo ou tipo de arquivo, sem carregar todas as orientações em toda tarefa. |
| `*.agent.md` | Define um agente especializado, com papel, instruções, ferramentas permitidas e, opcionalmente, configuração adicional. | Atividades recorrentes com objetivos distintos, como revisão, investigação ou planejamento. |
| `*.prompt.md` | Salva uma solicitação reutilizável que a pessoa aciona quando precisa. | Tarefas repetitivas que devem começar somente por escolha explícita, como preparar testes ou revisar um PR. O suporte depende do harness; no VS Code, prompt files são destinados ao harness Local, não ao harness Copilot. |
| `SKILL.md` | Descreve uma capacidade ou workflow especializado, que pode incluir scripts, exemplos e outros recursos na mesma pasta. | Procedimentos que o agente deve reutilizar quando forem pertinentes, como executar testes ou publicar uma versão. |
| `.vscode/mcp.json` | Configura servidores MCP para disponibilizar ferramentas e dados externos ao agente. | Quando uma tarefa realmente precisa de ferramentas adicionais. Revise cuidadosamente os servidores e não armazene credenciais no arquivo. |
| `AGENTS.md` | Formato de instruções de projeto compartilhado por diferentes agentes compatíveis. | Quando o projeto precisa de instruções comuns além do Copilot. Para o Copilot, pode ser usado como alternativa ao arquivo principal de instruções; evite manter cópias divergentes. |

Esses recursos se complementam, mas não são intercambiáveis: instruções definem regras; agentes definem papéis; prompts guardam pedidos manuais; skills ensinam workflows reutilizáveis. Eles orientam o modelo, mas não substituem testes, revisão humana ou controles de segurança.

## 1. Instruções gerais do repositório

Crie `.github/copilot-instructions.md` para registrar poucas regras estáveis e úteis em quase toda alteração. Por exemplo:

```markdown
# Instruções do projeto

- Siga a arquitetura e os padrões já existentes antes de introduzir novas abstrações.
- Mantenha regras de negócio fora da camada HTTP.
- Ao alterar comportamento, atualize ou adicione testes.
- Execute os testes relevantes e informe claramente o resultado.
- Não inclua segredos, tokens ou dados pessoais em código, logs ou exemplos.
```

Prefira instruções objetivas, verificáveis e específicas ao projeto. Não coloque neste arquivo uma descrição extensa de cada módulo ou instruções que só se aplicam a um pequeno conjunto de arquivos.

## 2. Instruções por linguagem ou área

Use arquivos `.instructions.md` quando uma regra só fizer sentido em parte do repositório. O campo `applyTo` é um padrão glob que indica os arquivos correspondentes:

```markdown
---
name: Testes do backend
description: Convenções para criar ou alterar testes do backend.
applyTo: "backend/**/*.py"
---

- Reutilize os fixtures existentes.
- Prefira testar comportamento observável, não detalhes internos.
- Execute `python -m pytest` para os testes afetados.
```

Exemplos de escopos úteis:

- `frontend/**/*.tsx` para componentes React;
- `backend/**/*.py` para código Python;
- `**/*test*.ts` para testes TypeScript.

Não confie apenas no nome do arquivo: escolha um `applyTo` que corresponda aos caminhos reais do projeto e teste se a instrução é descoberta.

## 3. Agentes especializados

Um agente personalizado é um papel selecionável no chat. Seu arquivo Markdown costuma ter cabeçalho YAML com nome, descrição e ferramentas, seguido das orientações:

```markdown
---
name: Reviewer
description: Revisa alterações e aponta problemas acionáveis sem editar arquivos.
tools: ['read', 'search']
---

Analise as alterações e leia o código relacionado quando necessário.
Priorize erros de comportamento, tratamento de falhas e testes ausentes.
Para cada achado, informe arquivo, linha, impacto e sugestão.
Não altere arquivos. Se não encontrar problemas, informe o que foi verificado.
```

Salve como `.github/agents/reviewer.agent.md`. Crie um agente quando um papel recorrente realmente precisar de critérios, ferramentas ou formato de resposta próprios. Não crie um agente para cada linguagem nem replique as regras gerais do projeto em todos eles.

## 4. Prompts reutilizáveis

Um prompt file é um roteiro que a pessoa chama manualmente para uma tarefa recorrente; ele não deve ser confundido com instruções aplicadas automaticamente. Um exemplo de conteúdo para `.github/prompts/add-tests.prompt.md`:

```markdown
---
name: add-tests
description: Adiciona testes para uma alteração de comportamento.
---

Identifique os testes existentes relacionados à alteração.
Adicione casos para o comportamento esperado e seus principais erros.
Execute os testes afetados e resuma os resultados.
Não altere código de produção, a menos que isso seja solicitado.
```

**Compatibilidade:** no VS Code, prompt files são voltados ao harness Local. Se a equipe usa o harness Copilot e precisa de workflows reutilizáveis nesse ambiente, considere uma skill, após verificar o suporte disponível na versão utilizada.

## 5. Skills e workflows

Uma skill agrupa um `SKILL.md` com instruções, e pode incluir scripts, modelos e exemplos. Em projetos Copilot no VS Code, uma localização suportada é `.github/skills/`. Exemplo de `.github/skills/run-tests/SKILL.md`:

```markdown
---
name: run-tests
description: Identifica e executa os testes relevantes para uma mudança de código.
---

1. Identifique a área alterada e os testes que a cobrem.
2. Use os comandos de teste documentados pelo projeto.
3. Não omita falhas: informe os comandos executados e seus resultados.
```

Use uma skill para um procedimento especializado que possa ser reutilizado; para uma regra curta e sempre válida, use instruções. Revise scripts e recursos de skills antes de adicioná-los ou executá-los.

## 6. MCP (opcional)

MCP permite conectar o agente a ferramentas ou fontes de dados adicionais. Não é necessário para começar: adicione uma configuração em `.vscode/mcp.json` somente quando houver uma necessidade concreta, e confira quais ferramentas o servidor disponibiliza. Não coloque tokens, senhas ou outros segredos em arquivos versionados; use o mecanismo seguro indicado pela ferramenta ou pelo ambiente.

## Boas práticas

1. **Comece pequeno:** escreva as instruções gerais mais importantes e amplie a configuração a partir de problemas recorrentes.
2. **Escolha o escopo correto:** regras globais em `copilot-instructions.md`; regras locais em `*.instructions.md`; processos especializados em skills ou agentes.
3. **Evite contradições e duplicação:** mantenha uma única fonte para cada regra e não faça instruções globais enormes.
4. **Prefira instruções acionáveis:** indique caminhos, comandos, limites e critérios de conclusão, em vez de frases vagas como “siga boas práticas”.
5. **Trate o conteúdo como orientação, não como controle de acesso:** instruções não garantem obediência nem substituem permissões, validações ou revisão.
6. **Teste a descoberta:** abra o editor de personalizações do VS Code, confirme que o arquivo aparece e experimente uma tarefa pequena em um chat novo, sem repetir as regras no pedido.
7. **Mantenha os arquivos versionados:** revise mudanças nas personalizações como qualquer outro código e valide comandos antes de recomendá-los.

## Referências oficiais

- [Personalizar instruções no VS Code](https://code.visualstudio.com/docs/agent-customization/custom-instructions)
- [Referência de arquivos de instruções](https://code.visualstudio.com/docs/agents/reference/custom-instructions-reference)
- [Agentes personalizados no VS Code](https://code.visualstudio.com/docs/agent-customization/custom-agents)
- [Prompt files no VS Code](https://code.visualstudio.com/docs/agent-customization/prompt-files)
- [Agent Skills no VS Code](https://code.visualstudio.com/docs/agent-customization/agent-skills)