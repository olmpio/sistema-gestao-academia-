package br.com.academia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDate;

@Document(collection = "avaliacoes")
public class AvaliacaoFisica {
    @Id
    private String id;

    @DocumentReference
    @Field("aluno")
    private Aluno aluno;

    @DocumentReference
    @Field("professor")
    private Professor professor;

    @Field("data_avaliacao")
    private LocalDate dataAvaliacao;

    @Field("peso")
    private Double peso;

    @Field("percentual_gordura")
    private Double percentualGordura;

    @Field("medidas_corporais")
    private MedidasCorporais medidasCorporais;

    public AvaliacaoFisica() {}

    public AvaliacaoFisica(String id, Aluno aluno, Professor professor, LocalDate dataAvaliacao, Double peso, Double percentualGordura, MedidasCorporais medidasCorporais) {
        this.id = id;
        this.aluno = aluno;
        this.professor = professor;
        this.dataAvaliacao = dataAvaliacao;
        this.peso = peso;
        this.percentualGordura = percentualGordura;
        this.medidasCorporais = medidasCorporais;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
    public LocalDate getDataAvaliacao() { return dataAvaliacao; }
    public void setDataAvaliacao(LocalDate dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }
    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }
    public Double getPercentualGordura() { return percentualGordura; }
    public void setPercentualGordura(Double percentualGordura) { this.percentualGordura = percentualGordura; }
    public MedidasCorporais getMedidasCorporais() { return medidasCorporais; }
    public void setMedidasCorporais(MedidasCorporais medidasCorporais) { this.medidasCorporais = medidasCorporais; }
}
