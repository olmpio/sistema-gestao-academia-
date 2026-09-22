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
