package br.com.academia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "treinos")
public class Treino {
    @Id
    private String id;

    @DocumentReference
    @Field("aluno")
    private Aluno aluno;

    @DocumentReference
    @Field("professor")
    private Professor professor;

    @Field("objetivo")
    private String objetivo;

    @Field("data_criacao")
    private LocalDate dataCriacao;

    @Field("itens_treino")
    private List<ItemTreino> itensTreino;

    public Treino() {}

    public Treino(String id, Aluno aluno, Professor professor, String objetivo, LocalDate dataCriacao, List<ItemTreino> itensTreino) {
        this.id = id;
        this.aluno = aluno;
        this.professor = professor;
        this.objetivo = objetivo;
        this.dataCriacao = dataCriacao;
        this.itensTreino = itensTreino;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }
    public List<ItemTreino> getItensTreino() { return itensTreino; }
    public void setItensTreino(List<ItemTreino> itensTreino) { this.itensTreino = itensTreino; }
}
