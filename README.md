# Catalogo de Filmes API

Este é um projeto de uma API REST desenvolvida em Java com Spring Boot, destinada ao gerenciamento de um catálogo de filmes. A API permite realizar operações CRUD (Criar, Ler, Atualizar e Deletar) para filmes, atores, gêneros, idiomas, países, diretores e estudios.

## Funcionalidades

### 📽️ **Filmes**
- Cadastre novos filmes.
- Atualize informações de filmes já existentes.
- Exclua registros de filmes desnecessários.
- Pesquise filmes por diferentes critérios.
- Busque filmes pelo ID.

### 🎭 **Atores**
- Cadastre atores no sistema.
- Atualize os dados dos atores registrados.
- Exclua atores que não são mais relevantes.
- Pesquise atores por diferentes critérios.
- Busque atores pelo ID.

### 🎥 **Gêneros**
- Cadastre novos gêneros cinematográficos.
- Atualize os gêneros existentes.
- Exclua gêneros que não são mais necessários.
- Pesquise gêneros disponíveis.
- Busque gêneros pelo ID.

### 🌐 **Idiomas**
- Cadastre idiomas disponíveis para filmes.
- Atualize informações sobre os idiomas registrados.
- Exclua idiomas que não são mais usados.
- Pesquise idiomas cadastrados.
- Busque idiomas pelo ID.

### 🗺️ **Países**
- Cadastre novos países ao sistema.
- Atualize as informações dos países cadastrados.
- Remova países que não são mais necessários.
- Pesquise países relacionados às produções.
- Busque países pelo ID.

### 🎬 **Diretores**
- Cadastre diretores de filmes.
- Atualize os dados dos diretores registrados.
- Exclua registros de diretores.
- Pesquise diretores cadastrados.
- Busque diretores pelo ID.

### 🏢 **Estúdios**
- Cadastre estúdios cinematográficos.
- Atualize as informações dos estúdios registrados.
- Exclua estúdios do sistema.
- Pesquise estúdios disponíveis.
- Busque estúdios pelo ID.

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
- **Postman**

## Importando Coleções Postman

As coleções Postman exportadas para este projeto estão localizadas na pasta `pos-architecture-java/tiago/postman`. Siga os passos abaixo para importar essas coleções no Postman:

### Passo 1: Localizando a Pasta de Coleções

As coleções exportadas do Postman estão armazenadas na pasta `pos-architecture-java/tiago/postman`. Dentro dessa pasta, você encontrará arquivos no formato `.json` que representam diferentes conjuntos de requisições para testar os endpoints da API.

### Passo 2: Importando no Postman

1. Abra o **Postman** em sua máquina.
2. Clique em **Import** no canto superior esquerdo da interface do Postman.
3. Selecione a opção **Upload Files**.
4. Navegue até a pasta `pos-architecture-java/tiago/postman` e selecione o arquivo `.json` da coleção que deseja importar.
5. Clique em **Open** e a coleção será carregada no seu Postman.

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