


# DesafioFinch

## Requisitos

### Banco de dados
É preciso ter um banco de dados  MySQL com uma base chamada **"lanchonete"**, os dados serão inseridos automaticamente. 

Ou executar o sql no arquivo **"scripts/script.sql"**. (necessário executar sempre que executar o programa) .

### Detalhes do banco
O banco deve estar configurado com as seguintes credênciais: usuário **"mysql"** e senha **"mysql"**.

Ou basta alterar o arquivo **"application.properties"** para inserir as credênciais de preferência.

### Lombok
O projeto usa o Lombok e já baixado pelo maven, mas é necessário executar e instalar em sua IDE de preferência.

## Instalação via Maven

Para instalar utilize na raiz do projeto:
```bash
 mvn clean install
```
e depois para executar:
```bash
 java -jar target/lanchonete-api-0.0.1-SNAPSHOT.jar
```

## Sobre o projeto

O programa não possui visualização gráfica, apenas APIs.

Utilize o Swagger para verificar as APIs:
```
http://localhost:8080/swagger-ui.html
```

