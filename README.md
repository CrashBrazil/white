# white - Sistemas de Gestão de Vendas Automotivas!

## Descrição do Projeto

Sistema backend para gestão de vendas automotivas desenvolvido com Spring Boot. O sistema gerencia **Cliente** e **Vendedores**, fornecendo uma API REST completa para operações CRUD.

## Tecnologia utilizadas

- **Java 25**
- **Spring Boot 3.4.5**
- **Spring Data JPA** - Persistência de dados
- **Spring Security** - Autenticação e autorização
- **Spring Web** - API REST
- **MySQL** - Banco de dados relacional
- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciamento de dependências
- **Docker** - Empacotador de software

## Funcionalidades implementadas

### Vendedores (CRUD Completo)

-  Criar novo vendedor
-  Listar todos os vendedores
-  Buscar vendedor por ID
-  Atualizar dados do vendedor
-  Deletar vendedor

### Cliente (CRUD Completo)

-  Criar novo cliente
-  Listar todos os clientes
-  Buscar cliente por ID
-  Atualizar dados do cliente
-  Deletar cliente

### Exemplos:

**GET** - "http://localhost:8080/clientes"

JSON - {
"nomeCompletoCliente": "Teste",
"emailCliente": "teste@email.com",
"telefoneCliente": "82988888888",
"sexoCliente": "MASCULINO",
"dataNascimentoCliente": "2004-12-31",
"tipoMoradia": "Apartamento",
"cidade": "Maceió",
"endereco": "Rua Nova",
"cep": "57000000",
"idVendedor": "ac48e7ed-1c74-43c6-9eb2-09a61a78aea4"
}

**POST** - "http://localhost:8080/clientes"

JSON - {
"nomeCompletoCliente": "Teste",
"emailCliente": "teste@email.com",
"telefoneCliente": "82988888888",
"sexoCliente": "MASCULINO",
"dataNascimentoCliente": "2004-12-31",
"tipoMoradia": "Apartamento",
"cidade": "Maceió",
"endereco": "Rua Nova",
"cep": "57000000",
"idVendedor": "ac48e7ed-1c74-43c6-9eb2-09a61a78aea4"
}

### Próximas implementações

-  Gestão de Veículos
-  Gestão de Vendas
-  Gestão de Comissões
-  Autenticação AUTH0

### Pré-requisitos

- JDK 21 ou superior
- MySQL Server
- Maven
- Insomnia/Postman(para testes)
- Obs: possivel rodar com o docker puro ou usando um orquestrador 

### Clone o repositório

```bash
git clone [https://github.com/CrashBrazil/white]
cd white

**Equipe:
Pedro Henrique Alves Lessa - 01638620
Felipe de Lima Passos - 01647492
Yuri Cordeiro Mascarenhas - 01327348
Giorgio Rafael Ataide Guimarães - 01620989
Matheus Venâncio da Silva - 01628426**