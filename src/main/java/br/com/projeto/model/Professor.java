package br.com.academia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "professores")
public class Professor {
    @Id
    private String id;

    @Field("nome")
    private String nome;

    @Field("cref")
    private String cref;

    @Field("turno_trabalho")
    private String turnoTrabalho;

    public Professor() {}

    public Professor(String id, String nome, String cref, String turnoTrabalho) {
        this.id = id;
        this.nome = nome;
        this.cref = cref;
        this.turnoTrabalho = turnoTrabalho;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCref() { return cref; }
    public void setCref(String cref) { this.cref = cref; }
    public String getTurnoTrabalho() { return turnoTrabalho; }
    public void setTurnoTrabalho(String turnoTrabalho) { this.turnoTrabalho = turnoTrabalho; }
}
