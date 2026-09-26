db.planos.insertMany([
  { _id: "PLN-MENSAL", nome: "Plano Fit Mensal", valorMensal: 120.00, beneficios: ["Musculação"] },
  { _id: "PLN-ANUAL", nome: "Plano Fit Anual", valorMensal: 89.90, beneficios: ["Musculação", "Aulas Coletivas"] }
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
  }
])

db.professores.insertOne({
  nome: "Rafael Costa", 
  cref: "123456-G/BA", 
  turnoTrabalho: "Manhã"
})

db.exercicios.insertMany([
  { nome: "Supino Reto", grupoMuscular: "Peito" },
  { nome: "Agachamento Livre", grupoMuscular: "Pernas" }
])

let aluno1 = db.alunos.findOne({ nome: "Jefté Goes" })._id
let aluno2 = db.alunos.findOne({ nome: "Maria Silva" })._id
let prof1 = db.professores.findOne({ nome: "Rafael Costa" })._id
let ex1 = db.exercicios.findOne({ nome: "Supino Reto" })._id
let ex2 = db.exercicios.findOne({ nome: "Agachamento Livre" })._id

db.contratos.insertOne({
  aluno_id: aluno1,
  plano_id: "PLN-ANUAL",
  dataInicio: ISODate("2026-01-15T00:00:00Z"),
  status: "Ativo"
})

let contrato1 = db.contratos.findOne({ aluno_id: aluno1 })._id

db.pagamentos.insertOne({
  contrato_id: contrato1,
  valor: 89.90,
  status: "Pago",
  dataPagamento: ISODate("2026-01-15T08:30:00Z")
})

db.treinos.insertOne({
  aluno_id: aluno1,
  professor_id: prof1,
  objetivo: "Hipertrofia",
  dataCriacao: ISODate("2026-01-16T10:00:00Z"),
  itensTreino: [
    { exercicio_id: ex1, series: 4, repeticoes: 10, tempoDescanso: 60 },
    { exercicio_id: ex2, series: 4, repeticoes: 12, tempoDescanso: 90 }
  ]
})

db.avaliacoes.insertOne({
  aluno_id: aluno1,
  professor_id: prof1,
  dataAvaliacao: ISODate("2026-01-16T09:00:00Z"),
  peso: 75.5,
  percentualGordura: 18.5,
  medidasCorporais: { bracoDireito: 35, coxaEsquerda: 55, peitoral: 98 }
})

db.checkins.insertMany([
  { aluno_id: aluno1, dataHoraAcesso: ISODate("2026-09-17T06:30:00Z"), catraca: 1 },
  { aluno_id: aluno2, dataHoraAcesso: ISODate("2026-09-17T18:45:00Z"), catraca: 2 }
])

db.equipamentos.insertMany([
  { nome: "Esteira ProX", marca: "Movement", dataProximaManutencao: ISODate("2026-12-01") },
  { nome: "Halter 10kg", marca: "Ziva", manutencaoEmDia: true }
])
