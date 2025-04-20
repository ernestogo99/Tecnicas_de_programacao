# 📱 Mini-WhatsApp

Um projeto simples de console em Java que simula o funcionamento básico de um aplicativo de mensagens estilo WhatsApp, utilizando arquitetura MVC e separação por camadas. Permite criar usuários, iniciar conversas, enviar, editar, apagar e encaminhar mensagens.

---

## 📁 Estrutura do Projeto

```
📦 mini-whatsapp/
├── 📁 controllers/         # Camada de controle que lida com regras de negócio e orquestra as ações
│   ├── ConversaController.java
│   └── UsuarioController.java
│
├── 📁 enums/              # Enumerações utilizadas no sistema
│   └── CheckVisualizacao.java
│
├── 📁 models/             # Camada de modelos contendo as entidades principais
│   ├── Conversa.java
│   ├── Mensagem.java
│   └── Usuario.java
│
├── 📁 services/           # Serviços com a lógica central de cada entidade
│   ├── ConversaService.java
│   └── UsuarioService.java
│
├── 📁 views/              # Interfaces de interação com o usuário
│   ├── AppView.java           # View principal que integra todas as outras
│   ├── ConversaView.java
│   └── UsuarioView.java
│
├── Main.java              # Classe principal que inicia o programa
```

---

## 🚀 Funcionalidades

- 📇 Criar, editar, listar e remover usuários
- 💬 Iniciar conversas entre dois usuários
- 📝 Enviar, editar e apagar mensagens
- 📤 Encaminhar mensagens para outros usuários
- 📚 Listar todas as mensagens e conversas do sistema

---

## 🛠️ Tecnologias

- Java 17+
- Programação orientada a objetos (POO)
- Arquitetura MVC (Model-View-Controller)
- Design modular com responsabilidade única

---

## ▶️ Como Executar

1. Clone este repositório:

```bash
git clone https://github.com/seu-usuario/mini-whatsapp.git
cd mini-whatsapp
```

2. Compile o projeto:

```bash
javac Main.java
```

3. Execute:

```bash
java Main
```

---
