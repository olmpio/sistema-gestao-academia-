# Levantamento das Entidades do Projeto - Sistema de Academia

## 1. Lista e Descrição das Entidades

| Entidade             | Descrição                                                 | Responsabilidade no sistema                                          |
| :------------------- | :-------------------------------------------------------- | :------------------------------------------------------------------- |
| **Aluno**            | Cliente matriculado na academia.                          | Acessar treinos, realizar check-in e acompanhar situação financeira. |
| **Professor**        | Profissional de educação física da academia.              | Prescrever treinos e realizar avaliações físicas dos alunos.         |
| **Plano**            | Modalidades de assinatura oferecidas (ex: Mensal, Anual). | Funcionar como catálogo de preços e benefícios disponíveis.          |
| **Contrato**         | Vínculo gerado quando um aluno assina um plano.           | Controlar a vigência (início e fim) e o status do plano do aluno.    |
| **Treino**           | Ficha de exercícios montada para um aluno.                | Guiar a rotina de exercícios do aluno com base no seu objetivo.      |
| **Exercício**        | Catálogo de movimentos (ex: Supino, Agachamento).         | Padronizar a execução e servir de base para a montagem dos treinos.  |
| **Pagamento**        | Registro das mensalidades ou taxas pagas/pendentes.       | Controlar o fluxo de caixa e a inadimplência dos alunos.             |
| **Avaliação Física** | Registro periódico de medidas corporais e bioimpedância.  | Acompanhar a evolução dos resultados do aluno ao longo do tempo.     |
| **Equipamento**      | Máquinas e aparelhos disponíveis no espaço físico.        | Controlar o inventário e o cronograma de manutenção preventiva.      |
| **Check-in**         | Registro de entrada do aluno na catraca.                  | Controlar o acesso diário e gerar métricas de frequência.            |

---

## 2. Levantamento dos Atributos

### 2.1 Aluno

Representa a pessoa matriculada na academia.

```text
Aluno
├── _id
├── nome
├── cpf
├── email
├── telefone
├── dataNascimento
└── endereco (Documento incorporado)
    ├── logradouro
    ├── numero
    ├── bairro
    ├── cidade
    └── estado
```

### 2.2 Professor

Representa o profissional responsável pela orientação dos alunos e elaboração dos treinos.

```text
Professor
├── _id
├── nome
├── cref
├── email
├── telefone
└── turnoTrabalho
```

Exemplos de `turnoTrabalho`:

* Manhã
* Tarde
* Noite

### 2.3 Plano

Representa os planos de assinatura oferecidos pela academia.

```text
Plano
├── _id
├── nome
├── valorMensal
├── duracaoMeses
└── beneficios (Array de strings)
```

Exemplo de benefícios:

```text
beneficios
├── "Acesso à musculação"
├── "Avaliação física"
└── "Acesso às aulas coletivas"
```

### 2.4 Contrato

Representa o vínculo entre um aluno e o plano contratado.

```text
Contrato
├── _id
├── aluno_id (Referência)
├── plano_id (Referência)
├── dataInicio
├── dataFim
└── status
```

Possíveis valores para `status`:

* Ativo
* Cancelado
* Congelado

### 2.5 Treino

Representa a ficha de exercícios criada para um aluno.

```text
Treino
├── _id
├── aluno_id (Referência)
├── professor_id (Referência)
├── objetivo
├── dataCriacao
└── itensTreino (Array de documentos incorporados)
    ├── exercicio_id (Referência)
    ├── series
    ├── repeticoes
    └── tempoDescanso
```

Exemplos de `objetivo`:

* Hipertrofia
* Emagrecimento
* Resistência
* Condicionamento físico

### 2.6 Exercício

Representa os exercícios disponíveis para utilização nas fichas de treino.

```text
Exercício
├── _id
├── nome
├── grupoMuscular
├── descricao
└── equipamentoNecessario
```

Exemplos de `grupoMuscular`:

* Peito
* Costas
* Pernas
* Ombros
* Bíceps
* Tríceps

### 2.7 Pagamento

Representa os pagamentos realizados ou pendentes pelos alunos.

```text
Pagamento
├── _id
├── aluno_id (Referência)
├── contrato_id (Referência)
├── valor
├── dataVencimento
├── dataPagamento
├── formaPagamento
└── status
```

Possíveis valores para `formaPagamento`:

* Pix
* Cartão
* Dinheiro

Possíveis valores para `status`:

* Pendente
* Pago
* Atrasado
* Cancelado

### 2.8 Avaliação Física

Representa as avaliações físicas realizadas periodicamente para acompanhar a evolução do aluno.

```text
Avaliação Física
├── _id
├── aluno_id (Referência)
├── professor_id (Referência)
├── dataAvaliacao
├── peso
├── altura
├── percentualGordura
├── massaMuscular
└── observacoes
```

### 2.9 Equipamento

Representa as máquinas e equipamentos disponíveis na academia.

```text
Equipamento
├── _id
├── nome
├── tipo
├── marca
├── dataAquisicao
├── status
└── ultimaManutencao
```

Possíveis valores para `status` (A adição do status ajuda a organização interna da academia para saber a situação do equipamento):

* Disponível
* Em manutenção
* Indisponível

### 2.10 Check-in

Representa o registro de entrada do aluno na academia.

```text
Check-in
├── _id
├── aluno_id (Referência)
├── data
├── horaEntrada
└── horaSaida
```

---

## 3. Relacionamentos

As principais relações identificadas no sistema são:

```text
Aluno
├── possui → Contrato
├── realiza → Pagamento
├── possui → Treino
├── realiza → Avaliação Física
└── realiza → Check-in

Professor
├── prescreve → Treino
└── realiza → Avaliação Física

Plano
└── é contratado através de → Contrato

Contrato
├── pertence a → Aluno
└── está associado a → Plano

Treino
├── pertence a → Aluno
├── é elaborado por → Professor
└── contém → Exercício

Pagamento
├── pertence a → Aluno
└── está relacionado a → Contrato

Avaliação Física
├── pertence a → Aluno
└── é realizada por → Professor
```

### Cardinalidades principais

* Um **Aluno** pode possuir vários **Contratos** ao longo do tempo.
* Um **Plano** pode estar associado a vários **Contratos**.
* Um **Aluno** pode possuir vários **Treinos**.
* Um **Professor** pode elaborar vários **Treinos**.
* Um **Treino** pode possuir vários **Exercícios**.
* Um **Exercício** pode aparecer em vários **Treinos**.
* Um **Aluno** pode possuir vários **Pagamentos**.
* Um **Contrato** pode possuir vários **Pagamentos**.
* Um **Aluno** pode realizar várias **Avaliações Físicas**.
* Um **Professor** pode realizar várias **Avaliações Físicas**.
* Um **Aluno** pode realizar vários **Check-ins**.

---

## 4. Pensando como MongoDB

A modelagem do banco será feita considerando a forma como os dados serão utilizados pela aplicação.

Nem todas as entidades precisam necessariamente se transformar em uma collection independente. Quando uma informação possui uma relação muito próxima com o documento principal e normalmente é acessada junto com ele, pode ser utilizado um **documento incorporado (embedded document)**.

Quando uma informação possui muitos registros, pode ser consultada independentemente ou possui relacionamento com várias outras entidades, é mais adequado utilizar uma **collection separada**, fazendo referência ao documento através do seu `_id`.

---

## 5. Embedded Documents

### 5.1 Endereço do Aluno

O endereço será armazenado como um documento incorporado dentro de `Aluno`.

Exemplo:

```json
{
  "_id": 1,
  "nome": "João da Silva",
  "cpf": "11111111111",
  "email": "joao@email.com",
  "telefone": "75999999999",
  "endereco": {
    "logradouro": "Rua A",
    "numero": 100,
    "bairro": "Centro",
    "cidade": "Feira de Santana",
    "estado": "BA"
  }
}
```

### Justificativa

O endereço pertence diretamente ao aluno e normalmente será consultado junto com suas informações cadastrais.

Além disso, não existe necessidade de consultar o endereço de forma independente. Por isso, utilizar um documento incorporado simplifica a estrutura e evita uma consulta adicional.

### 5.2 Itens do Treino

Os itens que compõem um treino serão armazenados como um array de documentos incorporados dentro de `Treino`.

Exemplo:

```json
{
  "_id": 1,
  "aluno_id": 10,
  "professor_id": 5,
  "objetivo": "Hipertrofia",
  "dataCriacao": "2026-08-17",
  "itensTreino": [
    {
      "exercicio_id": 1,
      "series": 4,
      "repeticoes": 10,
      "tempoDescanso": 60
    },
    {
      "exercicio_id": 2,
      "series": 3,
      "repeticoes": 12,
      "tempoDescanso": 60
    }
  ]
}
```

### Justificativa

Os itens representam a composição de um treino específico e normalmente serão acessados junto com o treino.

Por isso, não há necessidade de criar uma collection independente para cada item do treino. O `exercicio_id` continua sendo uma referência para a collection de `Exercício`.

---

## 6. Collections Separadas

As seguintes entidades serão tratadas como collections separadas:

```text
Aluno
Professor
Plano
Contrato
Treino
Exercício
Pagamento
Avaliação Física
Equipamento
Check-in
```

Cada uma dessas entidades possui informações que podem ser consultadas ou manipuladas independentemente.

### 6.1 Aluno

Será uma collection separada porque representa uma das principais entidades do sistema e possui diversos relacionamentos com outras informações.

```text
alunos
```

Outras entidades poderão fazer referência ao `_id` do aluno.

### 6.2 Professor

Será uma collection separada porque os professores podem ser associados a vários treinos e avaliações físicas.

```text
professores
```

### 6.3 Plano

Será uma collection separada porque os planos funcionam como opções de contratação disponíveis para diversos alunos.

```text
planos
```

### 6.4 Contrato

Será uma collection separada porque um aluno pode possuir diferentes contratos ao longo do tempo e cada contrato possui informações próprias de vigência e status.

```text
contratos
```

O contrato fará referência ao aluno e ao plano através de seus respectivos `_id`.

### 6.5 Treino

Será uma collection separada porque um aluno pode possuir vários treinos e os treinos possuem informações próprias, como objetivo, professor responsável e data de criação.

```text
treinos
```

Os exercícios utilizados no treino serão referenciados através do `exercicio_id`.

### 6.6 Exercício

Será uma collection separada porque o mesmo exercício pode ser utilizado em diferentes treinos e precisa ser mantido em um catálogo centralizado.

```text
exercicios
```

Dessa forma, não é necessário duplicar todas as informações do exercício dentro de cada treino.

### 6.7 Pagamento

Será uma collection separada porque cada aluno pode possuir diversos pagamentos ao longo do tempo.

```text
pagamentos
```

Isso facilita consultas relacionadas a pagamentos pendentes, pagos e atrasados.

### 6.8 Avaliação Física

Será uma collection separada porque um aluno pode realizar várias avaliações ao longo do tempo.

```text
avaliacoes_fisicas
```

Dessa forma, é possível manter um histórico das avaliações e acompanhar a evolução do aluno.

### 6.9 Equipamento

Será uma collection separada porque os equipamentos possuem informações próprias e precisam ser gerenciados independentemente dos alunos e treinos.

```text
equipamentos
```

A collection permitirá controlar informações como status, aquisição e manutenção.

### 6.10 Check-in

Será uma collection separada porque um aluno pode realizar muitos check-ins ao longo do tempo.

```text
checkins
```

Manter esses registros separados permite consultar o histórico de frequência e gerar métricas de utilização da academia.

---

## 7. Resumo da Modelagem

| Entidade             | Tipo de armazenamento        | Justificativa                                                                                  |
| :------------------- | :--------------------------- | :--------------------------------------------------------------------------------------------- |
| **Aluno**            | Collection separada          | Entidade principal do sistema e possui vários relacionamentos.                                 |
| **Professor**        | Collection separada          | Pode estar relacionado a vários treinos e avaliações.                                          |
| **Plano**            | Collection separada          | Pode ser utilizado por vários contratos.                                                       |
| **Contrato**         | Collection separada          | Possui histórico e informações próprias de vigência e status.                                  |
| **Treino**           | Collection separada          | Possui informações próprias e pode existir em grande quantidade por aluno.                     |
| **Exercício**        | Collection separada          | Pode ser reutilizado em diferentes treinos.                                                    |
| **Pagamento**        | Collection separada          | Um aluno pode possuir vários pagamentos e o histórico precisa ser mantido.                     |
| **Avaliação Física** | Collection separada          | Mantém o histórico de avaliações do aluno.                                                     |
| **Equipamento**      | Collection separada          | Possui ciclo de vida e manutenção próprios.                                                    |
| **Check-in**         | Collection separada          | Registra o histórico de frequência dos alunos.                                                 |
| **Endereço**         | Embedded Document em Aluno   | É uma informação diretamente relacionada ao aluno e normalmente acessada junto com seus dados. |
| **Itens do Treino**  | Embedded Documents em Treino | São parte da composição do treino e normalmente são acessados junto com ele.                   |

---

## 8. Justificativa das Decisões de Modelagem

Para definir a estrutura do banco, pensamos em como as informações serão utilizadas dentro do sistema da academia.

O endereco foi escolhido para ser um embedded document dentro de Aluno, pois é uma informação que pertence diretamente ao aluno e normalmente será consultada junto com seus dados. Dessa forma, não é necessário criar uma collection somente para armazenar endereços.

Os itensTreino também serão documentos incorporados dentro de Treino, pois eles fazem parte da ficha de treino e serão utilizados junto com as informações do próprio treino. Cada item terá uma referência para o exercício utilizado.

As demais entidades serão mantidas em collections separadas, pois possuem informações próprias e podem ter vários registros relacionados. Por exemplo, um aluno pode ter vários pagamentos, avaliações físicas, check-ins e treinos durante o período em que estiver matriculado na academia.

A entidade Exercício também ficará em uma collection separada porque o mesmo exercício pode ser utilizado em diferentes treinos. Assim, evitamos repetir todas as informações do exercício em cada treino.

As referências entre as collections serão feitas utilizando os campos _id, como aluno_id, professor_id, plano_id e exercicio_id.

Com essa estrutura, procuramos organizar os dados de uma forma que facilite o uso do sistema e aproveite a maneira como o MongoDB trabalha com documentos e collections.
