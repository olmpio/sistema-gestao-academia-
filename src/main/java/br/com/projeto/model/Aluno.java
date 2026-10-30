package br.com.academia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDate;

@Document(collection = "alunos")
public class Aluno {
    @Id
    private String id;

    @Field("nome")
    private String nome;

    @Field("email")
    private String email;

    @Field("idade")
    private Integer idade;

    @Field("ativo")
    private Boolean ativo;

    @Field("data_matricula")
    private LocalDate dataMatricula;

    @Field("endereco")
    private Endereco endereco;

    public Aluno() {}

    public Aluno(String id, String nome, String email, Integer idade, Boolean ativo, LocalDate dataMatricula, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.ativo = ativo;
        this.dataMatricula = dataMatricula;
        this.endereco = endereco;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public LocalDate getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }
    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
}
