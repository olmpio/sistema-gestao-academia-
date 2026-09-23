db.planos.insertMany([
  { _id: "PLN-MENSAL", nome: "Plano Fit Mensal", valorMensal: 120.00, beneficios: ["Musculação"] },
  { _id: "PLN-ANUAL", nome: "Plano Fit Anual", valorMensal: 89.90, beneficios: ["Musculação", "Aulas Coletivas", "Cadeira de Massagem"] }
])

db.alunos.insertMany([
  {
    nome: "Jefté Goes", email: "jefte@email.com", idade: 35, ativo: true,
    dataMatricula: ISODate("2026-01-15T09:00:00Z"),
    endereco: { logradouro: "Rua A", cidade: "Feira de Santana", estado: "BA" }
  },
  {
    nome: "Maria Silva", email: "maria@email.com", idade: 28, ativo: true,
    dataMatricula: ISODate("2026-02-10T14:30:00Z"),
    endereco: { logradouro: "Av. Maria Quitéria", cidade: "Feira de Santana", estado: "BA" }
  },
  {
    nome: "Carlos Souza", email: "carlos@email.com", idade: 42, ativo: false,
    dataMatricula: ISODate("2025-11-05T08:00:00Z"),
    endereco: { logradouro: "Rua B", cidade: "Salvador", estado: "BA" }
  },
  {
    nome: "Ana Costa", email: "ana@email.com", idade: 22, ativo: true,
    dataMatricula: ISODate("2026-09-01T18:00:00Z"),
    endereco: { logradouro: "Rua das Flores", cidade: "Feira de Santana", estado: "BA" }
  }
])

let aluno1 = db.alunos.findOne({ nome: "Jefté Goes" })._id;
let aluno2 = db.alunos.findOne({ nome: "Maria Silva" })._id;

db.contratos.insertOne({
  aluno_id: aluno1,
  plano_id: "PLN-ANUAL",
  dataInicio: ISODate("2026-01-15T00:00:00Z"),
  status: "Ativo"
})

db.checkins.insertMany([
  { aluno_id: aluno1, dataHoraAcesso: ISODate("2026-09-17T06:30:00Z"), catraca: 1 },
  { aluno_id: aluno2, dataHoraAcesso: ISODate("2026-09-17T18:45:00Z"), catraca: 2 },
  { aluno_id: aluno1, dataHoraAcesso: ISODate("2026-09-18T06:35:00Z"), catraca: 1 },
  { aluno_id: aluno2, dataHoraAcesso: ISODate("2026-09-18T19:00:00Z"), catraca: 2 }
])

db.equipamentos.insertMany([
  { nome: "Esteira ProX", marca: "Movement", dataProximaManutencao: ISODate("2026-12-01") },
  { nome: "Halter 10kg", marca: "Ziva", manutencaoEmDia: true }
])
