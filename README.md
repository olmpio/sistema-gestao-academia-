# Sistema de Gestão de Academia (`sistema-gestao-academia`)

Projeto desenvolvido para a disciplina de **Banco de Dados NoSQL**, implementando a camada de persistência e uma API REST funcional utilizando **Java**, **Spring Boot** e **MongoDB**.

## Integrantes da Equipe

* **Alexsandro Suzarte dos Santos**
* **Moisés Cerqueira dos Santos Santana**
* **Pedro Olímpio Arantes Freire Brandão**
* **Rodrigo Souza Oliveira**
* **Jonatas Admilson Paixão Santos**

## Objetivo e Descrição do Sistema

O **Sistema de Gestão de Academia** é uma solução backend desenvolvida para automatizar e centralizar as operações vitais de estabelecimentos fitness. O sistema gerencia o ciclo completo de alunos, contratações de planos, controle de pagamentos, agendamento e presenças em check-ins, montagem de fichas de treinos com exercícios e acompanhamento de avaliações físicas antropométricas.

A aplicação foi projetada sobre o paradigma de **Banco de Dados Orientado a Documentos (MongoDB)**, aplicando estratégias otimizadas de modelagem NoSQL para garantir alto desempenho nas leituras, consistência de dados através da camada de serviço Spring e facilidade de integração via endpoints REST.

## Tecnologias Utilizadas

* **Linguagem de Programação:** Java 17+
* **Framework Backend:** Spring Boot 3.x
* **Mapeamento e Persistência:** Spring Data MongoDB
* **Banco de Dados NoSQL:** MongoDB (v6.0+)
* **Gerenciador de Build e Dependências:** Apache Maven

## Requisitos para Execução

Para compilar e rodar este projeto localmente, certifique-se de possuir instalado em sua máquina:

1. **Java Development Kit (JDK 17)** ou superior.
2. **Apache Maven 3.8+**.
3. **MongoDB Server (v6.0+)** em execução local ou acesso a uma instância de cluster no **MongoDB Atlas**.
4. Uma ferramenta cliente de testes de API HTTP (ex.: **Insomnia**).

## Configuração do MongoDB

A aplicação se conecta ao MongoDB por meio de URI configurada no arquivo `src/main/resources/application.properties`.

Por padrão, a conexão está definida para uma instância local do MongoDB escutando na porta `27017` e salvando os documentos no banco `sistema_academia`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/sistema_academia
spring.main.allow-bean-definition-overriding=true
```

*Caso utilize um banco de dados remoto (como MongoDB Atlas) ou necessite de autenticação, substitua o valor da propriedade `spring.data.mongodb.uri` pela sua string de conexão completa.*

## Como Executar a Aplicação

1. **Clonar o Repositório**:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd sistema-gestao-academia
   ```

2. **Compilar e Executar o Projeto via Maven**:
   No terminal, na pasta raiz do projeto, execute:
   ```bash
   mvn spring-boot:run
   ```
   *A aplicação iniciará na porta padrão `8080` (`http://localhost:8080`).*

3. **Executar os Testes Automatizados**:
   Para validar as regras de negócio e suítes de teste unitário:
   ```bash
   mvn test
   ```

## Principais Entidades Implementadas

O sistema é composto por **10 entidades principais** mapeadas em collections do MongoDB:

 1. **Aluno**: Dados cadastrais dos alunos, status de matrícula e endereço.
 2. **Plano**: Catálogo de assinaturas da academia (duração, valor e benefícios).
 3. **Contrato**: Vinculação entre um aluno e o plano contratado.
 4. **Professor**: Cadastro dos instrutores e personal trainers.
 5. **Equipamento**: Maquinários e equipamentos disponíveis na academia.
 6. **Exercicio**: Exercícios cadastrados associados a um equipamento específico.
 7. **Treino**: Ficha de treinamento montada por um professor para um aluno.
 8. **Pagamento**: Histórico financeiro e mensalidades associadas aos contratos.
 9. **AvaliacaoFisica**: Registro periódico de medidas corporais e percentuais de gordura.
10. **Checkin**: Registro de entrada e frequência diária do aluno na academia.

## Estratégias de Embedded Documents e References

O projeto equilibra de forma estratégica as duas abordagens de modelagem do MongoDB:

### A. Documentos Incorporados (*Embedded Documents*)

Utilizados quando há um relacionamento forte de composição (os dados pertencem exclusivamente ao documento pai e são consultados conjuntamente):

* **`Endereco`** (incorporado dentro de `Aluno`): Evita *joins* desnecessários para recuperar o endereço do cliente.
* **`MedidasCorporais`** (incorporado dentro de `AvaliacaoFisica`): Armazena dados antropométricos e de dobras cutâneas diretamente no documento da avaliação.
* **`ItemTreino`** (incorporado dentro de `Treino`): Define a lista de exercícios, séries, repetições e descanso dentro da ficha do aluno.

### B. Referências (*References* via `@DocumentReference`)

Utilizadas em coleções independentes que possuem ciclo de vida próprio ou relacionamentos N:N e 1:N com volume elevado de acessos:

* **`Contrato`** ➡️ Referencia o `Aluno` e o `Plano`.
* **`Treino`** ➡️ Referencia o `Aluno` e o `Professor`.
* **`Pagamento`** ➡️ Referencia o `Contrato`.
* **`Exercicio`** ➡️ Referencia o `Equipamento`.

## 🔌 Endpoints Disponíveis (API REST)

A API disponibiliza o conjunto completo de operações CRUD (`CREATE`, `READ`, `UPDATE`, `DELETE`) para as 10 entidades:

| Entidade | Endpoint Base | Métodos HTTP |
| ----- | ----- | ----- |
| **Alunos** | `/api/alunos` | `GET`, `POST`, `PUT`, `DELETE` |
| **Planos** | `/api/planos` | `GET`, `POST`, `PUT`, `DELETE` |
| **Contratos** | `/api/contratos` | `GET`, `POST`, `PUT`, `DELETE` |
| **Professores** | `/api/professores` | `GET`, `POST`, `PUT`, `DELETE` |
| **Equipamentos** | `/api/equipamentos` | `GET`, `POST`, `PUT`, `DELETE` |
| **Exercícios** | `/api/exercicios` | `GET`, `POST`, `PUT`, `DELETE` |
| **Treinos** | `/api/treinos` | `GET`, `POST`, `PUT`, `DELETE` |
| **Pagamentos** | `/api/pagamentos` | `GET`, `POST`, `PUT`, `DELETE` |
| **Avaliações Físicas** | `/api/avaliacoes` | `GET`, `POST`, `PUT`, `DELETE` |
| **Check-ins** | `/api/checkins` | `GET`, `POST`, `PUT`, `DELETE` |

### Códigos de Resposta HTTP Utilizados:

* `200 OK`: Operação de busca ou alteração concluída com sucesso.
* `201 Created`: Novo registro criado com sucesso.
* `204 No Content`: Exclusão efetuada com sucesso.
* `400 Bad Request`: Erro de validação ou referência a documento inexistente.
* `404 Not Found`: Registro solicitado por ID não encontrado.
* `409 Conflict`: Conflito com regra de negócio (ex.: cadastro de e-mail duplicado).

## Exemplos de Utilização da API

### 1. Cadastrar um Novo Aluno (Com Embedded Document `Endereco`)

* **Requisição**: `POST /api/alunos`
* **Body (JSON)**:
  ```json
  {
    "nome": "Carlos Silva",
    "email": "carlos.silva@email.com",
    "telefone": "75999998888",
    "dataNascimento": "1995-05-20",
    "ativo": true,
    "endereco": {
      "logradouro": "Avenida Getúlio Vargas",
      "numero": "1500",
      "bairro": "Centro",
      "cidade": "Feira de Santana",
      "estado": "BA",
      "cep": "44001-000"
    }
  }
  ```

* **Resposta Esperada** (`201 Created`):
  ```json
  {
    "id": "1",
    "nome": "Carlos Silva",
    "email": "carlos.silva@email.com",
    "telefone": "75999998888",
    "dataNascimento": "1995-05-20",
    "ativo": true,
    "endereco": {
      "logradouro": "Avenida Getúlio Vargas",
      "numero": "1500",
      "bairro": "Centro",
      "cidade": "Feira de Santana",
      "estado": "BA",
      "cep": "44001-000"
    }
  }
  ```

### 2. Cadastrar um Plano

* **Requisição**: `POST /api/planos`
* **Body (JSON)**:
  ```json
  {
    "nome": "Plano Anual VIP",
    "descricao": "Acesso total à musculação e aulas coletivas",
    "valorMensal": 119.90,
    "duracaoMeses": 12
  }
  ```

* **Resposta Esperada** (`201 Created`):
  ```json
  {
    "id": "2",
    "nome": "Plano Anual VIP",
    "descricao": "Acesso total à musculação e aulas coletivas",
    "valorMensal": 119.90,
    "duracaoMeses": 12
  }
  ```

### 3. Criar um Contrato (Utilizando `@DocumentReference`)

* **Requisição**: `POST /api/contratos`
* **Body (JSON)**:
  ```json
  {
    "aluno": { "id": "1" },
    "plano": { "id": "2" },
    "dataInicio": "2026-11-01",
    "dataFim": "2027-11-01",
    "status": "ATIVO"
  }
  ```

* **Resposta Esperada** (`201 Created`):
  ```json
  {
    "id": "3",
    "aluno": { "id": "1" },
    "plano": { "id": "2" },
    "dataInicio": "2026-11-01",
    "dataFim": "2027-11-01",
    "status": "ATIVO"
  }
  ```

### 4. Consultar Todos os Alunos

* **Requisição**: `GET /api/alunos`
* **Resposta Esperada** (`200 OK`):
  ```json
  [
    {
      "id": "1",
      "nome": "Carlos Silva",
      "email": "carlos.silva@email.com",
      "telefone": "75999998888",
      "ativo": true
    }
  ]
  ```

### 5. Exemplo de Erro — Cadastro com E-mail Duplicado (Regra de Negócio)

* **Requisição**: `POST /api/alunos` com e-mail já existente no banco.
* **Resposta Esperada** (`409 Conflict`):
  ```json
  "Já existe um aluno cadastrado com este e-mail."
  ```
