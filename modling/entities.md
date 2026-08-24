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

## 6. Exercício
* **_id**
* **nome**
* **grupo_muscular**
* **descricao_execucao**
* **equipamento_id** *(Referência - opcional)*

## 7. Pagamento
* **_id**
* **contrato_id (Referência)**
* **data_Vencimento**
* **data_pagamento**
* **valor**
* **status** *(Pago, Pendente, Atrasado)*
* **metodo_pagamento** *(Pix, Cartão, Dinheiro)*

## 8. Avaliação Física
* **_id**
* **aluno_id** *(Referência)*
* **professor_id** *(Referência)*
* **data_avaliacao**
* **peso**
* **altura**
* **percentual_gordura**
* **medidas_corporais** *(Documento incorporado)*
  * **braco_direito**
  * **coxa_esquerda**
  * **cintura**
  * **peitoral**

## 9. Equipamento
* **_id**
* **nome**
* **marca**
* **data_aquisicao**
* **data_proxima_manutencao**

## 10. Check-in
* **_id**
* **aluno_id** *(Referência)*
* **data_hora_acesso**

---
#  Identificação dos Principais Relacionamentos

Para mapear como as informações se conectam no domínio da academia, identificamos as seguintes cardinalidades e relações lógicas entre as entidades:

* **Aluno e Contrato (1:N):** Um aluno pode ter tido vários contratos ao longo do tempo (ex: renovações anuais), mas cada contrato pertence a apenas um aluno.
* **Plano e Contrato (1:N):** O "Plano Anual" (catálogo) pode estar vinculado a centenas de contratos de diferentes alunos.
* **Aluno e Treino (1:N):** Um aluno possui um histórico com várias fichas de treino, mas cada ficha é elaborada para aquele aluno específico.
* **Professor e Treino (1:N):** Um professor elabora diversas fichas de treino para diversos alunos.
* **Treino e Exercício (N:N):** Uma ficha de treino contém vários exercícios, e um mesmo exercício (ex: Supino) faz parte de diversas fichas de treino (essa relação é resolvida com os documentos embutidos `itensTreino` fazendo referência ao catálogo de Exercícios).
* **Contrato e Pagamento (1:N):** Um contrato (ex: anual) gera vários pagamentos (12 mensalidades).
* **Aluno e Check-in (1:N):** Um aluno realiza múltiplos acessos à catraca ao longo do tempo.
* **Aluno e Avaliação Física (1:N):** O aluno faz diversas avaliações corporais durante o seu tempo na academia para comparar sua evolução.

---
## 4. Discussão e Modelagem (Embedded x Collections)

Seguindo a premissa de modelagem do MongoDB, nossas decisões foram baseadas na pergunta: **"Como os dados serão utilizados pela aplicação?"**

Abaixo, justificamos a escolha de utilizar documentos embutidos (embedded) ou coleções separadas por referências (references) para os principais relacionamentos:

### Decisões de Uso de Documentos Incorporados (Embedded Documents)

1. **Endereço no Aluno (`endereco`)**
   * **Justificativa:** O endereço é um dado que pertence exclusivamente a um único aluno (relação 1:1). Na aplicação, toda vez que acessarmos o perfil do aluno para ver seus dados cadastrais, precisaremos do endereço. Embutir esse dado evita uma busca desnecessária em outra collection, melhorando a performance de leitura.

2. **Detalhes da Avaliação Física (`medidasCorporais`)**
   * **Justificativa:** Em vez de criar uma entidade separada para as circunferências corporais, incorporamos dentro do documento de Avaliação Física. Esses dados sempre serão acessados e renderizados juntos na tela de histórico de avaliação do usuário.

3. **Itens do Treino (`itensTreino` dentro de Treino)**
   * **Justificativa:** A ficha de treino de um aluno é composta por vários exercícios (séries, repetições). Embutir a "linha do treino" dentro do documento `Treino` faz muito sentido, pois o aplicativo do aluno carregará a ficha inteira de uma só vez para que ele possa treinar. Nós apenas incorporamos as regras da execução (séries, repetições) e o ID do exercício, mantendo o cadastro base do exercício em uma collection separada.

### Decisões de Uso de Coleções Separadas (Collections e Referências)

1. **Aluno e Check-in**
   * **Justificativa:** Se os check-ins fossem um array embutido dentro de `Aluno`, com o passar dos anos esse array cresceria infinitamente (padrão *Unbounded Array*), o que degrada a performance do MongoDB e pode ultrapassar o limite de 16MB do documento. Portanto, `Check-in` será uma collection separada que referencia o `aluno_id`. A aplicação fará inserções rápidas (alto volume de gravação na catraca).

2. **Exercícios e Equipamentos (Catálogos)**
   * **Justificativa:** A aplicação precisa listar todos os exercícios disponíveis de forma independente para que o professor monte o treino. Se embutíssemos dados do exercício dentro de cada treino, teríamos dados extremamente duplicados e, se o nome do exercício mudasse, teríamos que atualizar milhares de treinos.

3. **Aluno e Treino / Avaliação Física**
   * **Justificativa:** Um aluno pode ter dezenas de fichas de treino arquivadas e dezenas de avaliações físicas ao longo dos anos. Manter essas entidades em coleções separadas com referência ao `aluno_id` permite que o sistema busque apenas o "Treino Ativo" ou a "Última Avaliação", mantendo o documento principal do `Aluno` leve e rápido.
