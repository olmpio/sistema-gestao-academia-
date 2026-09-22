db.contratos.aggregate([
  {
    $lookup: {
      from: "planos",
      localField: "plano_id",
      foreignField: "_id",
      as: "detalhes_plano"
    }
  }
])
## 3. Decisões: Embedded Documents vs References

| Relacionamento | Estratégia | Justificativa considerando leitura/escrita, tamanho e vida útil |
| :--- | :--- | :--- |
| **Aluno → Endereço** | Embedded | O endereço não tem vida independente e pertence exclusivamente ao aluno (1:1). São acessados juntos na tela de perfil. Não cresce (tamanho estático). |
| **Avaliação → Medidas** | Embedded | As medidas corporais (braço, perna, etc) fazem parte do escopo da avaliação. Sempre são lidas e gravadas juntas. Não são compartilhadas com outras entidades. |
| **Treino → Itens (Exercícios)** | Embedded (Parcial) | As regras do treino (séries, repetições) são embutidas em um array dentro do `Treino` para que o app do aluno carregue a ficha em uma única leitura rápida (alta frequência de leitura). |
| **Aluno → Check-in** | Reference | Um aluno fará centenas de check-ins. Se fossem embutidos, teríamos o anti-padrão de *Unbounded Array* (crescimento infinito), o que degrada a performance e estoura o limite de 16MB. Possuem alta frequência de escrita independente. |
| **Contrato → Plano** | Reference | O "Plano" é compartilhado por milhares de alunos. Se embutíssemos os dados do plano no contrato, qualquer alteração na descrição do plano exigiria um update em milhares de contratos. |

## 4. Schema Flexível (Análise)
No MongoDB, criamos a coleção `equipamentos` com uma diferença estrutural de propósito:
* A maioria dos equipamentos possui um campo `dataProximaManutencao` (ISODate).
* Alguns equipamentos mais simples (ex: Halteres) não possuem data, apenas um campo booleano `manutencaoEmDia: true`.

**Respostas da Equipe:**
* **Essa diferença é realmente necessária?** Sim, pois equipamentos eletrônicos (esteiras) exigem cronograma de datas, enquanto pesos livres (halteres) sofrem apenas inspeção visual de estado.
* **Essa flexibilidade ajuda ou prejudica?** Ajuda, pois evita que a aplicação precise gravar campos com valores nulos (`null` ou vazios) forçadamente, economizando espaço em disco e adaptando a estrutura à realidade física do objeto.
* **Seria melhor estabelecer uma estrutura mais consistente?** Para o "core" financeiro (Contratos, Pagamentos), a consistência é vital. Porém, para inventário de máquinas com naturezas tão diferentes, a flexibilidade do MongoDB é um trunfo.
