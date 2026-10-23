# 🏦 Java Bank System v2.0

Um sistema de terminal interativo que simula as operações de um banco real, desenvolvido como parte dos meus estudos iniciais na linguagem **Java**.

## 🚀 Funcionalidades

- **Criação de Conta:** Gera automaticamente um número de conta único de 4 dígitos para o cliente.
- **Sistema de Autenticação (Login):** Validação por número de conta e senha.
- **Bloqueio de Segurança:** A conta é bloqueada automaticamente se o usuário errar a senha 3 vezes seguidas.
- **Banco de Dados Fictício:** Utilização de `ArrayList` para manter os dados de múltiplos usuários ativos na memória durante a execução.
- **Operações Bancárias:** 
  - Consulta de Saldo e Extrato Detalhado.
  - Depósito.
  - Saque com validação de saldo.
  - Transferência entre contas diferentes (PIX) atualizando o histórico de ambas as partes.

## 🛠️ Tecnologias Utilizadas

- **Java 21** (Lógica de programação e POO)
- **Scanner** (Leitura de dados do terminal)
- **Random** (Geração de números aleatórios)
- **ArrayList** (Estrutura de dados para armazenamento em memória)

## 💻 Como Rodar o Projeto

Você pode rodar este projeto diretamente no seu navegador utilizando o compilador online [OnlineGDB](https://onlinegdb.com):
1. Copie o código do arquivo `Main.java`.
2. Cole no OnlineGDB.
3. Certifique-se de selecionar a linguagem **Java** no canto superior direito.
4. Clique em **Run** ou aperte `F9`.
