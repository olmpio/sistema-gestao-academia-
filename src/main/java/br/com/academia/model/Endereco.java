package br.com.academia.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class Endereco {
    @Field("logradouro")
    private String logradouro;

    @Field("cidade")
    private String cidade;

    @Field("estado")
    private String estado;

    public Endereco() {}

    public Endereco(String logradouro, String cidade, String estado) {
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}