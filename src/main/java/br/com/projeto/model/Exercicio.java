package br.com.academia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "exercicios")
public class Exercicio {
    @Id
    private String id;

    @Field("nome")
    private String nome;

    @Field("grupo_muscular")
    private String grupoMuscular;

    @DocumentReference
    @Field("equipamento")
    private Equipamento equipamento;

    public Exercicio() {}

    public Exercicio(String id, String nome, String grupoMuscular, Equipamento equipamento) {
        this.id = id;
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
        this.equipamento = equipamento;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getGrupoMuscular() { return grupoMuscular; }
    public void setGrupoMuscular(String grupoMuscular) { this.grupoMuscular = grupoMuscular; }
    public Equipamento getEquipamento() { return equipamento; }
    public void setEquipamento(Equipamento equipamento) { this.equipamento = equipamento; }
}
