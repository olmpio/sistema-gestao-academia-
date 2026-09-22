db.alunos.find({ ativo: true })
db.planos.find({ _id: "PLN-ANUAL" })
db.checkins.find({ catraca: 1 })

db.alunos.find({ ativo: true, "endereco.cidade": "Feira de Santana" })
db.planos.find({ valorMensal: { $lt: 100.00 }, beneficios: "Musculação" })

db.alunos.find({}, { nome: 1, ativo: 1, _id: 0 })
db.planos.find({}, { nome: 1, valorMensal: 1, _id: 0 })
db.contratos.find({}, { aluno_id: 1, dataInicio: 1 })

db.checkins.find({ aluno_id: ObjectId("COLOQUE_UM_ID_AQUI") })

db.alunos.find({ "endereco.estado": "BA" })

db.planos.find({ beneficios: "Aulas Coletivas" })

db.checkins.find().sort({ dataHoraAcesso: -1 })
