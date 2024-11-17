# Catalogo de Filmes API

Este é um projeto de uma API REST desenvolvida em Java com Spring Boot, destinada ao gerenciamento de um catálogo de filmes. A API permite realizar operações CRUD (Criar, Ler, Atualizar e Deletar) para filmes, atores, gêneros, idiomas, países, diretores e estudios.

## Funcionalidades

- **Filmes**: Cadastro, atualização, exclusão e consulta de filmes.
- **Atores**: Cadastro, atualização, exclusão e consulta de atores.
- **Gêneros**: Cadastro, atualização, exclusão e consulta de gêneros.
- **Idiomas**: Cadastro, atualização, exclusão e consulta de ediomas.
- **Países**: Cadastro, atualização, exclusão e consulta de países.
- **Diretores**: Cadastro, atualização, exclusão e consulta de diretores.
- **Estúdios**: Cadastro, atualização, exclusão e consulta de estúdios.

## Tecnologias Utilizadas

- **Java 17**
- **IntelliJ IDEA**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **Docker Compose**
- **Spring Security**
- **Lombok**
- **PostgreSQL**
- **ModelMapper**
- **Maven**
- **Jakarta Bean Validation**
- **OMDb API**
- **Spring Doc**

## Estrutura de Pacotes

- ![alt text](image-1.png)

## Endpoints da API

### Filmes

- `GET /api/v1/movies`: Pesquisar filmes.
- `POST /api/v1/movies`: Criar um novo filme.
- `GET /api/v1/movies/{id}`: Recuperar um filme pelo ID.
- `PUT /api/v1/movies/{id}`: Atualizar informações de um filme.
- `DELETE /api/v1/movies/{id}`: Deletar um filme.

### Atores

- `GET /api/v1/actors`: Pesquisar atores.
- `POST /api/v1/actors`: Criar um novo ator.
- `GET /api/v1/actors/{id}`: Recuperar um ator pelo ID.
- `PUT /api/v1/actors/{id}`: Atualizar informações de um ator.
- `DELETE /api/v1/actors/{id}`: Deletar um ator.

### Gêneros

- `GET /api/v1/genres`: Pesquisar gêneros.
- `POST /api/v1/genres`: Criar um novo gênero.
- `GET /api/v1/genres/{id}`: Recuperar um gênero pelo ID.
- `PUT /api/v1/genres/{id}`: Atualizar informações de um gênero.
- `DELETE /api/v1/genres/{id}`: Deletar um gênero.

### Idiomas

- `GET /api/v1/languages`: Pesquisar idiomas.
- `POST /api/v1/languages`: Criar um novo idioma.
- `GET /api/v1/languages/{id}`: Recuperar um idioma pelo ID.
- `PUT /api/v1/languages/{id}`: Atualizar informações de um idioma.
- `DELETE /api/v1/languages/{id}`: Deletar um idioma.

### Países

- `GET /api/v1/countries`: Pesquisar países.
- `POST /api/v1/countries`: Criar um novo país.
- `GET /api/v1/countries/{id}`: Recuperar um país pelo ID.
- `PUT /api/v1/countries/{id}`: Atualizar informações de um país.
- `DELETE /api/v1/countries/{id}`: Deletar um país.

### Diretores

- `GET /api/v1/directors`: Pesquisar diretores.
- `POST /api/v1/directors`: Criar um novo diretor.
- `GET /api/v1/directors/{id}`: Recuperar um diretor pelo ID.
- `PUT /api/v1/directors/{id}`: Atualizar informações de um diretor.
- `DELETE /api/v1/directors/{id}`: Deletar um diretor.

### Estúdios

- `GET /api/v1/studios`: Pesquisar estúdios.
- `POST /api/v1/studios`: Criar um novo estúdio.
- `GET /api/v1/studios/{id}`: Recuperar um estúdio pelo ID.
- `PUT /api/v1/studios/{id}`: Atualizar informações de um estúdio.
- `DELETE /api/v1/studios/{id}`: Deletar um estúdio.

## Requisitos

- **JDK 17 ou superior**
- **Maven**
- **Docker Compose**
- **Srpong Boot 3.3.4 ou superior**

## Como Executar

Siga os passos abaixo para iniciar o projeto:

```bash
# Clone este repositório
git clone https://github.com/tiagogarciaferreira/pos-architecture-java.git

# Navegue até a pasta do projeto
cd pos-architecture-java/tiago

# Certifique-se de ter o Docker e Docker Compose instalados e inicie os serviços auxiliares (como o banco de dados)
docker-compose up -d

# Execute a aplicação localmente usando Maven
./mvnw spring-boot:run

# Após iniciar, acesse a API no endereço:
# https://localhost:8443/api

# Acesse a documentação interativa da API (Swagger) no seguinte endereço:
# https://localhost:8443/api/swagger-ui.html

# Para encerrar os serviços auxiliares
docker-compose down