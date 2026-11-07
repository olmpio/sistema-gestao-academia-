package br.com.academia.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class MedidasCorporais {
    @Field("braco_direito")
    private Double bracoDireito;

    @Field("coxa_esquerda")
    private Double coxaEsquerda;

    @Field("peitoral")
    private Double peitoral;

    public MedidasCorporais() {}

    public MedidasCorporais(Double bracoDireito, Double coxaEsquerda, Double peitoral) {
        this.bracoDireito = bracoDireito;
        this.coxaEsquerda = coxaEsquerda;
        this.peitoral = peitoral;
    }

    public Double getBracoDireito() { return bracoDireito; }
    public void setBracoDireito(Double bracoDireito) { this.bracoDireito = bracoDireito; }
    public Double getCoxaEsquerda() { return coxaEsquerda; }
    public void setCoxaEsquerda(Double coxaEsquerda) { this.coxaEsquerda = coxaEsquerda; }
    public Double getPeitoral() { return peitoral; }
    public void setPeitoral(Double peitoral) { this.peitoral = peitoral; }
}