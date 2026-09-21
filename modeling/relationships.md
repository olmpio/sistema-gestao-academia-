# Modelagem de Dados e Relacionamentos

## 1. Entidades Envolvidas e Cardinalidade
Com base nas 10 entidades definidas (Aluno, Professor, Plano, Contrato, Treino, Exercício, Pagamento, Avaliação Física, Equipamento, Check-in), mapeamos os seguintes relacionamentos principais:

* **Aluno realiza Check-in**: 1:N (Um aluno tem muitos check-ins)
* **Aluno possui Contrato**: 1:N (Um aluno pode ter vários contratos ao longo do tempo)
* **Plano contém Contrato**: 1:N (Um plano é base para vários contratos)
* **Professor prescreve Treino**: 1:N (Um professor cria vários treinos)
* **Aluno recebe Treino**: 1:N (Um aluno pode ter um histórico de vários treinos)
* **Treino contém Exercício**: N:N (Um treino tem vários exercícios, um exercício está em vários treinos)

## 2. Estratégia de Identificadores (`_id`)
* **Regra Geral (ObjectId):** Para a maioria das coleções (`alunos`, `treinos`, `checkins`, `pagamentos`), decidimos utilizar o `ObjectId` gerado automaticamente pelo MongoDB.
  * *Justificativa:* Garante unicidade global distribuída, evita colisões, possui indexação nativa otimizada e já embute um timestamp de criação, sendo ideal para alto volume de inserções (ex: check-ins).
* **Exceção (Identificador Personalizado):** Para a coleção `planos`, optamos por usar um `_id` personalizado em String (ex: `"PLN-ANUAL"`).
  * *Justificativa:* Planos são entidades de catálogo com baixo volume de registros e raramente mudam. Um ID semântico facilita a leitura humana no banco e a referência em contratos.