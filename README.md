# 🏦 Gerenciador de Produtos - API REST com Quarkus

## 📘 Descrição

Projeto desenvolvido como parte do curso *Quarkus Avançado*. O objetivo é criar uma **API REST** API REST para gerenciar produtos.  
- Exercício 1: Realizar as operações de **CRUD (Create, Read, Update, Delete)** de produtos.
- Exercício 2: Adicionar **autenticação** à aplicação de gerenciamento de produtos. A autenticação deve ser feita utilizando o **Quarkus OIDC (`quarkus-oidc`)**, permitindo que apenas usuários autenticados possam acessar os endpoints da API.
---
## 🚀 Requisitos Funcionais Exercício 1

### 1. Criar Produto
- **Método HTTP:** `POST`
- **Path:** `/produtos`
- **Corpo da requisição:** JSON com os dados do produto.
- **Status esperado:**
    - `201 Created` em caso de sucesso.
    - `400 Bad Request` em caso de erro de validação.

### 2. Listar Produtos
- **Método HTTP:** `GET`
- **Path:** `/produtos`
- **Status esperado:**
    - `200 OK` com a lista de produtos.

### 3. Buscar Produto por ID
- **Método HTTP:** `GET`
- **Path:** `/produtos/{id}`
- **Status esperado:**
    - `200 OK` em caso de sucesso.
    - `404 Not Found` se o produto não existir.

### 4. Atualizar Produto
- **Método HTTP:** `PUT`
- **Path:** `/produtos/{id}`
- **Corpo da requisição:** JSON com os dados atualizados do produto.
- **Status esperado:**
    - `200 OK` em caso de sucesso.
    - `400 Bad Request` em caso de erro de validação.
    - `404 Not Found` se o produto não existir.

### 5. Excluir Produto
- **Método HTTP:** `DELETE`
- **Path:** `/produtos/{id}`
- **Status esperado:**
    - `204 No Content` em caso de sucesso.
    - `404 Not Found` se o produto não existir.

## Modelo de Produto
Cada produto deve possuir no mínimo os seguintes atributos:
- `id` (gerado automaticamente)
- `nome` (obrigatório)
- `descricao` (opcional)
- `preco` (obrigatório, maior que zero)
---
## Critérios de Avaliação (Exercício 1)

- Uso correto dos **métodos HTTP** (`GET`, `POST`, `PUT`, `DELETE`).
- Uso correto dos **status HTTP**.
- Uso correto e consistente dos **paths**.
- Validações básicas de entrada.
- Estrutura organizada do código.
---

## 🚀 Requisitos Funcionais Exercício 2

### 1. Configuração do OIDC
- Configure o Quarkus para usar `quarkus-oidc` com um **provedor OIDC** (exemplo: Keycloak).

### 2. Proteção de Endpoints
- Os endpoints da API `/produtos` devem exigir autenticação.

### 3. Controle de Acesso por Papel (Roles)
- Defina pelo menos dois papéis:
    - **admin**: pode criar, atualizar e excluir produtos.
    - **user**: pode apenas listar e buscar produtos.
- A aplicação deve validar os papéis contidos no token JWT e permitir ou negar acesso de acordo.

### 4. Exemplo de Regras de Acesso
- **POST /produtos** → somente `admin`.
- **PUT /produtos/{id}** → somente `admin`.
- **DELETE /produtos/{id}** → somente `admin`.
- **GET /produtos** e **GET /produtos/{id}** → `admin` e `user`.
---
## Critérios de Avaliação (Exercício 2)

- Configuração correta do **quarkus-oidc**.
- Proteção dos endpoints usando **anotações de segurança** (`@RolesAllowed`, `@Authenticated`).
- Implementação correta das regras de acesso por papel.
- Estrutura organizada do código e configuração.
---

# 🚀 Exercício 3

## Descrição

Além do CRUD de produtos já implementado no **Exercício 1**, você deverá adicionar **cache** para melhorar a performance da aplicação.  

O cache deve ser utilizado no endpoint de **buscar produto por ID**, e sempre que houver alguma alteração em um produto (criação, atualização ou exclusão), o cache correspondente deve ser invalidado.

---

## Requisitos Funcionais

### 1. Buscar Produto por ID com Cache

- **Método HTTP:** `GET`
- **Path:** `/produtos/{id}`
- O resultado da busca deve ser armazenado em cache para acessos futuros ao mesmo produto.
- **Status esperado:**
  - `200 OK` em caso de sucesso.
  - `404 Not Found` se o produto não existir.

### 2. Invalidar Cache ao Alterar Produto

Sempre que ocorrer uma das operações abaixo, o cache do produto correspondente deve ser removido/invalidado:

- **Atualizar Produto (`PUT /produtos/{id}`)** → invalida cache do produto atualizado.  
- **Excluir Produto (`DELETE /produtos/{id}`)** → invalida cache do produto removido.  

---

## Critérios de Avaliação (Exercício 3)

- Uso correto do **cache** no endpoint de busca de produto por ID.
- Invalidação correta do cache nas operações de alteração.
- Manutenção do uso correto de **status HTTP** e **métodos HTTP** já definidos no Exercício 1.
- Organização e clareza do código.  

## Bônus

Documentação: https://quarkus.io/guides/kafka

Quando a aplicação criar uma conta, enviar uma mensagem utilizando `quarkus-smallrye-kafka`.

A mensagem deve ter os seguintes atributos:

```yaml
nome: String
descricao: String
preco: BigDecimal
id: Long
dataCriacao: Instant
```

A aplicação que vai consumir pode ser a mesma que vai produzir.

---
## 💾 Tecnologias Utilizadas

- **Quarkus** (Framework principal)
- **H2 Database** (Banco de dados em memória)
- **Swagger/OpenAPI** (Documentação da API)
- **Validação básica de entrada**
- **Quarkus OIDC com Keycloak**
---
## 🧩 Extensões Quarkus Utilizadas
- quarkus-hibernate-orm
- quarkus-jdbc-h2
- quarkus-smallrye-openapi
- quarkus-junit5-mockito
- quakus-oidc
- quarkus-keycloak-admin-rest-client
---
## ▶️ Como executar

1. keycloak: realm 'produto-manager'
2. Execute a aplicação em modo de desenvolvimento:
   ```bash
       docker compose up 
3. Criar Token no Postman:
   ```bash
       http://localhost:53355/realms/produto-manager/protocol/openid-connect/token
5. Acesse a aplicação via navegador:
☑️ Interface Swagger: http://localhost:8081/q/swagger-ui/

   🔗   roles : "user" - password "123456"
   
   🔗   roles : "admin" - password "123456"

---
## 🔗 Endpoints da API

| Método | Rota                                  | Descrição                           | Permissão                        |
| ------ | ------------------------------------- | ----------------------------------- | -------------------------------- |
| GET    | `/produtos`                           | Retorna todos produtos cadastradas  | @RolesAllowed({"admin", "user"}) |
| GET    | `/produtos/{id}`                      | Retorna um produtopor ID            | @RolesAllowed({"admin", "user"}) |
| POST   | `/produtos`                           | Cria um novo produto                | @RolesAllowed({"admin"})         |
| PUT    | `/produtos/{id}`                      | Atualiza dados do produto           | @RolesAllowed({"admin"})         |
| DELETE | `/produtos/{id}`                      | Exclui um produto                   | @RolesAllowed({"admin"})         |

---
🧪 Testes

✅ Como executar os testes

`./mvnw test `

🧪 Cobertura

• 	Testes unitários com Mockito

--- 
## 🛡️ Segurança e Autenticação
- **quarkus-security** para habilitar a autenticação e autorização.
- Perfis admin e users no arquivo produto-manager-realm.json

---
## 📦Containerização
- Dockerfile para empacotar a aplicação.
- docker-compose para subir API.

---  
🧠 Sugestão de Funcionalidades que Podem Ser Acrescentadas

- **Frontend** (Interface de usuário)
- **Funcionalidades extras:** Relatório por produto; Histórico de produtos (CRUD)
  
## 🧪 Testes e Qualidade
1. Testes de Integração
- Simular chamadas reais à API e verificar persistência no banco
2. Testes de Performance
- Avaliar tempo de resposta da API com JMeter

## 📦 Deploy e Escalabilidade
1. Deploy em Nuvem
- Subir em serviços como Heroku, Railway, ou Azure.
- Configurar variáveis de ambiente e persistência.
---

## 👥 Desenvolvedor

- 👷 Genivaldo Ferreira da Silva
 ---
