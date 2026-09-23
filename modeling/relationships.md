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
  * 3. Decisões: Embedded Documents vs References

| Relacionamento | Estratégia | Justificativa considerando leitura/escrita, tamanho e vida útil |
| :--- | :--- | :--- |
| *Aluno → Endereço* | Embedded | O endereço não tem vida independente e pertence exclusivamente ao aluno (1:1). São acessados juntos na tela de perfil. Não cresce (tamanho estático). |
| *Avaliação → Medidas* | Embedded | As medidas corporais (braço, perna, etc) fazem parte do escopo da avaliação. Sempre são lidas e gravadas juntas. Não são compartilhadas com outras entidades. |
| *Treino → Itens (Exercícios)* | Embedded (Parcial) | As regras do treino (séries, repetições) são embutidas em um array dentro do Treino para que o app do aluno carregue a ficha em uma única leitura rápida (alta frequência de leitura). |
| *Aluno → Check-in* | Reference | Um aluno fará centenas de check-ins. Se fossem embutidos, teríamos o anti-padrão de Unbounded Array (crescimento infinito), o que degrada a performance e estoura o limite de 16MB. Possuem alta frequência de escrita independente. |
| *Contrato → Plano* | Reference | O "Plano" é compartilhado por milhares de alunos. Se embutíssemos os dados do plano no contrato, qualquer alteração na descrição do plano exigiria um update em milhares de contratos. |

## 4. Schema Flexível (Análise)
No MongoDB, criamos a coleção equipamentos com uma diferença estrutural de propósito:
* A maioria dos equipamentos possui um campo dataProximaManutencao (ISODate).
* Alguns equipamentos mais simples (ex: Halteres) não possuem data, apenas um campo booleano manutencaoEmDia: true.

*Respostas da Equipe:*
* *Essa diferença é realmente necessária?* Sim, pois equipamentos eletrônicos (esteiras) exigem cronograma de datas, enquanto pesos livres (halteres) sofrem apenas inspeção visual de estado.
* *Essa flexibilidade ajuda ou prejudica?* Ajuda, pois evita que a aplicação precise gravar campos com valores nulos (null ou vazios) forçadamente, economizando espaço em disco e adaptando a estrutura à realidade física do objeto.
* *Seria melhor estabelecer uma estrutura mais consistente?* Para o "core" financeiro (Contratos, Pagamentos), a consistência é vital. Porém, para inventário de máquinas com naturezas tão diferentes, a flexibilidade do MongoDB é um trunfo
