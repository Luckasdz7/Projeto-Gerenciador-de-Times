# ⚽ Gerenciador de Times

Um sistema de gerenciamento de times e jogadores desenvolvido em Java. Esta aplicação em modo console permite administrar equipes esportivas, controlando o cadastro de times e especificando as posições de seus respectivos jogadores (Atacantes e Goleiros). 

O projeto foi construído aplicando princípios de **Programação Orientada a Objetos (POO)**, como herança e polimorfismo, e utiliza o padrão de arquitetura dividindo responsabilidades entre **DAO** (Data Access Object) e **BO** (Business Object), além de realizar a persistência de dados em arquivos de texto (`.txt`).

## ✨ Funcionalidades (CRUD Completo)

O sistema possui um menu interativo com as seguintes operações:

* **Cadastrar Time (Create):** Cria novos times com um identificador único (exatamente 10 caracteres) e adiciona jogadores (Goleiros com número de defesas ou Atacantes com número de gols).
* **Consultar Time (Read):** Busca um time pelo seu identificador único e exibe todos os seus dados e a lista detalhada de jogadores.
* **Editar Time (Update):** Permite buscar um time existente e atualizar o seu nome, refletindo a mudança imediatamente no banco de dados local.
* **Excluir Time (Delete):** Remove um time e todos os seus jogadores cadastrados do sistema, apagando os registros da memória e do arquivo de persistência.

## 🛠️ Tecnologias e Conceitos Utilizados

* **Linguagem:** Java
* **Persistência de Dados:** Leitura e Escrita em arquivos de texto (`java.io.BufferedReader`, `BufferedWriter`, `FileWriter`, `FileReader`).
* **Estruturas de Dados:** Uso de Coleções (`List`, `ArrayList`) e Mapas (`Map`, `HashMap`) para armazenamento e busca eficiente em memória.
* **Arquitetura:** Separação de camadas (Modelos, DAO para acesso a dados, BO para regras de negócio e classe Principal para a interface com o usuário).

## 📂 Estrutura do Projeto

O projeto está organizado no pacote `pacotes` com as seguintes classes:

* **Modelos:**
  * `Time.java`: Representa a entidade time e guarda uma lista de jogadores.
  * `Jogador.java`: Classe abstrata que serve de base para os atletas.
  * `Atacante.java` / `Goleiro.java`: Classes filhas de Jogador, demonstrando herança e características específicas.
* **Regras de Negócio e Dados:**
  * `TimeBO.java`: Valida as informações (como o tamanho do ID) e faz a ponte entre a interface e o banco de dados.
  * `TimeDAO.java`: Responsável por toda a manipulação do arquivo `dados.txt` (salvar, buscar, editar, excluir e recarregar dados).
* **Interface:**
  * `Principal.java`: Contém o método `main` e o menu interativo com o usuário utilizando `Scanner`.

## 🚀 Como Executar

1. Certifique-se de ter o [Java JDK](https://www.oracle.com/br/java/technologies/downloads/) instalado em sua máquina.
2. Clone este repositório:
   ```bash
   git clone [https://github.com/SEU_USUARIO/projeto-gerenciador-de-times.git](https://github.com/SEU_USUARIO/projeto-gerenciador-de-times.git)
