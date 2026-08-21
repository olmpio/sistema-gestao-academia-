# Levantamento das Entidades do Projeto - Sistema de Academia

## 1. Lista e Descrição das Entidades

| Entidade | Descrição | Responsabilidade no sistema |
| :--- | :--- | :--- |
| **Aluno** | Cliente matriculado na academia. | Acessar treinos, realizar check-in e acompanhar situação financeira. |
| **Professor** | Profissional de educação física da academia. | Prescrever treinos e realizar avaliações físicas dos alunos. |
| **Plano** | Modalidades de assinatura oferecidas (ex: Mensal, Anual). | Funcionar como catálogo de preços e benefícios disponíveis. |
| **Contrato** | Vínculo gerado quando um aluno assina um plano. | Controlar a vigência (início e fim) e o status do plano do aluno. |
| **Treino** | Ficha de exercícios montada para um aluno. | Guiar a rotina de exercícios do aluno com base no seu objetivo. |
| **Exercício** | Catálogo de movimentos (ex: Supino, Agachamento). | Padronizar a execução e servir de base para a montagem dos treinos. |
| **Pagamento** | Registro das mensalidades ou taxas pagas/pendentes. | Controlar o fluxo de caixa e a inadimplência dos alunos. |
| **Avaliação Física** | Registro periódico de medidas corporais e bioimpedância. | Acompanhar a evolução dos resultados do aluno ao longo do tempo. |
| **Equipamento** | Máquinas e aparelhos disponíveis no espaço físico. | Controlar o inventário e o cronograma de manutenção preventiva. |
| **Check-in** | Registro de entrada do aluno na catraca. | Controlar o acesso diário e gerar métricas de frequência. |

---

# Levantamento dos Atributos

## 1. Aluno
* **_id**
* **nome**
* **cpf**
* **email**
* **telefone**
* **dataNascimento**
* **endereco** *(Documento incorporado)*
  * **logradouro**
  * **numero**
  * **bairro**
  * **cidade**
  * **estado**

## 2. Professor
* **_id**
* **nome**
* **cref**
* **email**
* **telefone**
* **turnoTrabalho** *(ex: Manhã, Noite)*

## 3. Plano
* **_id**
* **nome** *(ex: "Plano Fit Anual")*
* **valorMensal**
* **duracaoMeses**
* **beneficios** *(Array de strings)*

## 4. Contrato
* **_id**
* **aluno_id** *(Referência)*
* **plano_id** *(Referência)*
* **dataInicio**
* **dataFim**
* **status** *(Ativo, Cancelado, Congelado)*

## 5. Treino
* **_id**
* **aluno_id** *(Referência)*
* **professor_id** *(Referência)*
* **objetivo** *(ex: "Hipertrofia", "Emagrecimento")*
* **dataCriacao**
* **itensTreino** *(Array de documentos incorporados)*
  * **exercicio_id** *(Referência)*
  * **series**
  * **repeticoes**
  * **tempoDescanso**
*6. Exercício*
├── _id
├── nome
├── grupoMuscular
├── descricaoExecucao
└── equipamento_id (Referência - opcional)

*7. Pagamento*
├── _id
├── contrato_id (Referência)
├── dataVencimento
├── dataPagamento
├── valor
├── status (Pago, Pendente, Atrasado)
└── metodoPagamento (Pix, Cartão, Dinheiro)

*8. Avaliação Física*
├── _id
├── aluno_id (Referência)
├── professor_id (Referência)
├── dataAvaliacao
├── peso
├── altura
├── percentualGordura
└── medidasCorporais (Documento incorporado)
    ├── bracoDireito
    ├── coxaEsquerda
    ├── cintura
    └── peitoral

*9. Equipamento*
├── _id
├── nome
├── marca
├── dataAquisicao
└── dataProximaManutencao

*10. Check-in*
├── _id
├── aluno_id (Referência)
└── dataHoraAcesso

---
