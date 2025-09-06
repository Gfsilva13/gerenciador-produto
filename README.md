# 🏦 Gerenciador de Produtos - API REST com Quarkus

## 📘 Descrição

Projeto desenvolvido como parte do curso *Quarkus Avançado*. O objetivo é criar uma **API REST** API REST para gerenciar produtos.  
A API deve permitir realizar as operações de **CRUD (Create, Read, Update, Delete)** de produtos.

---
## 🚀 Requisitos Funcionais

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

---

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

## 💾 Tecnologias Utilizadas

- **Quarkus** (Framework principal)
- **H2 Database** (Banco de dados em memória)
- **Swagger/OpenAPI** (Documentação da API)
- **Validação básica de entrada**

---

## 🧩 Extensões Quarkus Utilizadas
- quarkus-hibernate-orm
- quarkus-jdbc-h2
- quarkus-smallrye-openapi
  
---
## ▶️ Como executar

1. Execute a aplicação em modo de desenvolvimento:
   ```bash
   ./mvnw quarkus:dev

2. Acesse a aplicação via navegador:

☑️ Interface padrão: http://localhost:8080/

☑️ Interface Swagger: http://localhost:8080/q/swagger-ui/

---

## 🔗 Endpoints da API

| Método | Rota                                  | Descrição                           | Permissão                        |
| ------ | ------------------------------------- | ----------------------------------- | -------------------------------- |
| GET    | `/produtos                            | Retorna todos produtos cadastradas  |                                  |
| GET    | `/produtos/{id}`                      | Retorna um produtopor ID            |                                  |
| POST   | `/produtos                            | Cria um novo produto                |                                  |
| PUT    | `/produtos/{id}`                      | Atualiza dados do produto           |                                  |
| DELETE | `/produtos/{id}`                      | Exclui um produto                   |                                  |


---

🧪 Testes

✅ Como executar os testes

`./mvnw test `

🧪 Cobertura

• 	Testes unitários com Mockito

---
🧠 Sugestão de Funcionalidades que Podem Ser Acrescentadas

- **Mockito** (Testes unitários)
- **Frontend** (Interface de usuário)
- **Consumo de API externa** para obter UF via CEP
- **quarkus-security** para habilitar a autenticação e autorização.
- **Funcionalidades extras:** Relatório por agência; Histórico de transações (CRUD) por conta
- 
## 🧪 Testes e Qualidade
1. Testes de Integração
- Simular chamadas reais à API e verificar persistência no banco.
2. Testes de Performance
- Avaliar tempo de resposta da API com JMeter

## 🛡️ Segurança e Autenticação
1. Autenticação Basic Auth
- Perfis admin e users no arquivo application-users.properties.
- Perfis de acesso admin e users no arquivo application-roles.properties.

## 📦 Deploy e Escalabilidade
1. Containerização
- Criar um Dockerfile para empacotar a aplicação.
- Usar docker-compose para subir banco e API juntos.
2. Deploy em Nuvem
- Subir em serviços como Heroku, Railway, ou Azure.
- Configurar variáveis de ambiente e persistência.
---

## 👥 Desenvolvedor

- 👷 Genivaldo Ferreira da Silva
 ---
