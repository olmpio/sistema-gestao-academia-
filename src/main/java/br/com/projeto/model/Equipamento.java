package br.com.academia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDate;

@Document(collection = "equipamentos")
public class Equipamento {
    @Id
    private String id;

    @Field("nome")
    private String nome;

    @Field("marca")
    private String marca;

    @Field("data_proxima_manutencao")
    private LocalDate dataProximaManutencao;

    public Equipamento() {}

    public Equipamento(String id, String nome, String marca, LocalDate dataProximaManutencao) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.dataProximaManutencao = dataProximaManutencao;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public LocalDate getDataProximaManutencao() { return dataProximaManutencao; }
    public void setDataProximaManutencao(LocalDate dataProximaManutencao) { this.dataProximaManutencao = dataProximaManutencao; }
}
