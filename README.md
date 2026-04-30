# ⚽ Gerenciador de Times de Futebol

Um sistema de gerenciamento de times e jogadores desenvolvido em **Java**, utilizando conceitos de Programação Orientada a Objetos (POO), persistência de dados em arquivos de texto (`.txt`) e arquitetura em camadas (DAO e BO).

## 📋 Sobre o Projeto

Este projeto foi desenvolvido como um exercício prático para fixar conceitos fundamentais do Java. Ele permite ao usuário cadastrar times de futebol e adicionar seus respectivos jogadores (Goleiros e Atacantes), salvando as informações permanentemente no computador.

### ✨ Funcionalidades

- **Cadastro de Times:** Adiciona novos times com um identificador único e obrigatório de exatamente 10 caracteres.
- **Cadastro de Jogadores:** Permite adicionar jogadores aos times. Os jogadores são divididos em:
  - `Goleiro`: Armazena o número de defesas.
  - `Atacante`: Armazena o número de gols.
- **Consultas Otimizadas:** Busca as informações completas de um time específico a partir do seu Identificador usando `HashMap`.
- **Persistência de Dados:** Salva e carrega os dados automaticamente a partir de um arquivo de texto (`dados.txt`).

## 🏗️ Arquitetura do Sistema

O projeto foi organizado separando as responsabilidades (camadas):

- **Modelo (`Time`, `Jogador`, `Goleiro`, `Atacante`):** Classes que representam as entidades do sistema. Aplicação de herança e polimorfismo.
- **Business Object (`TimeBO`):** Contém a lógica de negócio e as validações (ex: validação do ID de 10 caracteres).
- **Data Access Object (`TimeDAO`):** Responsável por manipular a leitura e escrita no arquivo `.txt` e manter o cache dos dados na memória usando um `Map`.
- **Principal (`Principal`):** Interface de interação com o usuário via Console (Menu interativo).

## 🚀 Como Executar

1. Clone este repositório em sua máquina:
   ```bash
   git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
