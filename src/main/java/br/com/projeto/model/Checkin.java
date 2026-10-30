package br.com.academia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Document(collection = "checkins")
public class Checkin {
    @Id
    private String id;

    @DocumentReference
    @Field("aluno")
    private Aluno aluno;

    @Field("data_hora_acesso")
    private LocalDateTime dataHoraAcesso;

    @Field("catraca")
    private Integer catraca;

    public Checkin() {}

    public Checkin(String id, Aluno aluno, LocalDateTime dataHoraAcesso, Integer catraca) {
        this.id = id;
        this.aluno = aluno;
        this.dataHoraAcesso = dataHoraAcesso;
        this.catraca = catraca;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public LocalDateTime getDataHoraAcesso() { return dataHoraAcesso; }
    public void setDataHoraAcesso(LocalDateTime dataHoraAcesso) { this.dataHoraAcesso = dataHoraAcesso; }
    public Integer getCatraca() { return catraca; }
    public void setCatraca(Integer catraca) { this.catraca = catraca; }
}
