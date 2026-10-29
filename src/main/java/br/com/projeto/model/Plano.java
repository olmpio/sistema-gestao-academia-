package br.com.academia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.math.BigDecimal;
import java.util.List;

@Document(collection = "planos")
public class Plano {
    @Id
    private String id;

    @Field("nome")
    private String nome;

    @Field("valor_mensal")
    private BigDecimal valorMensal;

    @Field("beneficios")
    private List<String> beneficios;

    public Plano() {}

    public Plano(String id, String nome, BigDecimal valorMensal, List<String> beneficios) {
        this.id = id;
        this.nome = nome;
        this.valorMensal = valorMensal;
        this.beneficios = beneficios;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getValorMensal() { return valorMensal; }
    public void setValorMensal(BigDecimal valorMensal) { this.valorMensal = valorMensal; }
    public List<String> getBeneficios() { return beneficios; }
    public void setBeneficios(List<String> beneficios) { this.beneficios = beneficios; }
}
