# k1-t3-java

Este projeto é uma aplicação de gerenciamento de tarefas em Java, executada via terminal. Permite adicionar, excluir, modificar e visualizar tarefas com atributos como nome, descrição, prioridade, categoria, status e data.

## Funcionalidades

- Adicionar novas tarefas com nome, descrição, prioridade, categoria, status e data.
- Excluir tarefas pelo ID.
- Modificar atributos de tarefas existentes.
- Visualizar tarefas em diferentes modos de exibição (padrão, por categoria, prioridade ou status).
- Validação de entrada para prioridade, status e data.

## Estrutura do Projeto
src/ Main.java // Lógica principal e interface de usuário via terminal Tarefa.java // Classe que representa uma tarefa
## Como Executar

1. Certifique-se de ter o Java instalado (JDK 8+).
2. Compile os arquivos:
   ```sh
   javac src/*.java

   java -cp src Main