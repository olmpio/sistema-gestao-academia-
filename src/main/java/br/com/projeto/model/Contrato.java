package br.com.academia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDate;

@Document(collection = "contratos")
public class Contrato {
    @Id
    private String id;

    @DocumentReference
    @Field("aluno")
    private Aluno aluno;

    @DocumentReference
    @Field("plano")
    private Plano plano;

    @Field("data_inicio")
    private LocalDate dataInicio;

    @Field("status")
    private String status;

    public Contrato() {}

    public Contrato(String id, Aluno aluno, Plano plano, LocalDate dataInicio, String status) {
        this.id = id;
        this.aluno = aluno;
        this.plano = plano;
        this.dataInicio = dataInicio;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Plano getPlano() { return plano; }
    public void setPlano(Plano plano) { this.plano = plano; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
