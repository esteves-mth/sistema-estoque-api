# Sistema Estoque CRUD

Sistema simples de gerenciamento de estoque em Java, com CRUD completo usando MySQL.

## Descrição
Este projeto implementa um sistema de estoque básico em Java.  
Permite cadastrar, listar, buscar, atualizar e deletar produtos no banco de dados MySQL.  
Ideal para aprendizado de JDBC, SQL e boas práticas em Java.

## Funcionalidades
- Inserir produtos no estoque
- Listar todos os produtos
- Buscar produto por ID
- Atualizar dados do produto
- Deletar produto

## Tecnologias
- Java 17+
- JDBC
- MySQL
- Git / GitHub

## Instalação

1. Configure o banco de dados MySQL:
   - Crie o banco `estoque_db`
   - Crie a tabela `produtos`:

     ```sql
     CREATE TABLE produtos (
       id INT AUTO_INCREMENT PRIMARY KEY,
       nome VARCHAR(255) NOT NULL,
       preco DOUBLE NOT NULL,
       quantidade INT NOT NULL
     );
     ```

2. Atualize `ConnectionFactory.java` com suas credenciais ou configure variáveis de ambiente.

## Uso

1. Compile o projeto:

   ```bash
   javac -d bin src/br/com/esteves/**/*.java
   ```

2. Execute a aplicação:

   ```bash
   java -cp bin br.com.esteves.App
    ```

3. A aplicação irá:
  - Inserir um produto de teste
  - Listar todos os produtos
  - Deletar um produto pelo ID (7 no exemplo)

## Contribuição
Pull requests são bem-vindos. Para alterações maiores, abra uma issue primeiro.

## Licença
MIT License
